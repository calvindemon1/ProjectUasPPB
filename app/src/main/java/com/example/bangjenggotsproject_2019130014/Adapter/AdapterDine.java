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

import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDine;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDine extends RecyclerView.Adapter<AdapterDine.ViewHolder> {
    private final Context context;
    private final List<ModelDine> list_dine;
    private final ApiInterfaceDine apiInterfaceDine;

    public AdapterDine(Context context, List<ModelDine> list_dine, ApiInterfaceDine apiInterfaceDine) {
        this.context = context;
        this.list_dine = list_dine;
        this.apiInterfaceDine = apiInterfaceDine;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_dine_data, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDine.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_dine.setText(String.valueOf(list_dine.get(position).getId_dine()));
        holder.nama_reservasi.setText(list_dine.get(position).getNama_reservasi());
        holder.tanggal_dine.setText(list_dine.get(position).getTanggal_dine());
        holder.waktu_dine.setText(list_dine.get(position).getWaktu_dine());
        holder.status.setText(list_dine.get(position).getStatus());

        holder.btnterima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelDine md = new ModelDine();
                md.setId_dine(Integer.parseInt(holder.id_dine.getText().toString()));
                md.setNama_reservasi(holder.nama_reservasi.getText().toString());
                md.setTanggal_dine(holder.tanggal_dine.getText().toString());
                md.setWaktu_dine(holder.waktu_dine.getText().toString());
                md.setStatus("Diterima");
                updateDataDine(md, position);
            }
        });

        holder.btntolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelDine md = new ModelDine();
                md.setId_dine(Integer.parseInt(holder.id_dine.getText().toString()));
                md.setNama_reservasi(holder.nama_reservasi.getText().toString());
                md.setTanggal_dine(holder.tanggal_dine.getText().toString());
                md.setWaktu_dine(holder.waktu_dine.getText().toString());
                md.setStatus("Ditolak");
                updateDataDineTolak(md,position);
            }
        });
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
        private Button btnterima;
        private Button btntolak;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_dine = itemView.findViewById(R.id.id_dine);
            nama_reservasi = itemView.findViewById(R.id.nama_dine);
            tanggal_dine = itemView.findViewById(R.id.tanggal_dine);
            waktu_dine = itemView.findViewById(R.id.waktu_dine);
            status = itemView.findViewById(R.id.status_dine);
            btnterima = itemView.findViewById(R.id.btnterima);
            btntolak = itemView.findViewById(R.id.btntolak);
        }
    }

    private void updateDataDine(ModelDine md, final int i){
        Call<ModelDine> callback = apiInterfaceDine.updatedine(md);
        callback.enqueue(new Callback<ModelDine>() {
            @Override
            public void onResponse(Call<ModelDine> call, Response<ModelDine> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 200 && response.body() != null){
                        list_dine.remove(i);
                        notifyItemChanged(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDine> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    private void updateDataDineTolak(ModelDine md, final int i){
        Call<ModelDine> callback = apiInterfaceDine.updatedine(md);
        callback.enqueue(new Callback<ModelDine>() {
            @Override
            public void onResponse(Call<ModelDine> call, Response<ModelDine> response) {
                if (response.isSuccessful()) {
                    if(response.code() == 200 && response.body() != null){
                        list_dine.remove(i);
                        notifyItemChanged(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDine> call, Throwable t) {
                message(t.getMessage());
            }
        });
    }

    private void message(final String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
