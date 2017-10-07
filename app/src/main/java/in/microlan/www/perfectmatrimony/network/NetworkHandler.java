package in.microlan.www.perfectmatrimony.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonObject;

import in.microlan.www.perfectmatrimony.authenticate.model.AuthenticateDO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {

                Log.d(TAG, "onFailure: ");
                iNetworkResultListener.onError(t.getMessage());
            }
        };

    }


    @SuppressWarnings("unchecked")
    private void getNewRefreshToken() {

        IEndPointsAPI endPointsAPI = NetworkClient.getClient().create(IEndPointsAPI.class);
        NetworkHandler networkHandler = new NetworkHandler(NetworkHandler.this);

        try {
            JsonObject jsonObject = new JsonObject();
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        } catch (Exception ex) {

        }

    }

    @Override
    public void onSuccess(Object responseBody, int responseType) {

        switch (responseType) {
            case NetworkConstants.RESPONSE_TYPE.REFRESH_TOKEN:

                AuthenticateDO authenticateDO = (AuthenticateDO) responseBody;


                //HIT the same API again
                iNetworkResultListener.callCurrentAPIWithNewToken();

                break;
        }
    }

    @Override
    public void onError(String message) {
        ///If failed, redirect user to Login
        //onError


    }

    @Override
    public void callCurrentAPIWithNewToken() {

    }
}
