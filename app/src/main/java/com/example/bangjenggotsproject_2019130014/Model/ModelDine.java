package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelDine {
    @SerializedName("id_dine")
    private int id_dine;

    @SerializedName("id_user")
    private int id_user;

    @SerializedName("nama_reservasi")
    private String nama_reservasi;

    @SerializedName("tanggal_dine")
    private String tanggal_dine;

    @SerializedName("waktu_dine")
    private String waktu_dine;

    @SerializedName("status")
    private String status;

    public int getId_dine() {
        return id_dine;
    }

    public void setId_dine(int id_dine) {
        this.id_dine = id_dine;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_reservasi() {
        return nama_reservasi;
    }

    public void setNama_reservasi(String nama_reservasi) {
        this.nama_reservasi = nama_reservasi;
    }

    public String getTanggal_dine() {
        return tanggal_dine;
    }

    public void setTanggal_dine(String tanggal_dine) {
        this.tanggal_dine = tanggal_dine;
    }

    public String getWaktu_dine() {
        return waktu_dine;
    }

    public void setWaktu_dine(String waktu_dine) {
        this.waktu_dine = waktu_dine;
    }
}
