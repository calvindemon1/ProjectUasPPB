package com.example.bangjenggotsproject_2019130014.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.R;

import java.util.List;

public class AdapterDelivery extends RecyclerView.Adapter<AdapterDelivery.ViewHolder> {
    private final Context context;
    private final List<ModelDelivery> list_delivery;


    public AdapterDelivery(Context context, List<ModelDelivery> list_delivery) {
        this.context = context;
        this.list_delivery = list_delivery;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_delivery, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDelivery.ViewHolder holder, int position) {
        holder.id_delivery.setText(list_delivery.get(position).getId_delivery());
        holder.nama_pemesan.setText(list_delivery.get(position).getNama_pemesan());
        holder.alamat.setText(list_delivery.get(position).getAlamat());
        holder.pesanan.setText(list_delivery.get(position).getPesanan());
        holder.tanggal_pesan.setText(list_delivery.get(position).getTanggal_pesan());
        holder.waktu_pesan.setText(list_delivery.get(position).getWaktu_pesan());
        holder.status.setText(list_delivery.get(position).getStatus());
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_delivery = itemView.findViewById(R.id.id_delivery);
            nama_pemesan = itemView.findViewById(R.id.nama_delivery);
            alamat = itemView.findViewById(R.id.alamat_delivery);
            pesanan = itemView.findViewById(R.id.pesanan);
            tanggal_pesan = itemView.findViewById(R.id.tanggal_delivery);
            waktu_pesan = itemView.findViewById(R.id.waktu_delivery);
            status = itemView.findViewById(R.id.status_delivery);
        }
    }
}
