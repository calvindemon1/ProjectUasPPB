package com.example.bangjenggotsproject_2019130014.Interface;

import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterfaceDelivery {
    @POST("inputDelivery.php")
    Call<ModelDelivery> delivery (@Body ModelDelivery modelDelivery);
}
