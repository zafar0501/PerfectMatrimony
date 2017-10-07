package in.microlan.www.perfectmatrimony.network;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.etw.android.R;
import com.etw.android.authenticate.model.AuthenticateDO;
import com.etw.android.common.LogoutUser;
import com.etw.android.common.model.ErrorMessageDO;
import com.etw.android.common.model.UserInfo;
import com.etw.android.common.services.ArchiveUnarchiveAllService;
import com.etw.android.multiUserHandler.presenter.IUserInfoDBInterface;
import com.etw.android.multiUserHandler.presenter.IUserInfoDBInterfaceCallBack;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.etw.android.common.model.UserInfo.CurrentUser;

public class NetworkHandler<T> implements INetworkResult {

    private static final String TAG = NetworkHandler.class.getSimpleName();
    private INetworkResult iNetworkResultListener;
    private Context context;

    public NetworkHandler(INetworkResult iNetworkResultListener) {
        this.iNetworkResultListener = iNetworkResultListener;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Callback EnqueueRequest(final int responseType, Context context) {
        this.context = context;
        return new Callback<T>() {

            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

                if (response.isSuccessful()) {
                    iNetworkResultListener.onSuccess(response.body(), responseType);
                } else {
                    String errorMessage = parseError(response).getMessage();
                    if (!TextUtils.isEmpty(errorMessage)) {
                        iNetworkResultListener.onError(errorMessage);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {

                Log.d(TAG, "onFailure: ");
                iNetworkResultListener.onError(t.getMessage());
            }
        };

    }

    private ErrorMessageDO parseError(Response<?> response) {

        ErrorMessageDO errorMessageDO = new ErrorMessageDO();

        try {

            JSONObject jsonObject = new JSONObject(response.errorBody().string());
            String errors = jsonObject.getString("error");

            if (jsonObject.has("message")) {
                String message = jsonObject.getString("message");
                errorMessageDO.setMessage(message);
            } else {
                if (!TextUtils.equals(errors, "invalid_token")) {
                    errorMessageDO.setMessage(errors);
                } else {
                    getNewRefreshToken();
                    errors = "";
                }
            }
            errorMessageDO.setError(errors);

        } catch (Exception ex) {
            Log.d(TAG, "parseError: " + ex);
            onError(null);
        }

        return errorMessageDO;
    }

    @SuppressWarnings("unchecked")
    private void getNewRefreshToken() {

        IEndPointsAPI endPointsAPI = NetworkClient.getClient().create(IEndPointsAPI.class);
        NetworkHandler networkHandler = new NetworkHandler(NetworkHandler.this);

        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(context.getString(R.string.access_token), CurrentUser().getAuthToken());
            jsonObject.addProperty(context.getString(R.string.refresh_token), CurrentUser().getRefreshToken());
            jsonObject.addProperty(context.getString(R.string.auth_key), CurrentUser().getAuthKey());

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
            Call<AuthenticateDO> refreshAPICall = endPointsAPI.refreshTokenAPI(CurrentUser().getAuthToken(), body);
            refreshAPICall.enqueue(networkHandler.EnqueueRequest(NetworkConstants.RESPONSE_TYPE.REFRESH_TOKEN, context));

        } catch (Exception ex) {
            Log.d(TAG, "getNewRefreshToken: ");
        }

    }

    @Override
    public void onSuccess(Object responseBody, int responseType) {

        switch (responseType) {
            case NetworkConstants.RESPONSE_TYPE.REFRESH_TOKEN:

                AuthenticateDO authenticateDO = (AuthenticateDO) responseBody;
                CurrentUser().setAuthToken(authenticateDO.getAccessToken());
                CurrentUser().setRefreshToken(authenticateDO.getRefreshToken());
                CurrentUser().setAuthKey(authenticateDO.getAuthKey());

                //Update the result in sqlite for Current user
                IUserInfoDBInterface userInfoDBInterface = new IUserInfoDBInterface(context, new IUserInfoDBInterfaceCallBack() {
                    @Override
                    public void UserInfoResultList(List<UserInfo> userInfoResult) {

                    }

                    @Override
                    public void OnUpdateUserInfo(int returnToken) {
                    }
                });
                userInfoDBInterface.updateUserInfo(CurrentUser(), 0);

                //HIT the same API again
                iNetworkResultListener.callCurrentAPIWithNewToken();

                break;
        }
    }

    @Override
    public void onError(String message) {
        ///If failed, redirect user to Login
        //onError
        if (context instanceof ArchiveUnarchiveAllService) {

            ArchiveUnarchiveAllService archiveUnarchiveAllService = (ArchiveUnarchiveAllService) context;
            archiveUnarchiveAllService.showResult(null);

        } else {
            new LogoutUser().displayLoginScreen((Activity) context);
        }


    }

    @Override
    public void callCurrentAPIWithNewToken() {

    }
}
