package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterDine;
import com.example.bangjenggotsproject_2019130014.Adapter.AdapterMakanan;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDine;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceMakanan;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DineInAdmin extends AppCompatActivity {
    private RecyclerView recycle_menu;
    private ApiInterfaceDine apiInterfaceDine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dine_in_admin);

        recycle_menu = findViewById(R.id.recycle_dine);

        initApi();
        getAllDine();
    }

    private void initApi() {
        apiInterfaceDine = ApiClient.getClient().create(ApiInterfaceDine.class);
    }

    private void getAllDine() {
        Call<ModelDine> callback = apiInterfaceDine.alldatadine();
        callback.enqueue(new Callback<ModelDine>() {
            @Override
            public void onResponse(Call<ModelDine> call, Response<ModelDine> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        if (!response.body().getResult().isEmpty()) {
                            List<ModelDine> list_dine = response.body().getResult();
                            recycle_menu.setHasFixedSize(false);
                            recycle_menu.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                            AdapterDine adapterDine = new AdapterDine(getBaseContext(), list_dine);
                            recycle_menu.setAdapter(adapterDine);
                            recycle_menu.setNestedScrollingEnabled(true);
                        } else {
                            message("Data masih Kosong !");
                        }
                    } else {
                        message("Get Data Failed !");
                    }
                } else {
                    message("Response if Failed !");
                }
            }

            @Override
            public void onFailure(Call<ModelDine> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}