package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDine;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceUser;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.Model.ModelUser;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DineIn extends AppCompatActivity implements View.OnClickListener{
    private EditText txttanggaldine;
    private EditText txtwaktudine;
    private Button btntanggaldine;
    private Button btnwaktudine;
    private EditText namadine;
    private ApiInterfaceDine apiInterfaceDine;
    SharedPreferences   sharedPreferences;
    public static final String my_shared_preferences = "my_session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dine_in);


        namadine = findViewById(R.id.namadine);
        Button submitdine = findViewById(R.id.submitdine);
        txttanggaldine = findViewById(R.id.txttanggaldine);
        txtwaktudine = findViewById(R.id.txtwaktudine);
        btntanggaldine = findViewById(R.id.btntanggaldine);
        btnwaktudine = findViewById(R.id.btnwaktudine);

        btntanggaldine.setOnClickListener(this);
        btnwaktudine.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

            submitdine.setOnClickListener(view -> DineIn());
            initApi();
        }

        private void initApi(){
            apiInterfaceDine = ApiClient.getClient().create(ApiInterfaceDine.class);
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btntanggaldine:

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                txttanggaldine.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btnwaktudine:

                c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                txtwaktudine.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
        }
    }


    private void DineIn(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Memuat...");
        progressDialog.show();

        ModelDine model = new ModelDine();

        int iduser = sharedPreferences.getInt("iduser",0);

        model.setId_user(iduser);
        model.setNama_reservasi(namadine.getText().toString());
        model.setTanggal_dine(txttanggaldine.getText().toString());
        model.setWaktu_dine(txtwaktudine.getText().toString());

        Call<ModelDine> callback = apiInterfaceDine.dinein(model);
        callback.enqueue(new Callback<ModelDine>() {
            @Override
            public void onResponse(Call<ModelDine> call, Response<ModelDine> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {

                        progressDialog.dismiss();

                        onBackPressed();
                    } else {
                        message("Get Data Failed !");
                        progressDialog.dismiss();
                    }
                } else {
                    message("Response if Failed !");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ModelDine> call, Throwable t) {
                message(t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}