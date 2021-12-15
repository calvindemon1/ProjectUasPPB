package com.example.bangjenggotsproject_2019130014;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceUser;
import com.example.bangjenggotsproject_2019130014.Model.ModelUser;
import com.example.bangjenggotsproject_2019130014.Model.ResultUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private ApiInterfaceUser apiInterfaceUser;
    private EditText username_login;
    private EditText password_login;

    SharedPreferences   sharedPreferences;
    public static final String my_shared_preferences = "my_session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_login = findViewById(R.id.username_login);
        password_login = findViewById(R.id.password_login);

        Button register = findViewById(R.id.btnRegister);
        Button login = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

        if(sharedPreferences.getInt("key", 0) != 0){
            if(sharedPreferences.getInt("key", 0) == 1){
                Intent intent = new Intent(Login.this, MainMenuAdmin.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(Login.this, MainMenu.class);
                startActivity(intent);
                finish();
            }

        }

        login.setOnClickListener(view -> loginApi());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Register.class));
            }
        });
        initApi();
    }

    private void initApi(){
        apiInterfaceUser = ApiClient.getClient().create(ApiInterfaceUser.class);
    }

    private void loginApi(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Memuat...");
        progressDialog.show();

        ModelUser model = new ModelUser();
        model.setUsername(username_login.getText().toString());
        model.setPassword(password_login.getText().toString());

        Call<ResultUser> callback = apiInterfaceUser.loginUser(model);
        callback.enqueue(new Callback<ResultUser>() {
            @Override
            public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("iduser",response.body().getResult().getId_user());
                        editor.putInt("key", response.body().getResult().getStatus());
                        editor.apply();

                        progressDialog.dismiss();

                        if(response.body().getResult().getStatus()==1){
                            Intent login1 = new Intent(getBaseContext(), MainMenuAdmin.class);
                            startActivity(login1);
                            finish();
                        }else{
                            Intent login2 = new Intent(getBaseContext(), MainMenu.class);
                            startActivity(login2);
                            finish();
                        }
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
            public void onFailure(Call<ResultUser> call, Throwable t) {
                message(t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}