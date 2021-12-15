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

import com.example.bangjenggotsproject_2019130014.Interface.ApiInterfaceDine;
import com.example.bangjenggotsproject_2019130014.Model.ModelDine;
import com.example.bangjenggotsproject_2019130014.R;

import java.util.List;

public class AdapterDineData extends RecyclerView.Adapter<AdapterDineData.ViewHolder> {
    private final Context context;
    private final List<ModelDine> list_dinedata;
    private final ApiInterfaceDine apiInterfaceDine;

    public AdapterDineData(Context context, List<ModelDine> list_dinedata, ApiInterfaceDine apiInterfaceDine) {
        this.context = context;
        this.list_dinedata = list_dinedata;
        this.apiInterfaceDine = apiInterfaceDine;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_dine_data, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDineData.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_dine1.setText(String.valueOf(list_dinedata.get(position).getId_dine()));
        holder.nama_reservasi1.setText(list_dinedata.get(position).getNama_reservasi());
        holder.tanggal_dine1.setText(list_dinedata.get(position).getTanggal_dine());
        holder.waktu_dine1.setText(list_dinedata.get(position).getWaktu_dine());
        holder.status1.setText(list_dinedata.get(position).getStatus());

        holder.btnok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_dinedata.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_dinedata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id_dine1;
        private TextView nama_reservasi1;
        private TextView tanggal_dine1;
        private TextView waktu_dine1;
        private TextView status1;
        private Button btnok1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_dine1 = itemView.findViewById(R.id.id_dine1);
            nama_reservasi1 = itemView.findViewById(R.id.nama_dine1);
            tanggal_dine1 = itemView.findViewById(R.id.tanggal_dine1);
            waktu_dine1 = itemView.findViewById(R.id.waktu_dine1);
            status1 = itemView.findViewById(R.id.status_dine1);
            btnok1 = itemView.findViewById(R.id.btnok1);
        }
    }
}
