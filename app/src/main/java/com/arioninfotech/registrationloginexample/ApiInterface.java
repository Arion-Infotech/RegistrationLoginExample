package com.arioninfotech.registrationloginexample;
import java.util.List;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.mime.MultipartTypedOutput;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/registrationservice.jsp")
    public void register(@Field("name") String Name,
                         @Field("place") String Place,
                         @Field("email") String Email,
                         @Field("password") String Phone,
                         Callback<SignUpResponse> callback);

    @FormUrlEncoded
    @POST("/loginservice.jsp")
    public void signin(@Field("email") String Username,
                       @Field("password") String Password, Callback<SignUpResponse> callback
    );

}
