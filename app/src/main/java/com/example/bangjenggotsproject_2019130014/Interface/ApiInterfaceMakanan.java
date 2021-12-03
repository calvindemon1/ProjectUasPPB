package com.example.bangjenggotsproject_2019130014.Interface;

import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceMakanan {

    @GET("getAllMakanan.php")
    Call<ModelMakanan> getAllMakanan();

}
