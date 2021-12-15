package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterDelivery;
import com.example.bangjenggotsproject_2019130014.Adapter.AdapterMakanan;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDelivery;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceMakanan;
import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;
import com.example.bangjenggotsproject_2019130014.Model.ResultDelivery;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryAdmin extends AppCompatActivity {
    private RecyclerView recycle_menu;
    private ApiInterfaceDelivery apiInterfaceDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_admin);
        recycle_menu = findViewById(R.id.recycle_delivery);

        initApi();
        getAllDelivery();
    }

    private void initApi() {
        apiInterfaceDelivery = ApiClient.getClient().create(ApiInterfaceDelivery.class);
    }

    private void getAllDelivery() {
        Call<ResultDelivery> callback = apiInterfaceDelivery.alldatadelivery();
        callback.enqueue(new Callback<ResultDelivery>() {
            @Override
            public void onResponse(Call<ResultDelivery> call, Response<ResultDelivery> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        if (!response.body().getResult().isEmpty()) {
                            List<ModelDelivery> list_delivery = response.body().getResult();
                            recycle_menu.setHasFixedSize(false);
                            recycle_menu.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                            AdapterDelivery adapterDelivery = new AdapterDelivery(getBaseContext(), list_delivery, apiInterfaceDelivery);
                            recycle_menu.setAdapter(adapterDelivery);
                            recycle_menu.setNestedScrollingEnabled(true);
                            adapterDelivery.notifyDataSetChanged();
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
            public void onFailure(Call<ResultDelivery> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}