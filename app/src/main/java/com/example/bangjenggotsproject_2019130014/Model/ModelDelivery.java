package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelDelivery {
    @SerializedName("id_delivery")
    private Integer id_delivery;

    @SerializedName("nama_pemesan")
    private String nama_pemesan;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("pesanan")
    private String pesanan;

    @SerializedName("tanggal_pesan")
    private String tanggal_pesan;

    @SerializedName("waktu_pesan")
    private String waktu_pesan;

    @SerializedName("status")
    private String status;

    public String getNama_pemesan() {
        return nama_pemesan;
    }

    public void setNama_pemesan(String nama_pemesan) {
        this.nama_pemesan = nama_pemesan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPesanan() {
        return pesanan;
    }

    public void setPesanan(String pesanan) {
        this.pesanan = pesanan;
    }

    public String getTanggal_pesan() {
        return tanggal_pesan;
    }

    public void setTanggal_pesan(String tanggal_pesan) {
        this.tanggal_pesan = tanggal_pesan;
    }

    public String getWaktu_pesan() {
        return waktu_pesan;
    }

    public void setWaktu_pesan(String waktu_pesan) {
        this.waktu_pesan = waktu_pesan;
    }

    public Integer getId_delivery() {
        return id_delivery;
    }

    public void setId_delivery(Integer id_delivery) {
        this.id_delivery = id_delivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
