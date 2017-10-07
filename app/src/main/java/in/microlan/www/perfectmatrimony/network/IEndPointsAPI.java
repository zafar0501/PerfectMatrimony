package in.microlan.www.perfectmatrimony.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IEndPointsAPI {

    //Login
    @Headers({
            "Content-Type: application/json"
    })
    @POST("useraccount/login")
    Call loginAPI(@Header("ETWClientId") String companyId, @Header("Authorization") String authorization);


    //UsaerInfo
    @GET("useraccount/v1/users/" + "{fullUrl}" + "?details=true")
    Call callUserInfoAPI(@Path(value = "fullUrl") String fullUrl, @Header("Accept") String contentType,
                         @Header("Cookie") String cookie);

    // User SignOut
    @GET("useraccount/logout")
    Call<Void> callUserSignOut(@Header("Accept") String contentType, @Header("Cookie") String cookie);

    // User Forgot Password
    @POST("useraccount/reset")
    Call<Void> callUserForgotPassword(@Header("Accept") String contentType, @Body RequestBody emailBody);


}
