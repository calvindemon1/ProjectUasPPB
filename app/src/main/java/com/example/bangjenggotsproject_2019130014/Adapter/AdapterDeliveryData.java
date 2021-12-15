package com.example.bangjenggotsproject_2019130014.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDelivery;
import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDine;
import com.example.bangjenggotsproject_2019130014.Model.ModelDelivery;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.R;

import java.util.List;

public class AdapterDeliveryData extends RecyclerView.Adapter<AdapterDeliveryData.ViewHolder>{
    private final Context context;
    private final List<ModelDelivery> list_deliverydata;
    private final ApiInterfaceDelivery apiInterfaceDelivery;

    public AdapterDeliveryData(Context context, List<ModelDelivery> list_deliverydata, ApiInterfaceDelivery apiInterfaceDelivery) {
        this.context = context;
        this.list_deliverydata = list_deliverydata;
        this.apiInterfaceDelivery = apiInterfaceDelivery;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_delivery_data, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDeliveryData.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_delivery1.setText(String.valueOf(list_deliverydata.get(position).getId_delivery()));
        holder.nama_pemesan1.setText(list_deliverydata.get(position).getNama_pemesan());
        holder.alamat_delivery1.setText(list_deliverydata.get(position).getAlamat());
        holder.pesanan_delivery1.setText(list_deliverydata.get(position).getPesanan());
        holder.tanggal_delivery1.setText(list_deliverydata.get(position).getTanggal_pesan());
        holder.waktu_delivery1.setText(list_deliverydata.get(position).getWaktu_pesan());
        holder.status2.setText(list_deliverydata.get(position).getStatus());

        holder.btnok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_deliverydata.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_deliverydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id_delivery1;
        private TextView nama_pemesan1;
        private TextView alamat_delivery1;
        private TextView pesanan_delivery1;
        private TextView tanggal_delivery1;
        private TextView waktu_delivery1;
        private TextView status2;
        private Button btnok2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_delivery1 = itemView.findViewById(R.id.id_delivery1);
            nama_pemesan1 = itemView.findViewById(R.id.nama_pemesan1);
            alamat_delivery1 = itemView.findViewById(R.id.alamat_delivery1);
            pesanan_delivery1 = itemView.findViewById(R.id.pesanan_delivery1);
            tanggal_delivery1 = itemView.findViewById(R.id.tanggal_delivery1);
            waktu_delivery1 = itemView.findViewById(R.id.waktu_delivery1);
            status2 = itemView.findViewById(R.id.status2);
            btnok2 = itemView.findViewById(R.id.btnok2);
        }
    }
}
