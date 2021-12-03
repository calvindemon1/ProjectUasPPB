package com.example.bangjenggotsproject_2019130014.Interface;

import com.example.bangjenggotsproject_2019130014.Model.ModelDine;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterfaceDine {
    @POST("inputDine.php")
    Call<ModelDine> dinein (@Body ModelDine modelDine);
}
