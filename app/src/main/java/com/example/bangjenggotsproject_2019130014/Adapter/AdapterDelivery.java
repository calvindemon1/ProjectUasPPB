package com.example.bangjenggotsproject_2019130014.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDelivery extends RecyclerView.Adapter<AdapterDelivery.ViewHolder> {
    private final Context context;
    private final List<ModelDelivery> list_delivery;
    private final ApiInterfaceDelivery apiInterfaceDelivery;

    public AdapterDelivery(Context context, List<ModelDelivery> list_delivery, ApiInterfaceDelivery apiInterfaceDelivery) {
        this.context = context;
        this.list_delivery = list_delivery;
        this.apiInterfaceDelivery = apiInterfaceDelivery;
    }

    private void updateDataDelivery(AdapterDelivery.ViewHolder holder, final int i){
        ModelDelivery md = new ModelDelivery();
        md.setId_delivery(Integer.parseInt(holder.id_delivery.getText().toString()));
        md.setNama_pemesan(holder.nama_pemesan.getText().toString());
        md.setAlamat(holder.alamat.getText().toString());
        md.setPesanan(holder.pesanan.getText().toString());
        md.setTanggal_pesan(holder.tanggal_pesan.getText().toString());
        md.setWaktu_pesan(holder.waktu_pesan.getText().toString());
        md.setStatus("Diterima");
        Call<ModelDelivery> callback = apiInterfaceDelivery.updatedelivery(md);
        callback.enqueue(new Callback<ModelDelivery>() {
            @Override
            public void onResponse(Call<ModelDelivery> call, Response<ModelDelivery> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 200 && response.body() != null){
                        list_delivery.remove(i);
                        notifyItemChanged(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDelivery> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    public void updateDeliveryTolak(AdapterDelivery.ViewHolder holder, final int i){
        ModelDelivery md = new ModelDelivery();
        md.setId_delivery(Integer.parseInt(holder.id_delivery.getText().toString()));
        md.setNama_pemesan(holder.nama_pemesan.getText().toString());
        md.setAlamat(holder.alamat.getText().toString());
        md.setPesanan(holder.pesanan.getText().toString());
        md.setTanggal_pesan(holder.tanggal_pesan.getText().toString());
        md.setWaktu_pesan(holder.waktu_pesan.getText().toString());
        md.setStatus("Ditolak");
        Call<ModelDelivery> callback = apiInterfaceDelivery.updatedelivery(md);
        callback.enqueue(new Callback<ModelDelivery>() {
            @Override
            public void onResponse(Call<ModelDelivery> call, Response<ModelDelivery> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 200 && response.body() != null){
                        list_delivery.remove(i);
                        notifyItemChanged(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDelivery> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_delivery, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDelivery.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_delivery.setText(String.valueOf(list_delivery.get(position).getId_delivery()));
        holder.nama_pemesan.setText(list_delivery.get(position).getNama_pemesan());
        holder.alamat.setText(list_delivery.get(position).getAlamat());
        holder.pesanan.setText(list_delivery.get(position).getPesanan());
        holder.tanggal_pesan.setText(list_delivery.get(position).getTanggal_pesan());
        holder.waktu_pesan.setText(list_delivery.get(position).getWaktu_pesan());
        holder.status.setText(list_delivery.get(position).getStatus());

        holder.btnterimad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataDelivery(holder, position);
            }
        });

        holder.btntolakd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDeliveryTolak(holder,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_delivery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id_delivery;
        private TextView nama_pemesan;
        private TextView alamat;
        private TextView pesanan;
        private TextView tanggal_pesan;
        private TextView waktu_pesan;
        private TextView status;
        private Button btnterimad;
        private Button btntolakd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_delivery = itemView.findViewById(R.id.id_delivery);
            nama_pemesan = itemView.findViewById(R.id.nama_pemesan);
            alamat = itemView.findViewById(R.id.alamat);
            pesanan = itemView.findViewById(R.id.pesanan);
            tanggal_pesan = itemView.findViewById(R.id.tanggal_pesan);
            waktu_pesan = itemView.findViewById(R.id.waktu_pesan);
            status = itemView.findViewById(R.id.status);
            btnterimad = itemView.findViewById(R.id.btnterimad);
            btntolakd = itemView.findViewById(R.id.btntolakd);
        }
    }
}
