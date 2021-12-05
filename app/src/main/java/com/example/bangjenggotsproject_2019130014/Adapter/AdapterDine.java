package com.example.bangjenggotsproject_2019130014.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.R;

import java.util.List;

public class AdapterDine extends RecyclerView.Adapter<AdapterDine.ViewHolder> {
    private final Context context;
    private final List<ModelDine> list_dine;

    public AdapterDine(Context context, List<ModelDine> list_dine) {
        this.context = context;
        this.list_dine = list_dine;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_dine_in, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDine.ViewHolder holder, int position) {
        holder.id_dine.setText(list_dine.get(position).getId_dine());
        holder.nama_reservasi.setText(list_dine.get(position).getNama_reservasi());
        holder.tanggal_dine.setText(list_dine.get(position).getTanggal_dine());
        holder.waktu_dine.setText(list_dine.get(position).getWaktu_dine());
        holder.status.setText(list_dine.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list_dine.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id_dine;
        private TextView nama_reservasi;
        private TextView tanggal_dine;
        private TextView waktu_dine;
        private TextView status;
        private Button btndine;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_dine = itemView.findViewById(R.id.id_dine);
            nama_reservasi = itemView.findViewById(R.id.nama_dine);
            tanggal_dine = itemView.findViewById(R.id.tanggal_dine);
            waktu_dine = itemView.findViewById(R.id.waktu_dine);
            status = itemView.findViewById(R.id.status_dine);
            btndine = itemView.findViewById(R.id.btndine);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
