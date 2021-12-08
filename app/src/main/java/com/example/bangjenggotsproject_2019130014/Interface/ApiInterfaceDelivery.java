package com.example.bangjenggotsproject_2019130014.Interface;

import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.Model.ResultDelivery;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterfaceDelivery {
    @Headers({
            "accept: text/plain",
            "Content-Type: application/json-patch+json"
    })
    @POST("inputDelivery.php")
    Call<ModelDelivery> delivery(@Body ModelDelivery modelDelivery);

    @GET("getAllDataDelivery.php")
    Call<ResultDelivery> alldatadelivery();

    @PUT("updateDelivery.php")
    Call<ModelDelivery> updatedelivery(@Body ModelDelivery modelDelivery);
}
