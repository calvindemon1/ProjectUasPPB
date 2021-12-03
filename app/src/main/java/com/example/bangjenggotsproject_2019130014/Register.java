package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterMakanan;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceUser;
import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;
import com.example.bangjenggotsproject_2019130014.Model.ModelUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private ApiInterfaceUser apiInterfaceUser;
    private EditText nama_user;
    private EditText username;
    private RadioGroup groupgender;
    private RadioButton male;
    private RadioButton female;
    private EditText password;
    private EditText repassword;
    private Button register;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama_user = findViewById(R.id.nama_user);
        username = findViewById(R.id.username);
        groupgender = findViewById(R.id.groupgender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        register = findViewById(R.id.btnRegister);

        groupgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.male){
                    gender = male.getText().toString();
                }
                if(i == R.id.female){
                    gender = female.getText().toString();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerApi();
            }
        });
        initApi();
    }

    private boolean confirmPassword(){
        if(password.getText().toString().equals(repassword.getText().toString())){
            return true;
        } else {
            repassword.setError("Password didn't match!");
            return false;
        }
    }

    private void initApi(){
        apiInterfaceUser = ApiClient.getClient().create(ApiInterfaceUser.class);
    }

    private void registerApi(){
        if(confirmPassword()){
            ModelUser model = new ModelUser();
            model.setNama(nama_user.getText().toString());
            model.setUsername(username.getText().toString());
            model.setGender(gender);
            model.setPassword(password.getText().toString());

            Call<ModelUser> callback = apiInterfaceUser.registerUser(model);
            callback.enqueue(new Callback<ModelUser>() {
                @Override
                public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200 && response.body() != null) {
                            startActivity(new Intent(getBaseContext(), MainMenu.class));
                            finish();
                        } else {
                            message("Get Data Failed !");
                        }
                    } else {
                        message("Response if Failed !");
                    }
                }

                @Override
                public void onFailure(Call<ModelUser> call, Throwable t) {
                    message(t.getMessage());
                }
            });
        }
    }

    private void message(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}