package com.example.bangjenggotsproject_2019130014.Interface;

import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.Model.ResultDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ResultDine;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterfaceDelivery {
    @Headers({
            "accept: text/plain",
            "Content-Type: application/json-patch+json"
    })
    @POST("inputDelivery.php")
    Call<ModelDelivery> delivery(@Body ModelDelivery modelDelivery);

    @GET("getAllDataDelivery.php")
    Call<ResultDelivery> alldatadelivery();

    @GET("getAllDataDeliveryById.php")
    Call<ResultDelivery> alldatadeliverybyid(@Query("id_user") Integer id);

    @PUT("updateDelivery.php")
    Call<ModelDelivery> updatedelivery(@Body ModelDelivery modelDelivery);
}
