package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterMakanan;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceMakanan;
import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {
    private RecyclerView recycle_menu;
    private ApiInterfaceMakanan apiInterfaceMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recycle_menu = findViewById(R.id.recycle_menu_recomen);

        initApi();
        getAllMakanan();
    }

    private void initApi() {
        apiInterfaceMakanan = ApiClient.getClient().create(ApiInterfaceMakanan.class);
    }

    private void getAllMakanan() {
        Call<ModelMakanan> callback = apiInterfaceMakanan.getAllMakanan();
        callback.enqueue(new Callback<ModelMakanan>() {
            @Override
            public void onResponse(Call<ModelMakanan> call, Response<ModelMakanan> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        if (!response.body().getResult().isEmpty()) {
                            List<ModelMakanan> list_makanan = response.body().getResult();
                            recycle_menu.setHasFixedSize(false);
                            recycle_menu.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                            AdapterMakanan adapterMakanan = new AdapterMakanan(getBaseContext(), list_makanan);
                            recycle_menu.setAdapter(adapterMakanan);
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
            public void onFailure(Call<ModelMakanan> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}