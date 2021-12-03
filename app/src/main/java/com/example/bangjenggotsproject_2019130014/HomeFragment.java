package com.example.bangjenggotsproject_2019130014;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.bangjenggotsproject_2019130014.Adapter.AdapterMakanan;
import com.example.bangjenggotsproject_2019130014.ApiClient.ApiClient;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceMakanan;
import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recycle_menu_recomen;
    private ApiInterfaceMakanan apiInterfaceMakanan;
    private ImageView dinein;
    private ImageView delivery;
    private ImageView promo;
    private ImageView location;

    ViewFlipper flipper;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dinein = view.findViewById(R.id.dinein);
        delivery = view.findViewById(R.id.delivery);
        promo = view.findViewById(R.id.promo);
        location = view.findViewById(R.id.location);

        dinein.setOnClickListener(this);
        delivery.setOnClickListener(this);
        promo.setOnClickListener(this);
        location.setOnClickListener(this);

        recycle_menu_recomen = view.findViewById(R.id.recycle_menu_recomen);

        int gambar[] = {R.drawable.creamychickenmushroom, R.drawable.koreanhousechicken, R.drawable.sweetlimehoneychicken, R.drawable.roastedchickensambalmatah};

        flipper = view.findViewById(R.id.flipper);

        for(int gmb : gambar){
            gambarSlide(gmb);
        }

        initApi();
        getAllMakanan();

        return view;
    }

    public void gambarSlide(int gmbr){
        ImageView iv = new ImageView(requireActivity());
        iv.setBackgroundResource(gmbr);

        flipper.addView(iv);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(requireActivity(), android.R.anim.slide_in_left);
        flipper.setOutAnimation(requireActivity(), android.R.anim.slide_out_right);
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
                            recycle_menu_recomen.setHasFixedSize(false);
                            recycle_menu_recomen.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            AdapterMakanan adapterMakanan = new AdapterMakanan(getActivity(), list_makanan);
                            recycle_menu_recomen.setAdapter(adapterMakanan);
                            recycle_menu_recomen.setNestedScrollingEnabled(true);
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view == dinein){
            startActivity(new Intent(getActivity(),DineIn.class));
        }

        if(view == delivery){
            startActivity(new Intent(getActivity(),Delivery.class));
        }

        if(view == promo){
            startActivity(new Intent(getActivity(), Menu.class));
        }

        if(view == location){
            startActivity(new Intent(getActivity(),Location.class));
        }
    }
}