package com.example.bangjenggotsproject_2019130014.Interface;

import com.example.bangjenggotsproject_2019130014.Model.ModelUser;
import com.example.bangjenggotsproject_2019130014.Model.ResultDataUser;
import com.example.bangjenggotsproject_2019130014.Model.ResultUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterfaceUser {

    @POST("register.php")
    Call<ModelUser> registerUser(@Body ModelUser modelUser);

    @POST("login.php")
    Call<ResultUser> loginUser(@Body ModelUser modelUser);

    @GET("getDataUser.php")
    Call<ResultDataUser> getUser(@Query("id_user") Integer id);
}
