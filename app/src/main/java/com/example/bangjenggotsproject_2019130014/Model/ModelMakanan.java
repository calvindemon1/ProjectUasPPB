package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelMakanan {

    @SerializedName("result")
    private List<ModelMakanan> result;

    @SerializedName("id_makanan")
    private Integer id_makanan;

    @SerializedName("nama_makanan")
    private String nama_makanan;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("gambar")
    private String gambar;

    @SerializedName("harga_makanan")
    private Double harga_makanan;

    public List<ModelMakanan> getResult() {
        return result;
    }

    public void setResult(List<ModelMakanan> result) {
        this.result = result;
    }

    public Integer getId_makanan() {
        return id_makanan;
    }

    public void setId_makanan(Integer id_makanan) {
        this.id_makanan = id_makanan;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getDescription() {
        return deskripsi;
    }

    public void setDescription(String description) {
        this.deskripsi = description;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Double getHarga_makanan() {
        return harga_makanan;
    }

    public void setHarga_makanan(Double harga_makanan) {
        this.harga_makanan = harga_makanan;
    }
}
