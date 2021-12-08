package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDelivery;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDine;
import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delivery extends AppCompatActivity implements View.OnClickListener {
    private EditText txttanggaldelivery;
    private EditText txtwaktudelivery;
    private Button btntanggaldelivery;
    private Button btnwaktudelivery;
    private EditText namadelivery;
    private  EditText alamat;
    private  EditText pesanan;
    private ApiInterfaceDelivery apiInterfaceDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        namadelivery = findViewById(R.id.namadelivery);
        alamat = findViewById(R.id.alamatdelivery);
        pesanan = findViewById(R.id.pesanan);
        Button submitdelivery = findViewById(R.id.submitdelivery);
        txttanggaldelivery = findViewById(R.id.txttanggaldelivery);
        txtwaktudelivery = findViewById(R.id.txtwaktudelivery);
        btntanggaldelivery = findViewById(R.id.btntanggaldelivery);
        btnwaktudelivery = findViewById(R.id.btnwaktudelivery);

        btntanggaldelivery.setOnClickListener(this);
        btnwaktudelivery.setOnClickListener(this);

        submitdelivery.setOnClickListener(view -> Delivery());
        initApi();
    }

    private void initApi(){
        apiInterfaceDelivery = ApiClient.getClient().create(ApiInterfaceDelivery.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btntanggaldelivery:

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                txttanggaldelivery.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btnwaktudelivery:

                c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        txtwaktudelivery.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
                break;
        }
    }


    private void Delivery(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Memuat...");
        progressDialog.show();

        ModelDelivery model = new ModelDelivery();
        model.setNama_pemesan(namadelivery.getText().toString());
        model.setAlamat(alamat.getText().toString());
        model.setPesanan(pesanan.getText().toString());
        model.setTanggal_pesan(txttanggaldelivery.getText().toString());
        model.setWaktu_pesan(txtwaktudelivery.getText().toString());
        model.setStatus("Belum diproses");

        Call<ModelDelivery> callback = apiInterfaceDelivery.delivery(model);
        callback.enqueue(new Callback<ModelDelivery>() {
            @Override
            public void onResponse(Call<ModelDelivery> call, Response<ModelDelivery> response) {
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
            public void onFailure(Call<ModelDelivery> call, Throwable t) {
                message(t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}