package devesh.com.simpleloginsignup.Interfaces;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface RestAPI {

    @FormUrlEncoded
    @POST("/LoginUser")
    public void loginUser(
            @Field("email") String email,
            @Field("password") String password,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/InsertUserDetails")
    public void insertUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("userType") String userType,
            Callback<Response> callback);
}
