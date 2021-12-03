package com.example.bangjenggotsproject_2019130014;

import static com.example.bangjenggotsproject_2019130014.Login.my_shared_preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterMakanan;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceUser;
import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;
import com.example.bangjenggotsproject_2019130014.Model.ModelUser;
import com.example.bangjenggotsproject_2019130014.Model.ResultDataUser;

import java.util.List;

public class Account extends Fragment {
    private ApiInterfaceUser apiInterfaceUser;
    SharedPreferences sharedPreferences;
    public static final String my_shared_preferences = "my_session";

    public Account() {
        // Required empty public constructor
    }
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        TextView nama_acc = view.findViewById(R.id.nama_acc);
        TextView username_acc = view.findViewById(R.id.username_acc);
        TextView gender_acc = view.findViewById(R.id.gender_acc);

        sharedPreferences = requireActivity().getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

        final String iduser = sharedPreferences.getString("key",null);

        initApi();

        Call<ResultDataUser> callback = apiInterfaceUser.getUser(Integer.parseInt(iduser));
        callback.enqueue(new Callback<ResultDataUser>() {
            @Override
            public void onResponse(Call<ResultDataUser> call, Response<ResultDataUser> response) {

                if (response.isSuccessful()) {
                    if (response.code() == 200 && response.body() != null) {
                        nama_acc.setText(response.body().getResult().get(0).getNama());
                        username_acc.setText(response.body().getResult().get(0).getUsername());
                        gender_acc.setText(response.body().getResult().get(0).getGender());
                    } else {
                        message("Get Data Failed !");
                    }
                } else {
                    message("Response if Failed !");
                }
            }

            @Override
            public void onFailure(Call<ResultDataUser> call, Throwable t) {
                message(t.getMessage());
            }
        });

        return view;
    }

    private void initApi(){
        apiInterfaceUser = ApiClient.getClient().create(ApiInterfaceUser.class);
    }

    private void message(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}