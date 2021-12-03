package com.example.bangjenggotsproject_2019130014.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangjenggotsproject_2019130014.Model.ModelMakanan;
import com.example.bangjenggotsproject_2019130014.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.zip.Inflater;

public class AdapterMakanan extends RecyclerView.Adapter<AdapterMakanan.ViewHolder> {

    private final Context context;
    private final List<ModelMakanan> list_makanan;

    public AdapterMakanan(Context context, List<ModelMakanan> list_makanan) {
        this.context = context;
        this.list_makanan = list_makanan;
    }

    @NonNull
    @Override
    public AdapterMakanan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_top_recomend, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMakanan.ViewHolder holder, int position) {
        String url = list_makanan.get(position).getGambar();
        if (!url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .into(holder.gmbr_rekom);
        }

        holder.nama_makanan.setText(list_makanan.get(position).getNama_makanan());
        Double harga_makanan = list_makanan.get(position).getHarga_makanan();
        final String formatHarga = NumberFormat.getInstance().format(harga_makanan);
        holder.harga_makanan.setText("Rp. " + formatHarga);
        holder.deskripsi.setText(list_makanan.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list_makanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gmbr_rekom;
        private TextView nama_makanan;
        private TextView harga_makanan;
        private TextView deskripsi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gmbr_rekom = itemView.findViewById(R.id.gmbr_rekom);
            nama_makanan = itemView.findViewById(R.id.nama_makanan);
            harga_makanan = itemView.findViewById(R.id.harga_makanan);
            deskripsi = itemView.findViewById(R.id.deskripsi);
        }
    }
}
