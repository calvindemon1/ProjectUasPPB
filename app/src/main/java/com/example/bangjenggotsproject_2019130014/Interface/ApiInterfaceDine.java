package com.example.bangjenggotsproject_2019130014.Interface;

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

public interface ApiInterfaceDine {
    @Headers({
            "accept: text/plain",
            "Content-Type: application/json-patch+json"
    })
    @POST("inputDine.php")
    Call<ModelDine> dinein (@Body ModelDine modelDine);

    @GET("getAllDataDine.php")
    Call<ResultDine> alldatadine();

    @GET("getAllDataDineById.php")
    Call<ResultDine> alldatadinebyid(@Query("id_user") Integer id);

    @PUT("updateDine.php")
    Call<ModelDine> updatedine(@Body ModelDine modelDine);
}
