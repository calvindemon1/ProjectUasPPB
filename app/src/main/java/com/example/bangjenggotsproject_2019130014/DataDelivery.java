package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterDelivery;
import com.example.bangjenggotsproject_2019130014.Adapter.AdapterDeliveryData;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ResultDelivery;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDelivery extends AppCompatActivity {
    private RecyclerView recycle_menu;
    private ApiInterfaceDelivery apiInterfaceDelivery;
    SharedPreferences sharedPreferences;
    public static final String my_shared_preferences = "my_session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_delivery);

        recycle_menu = findViewById(R.id.recycle_datadelivery);

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

        initApi();
        getAllDataDelivery();
    }

    private void initApi() {
        apiInterfaceDelivery = ApiClient.getClient().create(ApiInterfaceDelivery.class);
    }

    private void getAllDataDelivery(){
        int iduser = sharedPreferences.getInt("iduser",0);

        Call<ResultDelivery> callback = apiInterfaceDelivery.alldatadeliverybyid(iduser);
        callback.enqueue(new Callback<ResultDelivery>() {
            @Override
            public void onResponse(Call<ResultDelivery> call, Response<ResultDelivery> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        if (!response.body().getResult().isEmpty()) {
                            List<ModelDelivery> list_deliverydata = response.body().getResult();
                            recycle_menu.setHasFixedSize(true);
                            recycle_menu.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                            AdapterDeliveryData adapterDeliveryData = new AdapterDeliveryData(getBaseContext(), list_deliverydata, apiInterfaceDelivery);
                            recycle_menu.setAdapter(adapterDeliveryData);
                            recycle_menu.setNestedScrollingEnabled(true);
                            adapterDeliveryData.notifyDataSetChanged();
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