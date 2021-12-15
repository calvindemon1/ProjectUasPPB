package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterDine;
import com.example.bangjenggotsproject_2019130014.Adapter.AdapterDineData;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDine;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.Model.ResultDine;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity {
    private RecyclerView recycle_menu;
    private ApiInterfaceDine apiInterfaceDine;
    SharedPreferences sharedPreferences;
    public static final String my_shared_preferences = "my_session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recycle_menu = findViewById(R.id.recycle_datadine);
        Button btndelivery = findViewById(R.id.btndelivery);

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

        btndelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notification.this, DataDelivery.class);
                startActivity(intent);
            }
        });

        initApi();
        getAllDataDine();
    }

    private void initApi() {
        apiInterfaceDine = ApiClient.getClient().create(ApiInterfaceDine.class);
    }

    private void getAllDataDine(){
        int iduser = sharedPreferences.getInt("iduser",0);

        Call<ResultDine> callback = apiInterfaceDine.alldatadinebyid(iduser);
        callback.enqueue(new Callback<ResultDine>() {
            @Override
            public void onResponse(Call<ResultDine> call, Response<ResultDine> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        if (!response.body().getResult().isEmpty()) {
                            List<ModelDine> list_dine = response.body().getResult();
                            recycle_menu.setHasFixedSize(true);
                            recycle_menu.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
                            AdapterDineData adapterDineData = new AdapterDineData(getBaseContext(), list_dine, apiInterfaceDine);
                            recycle_menu.setAdapter(adapterDineData);
                            recycle_menu.setNestedScrollingEnabled(true);
                            adapterDineData.notifyDataSetChanged();
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
            public void onFailure(Call<ResultDine> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}