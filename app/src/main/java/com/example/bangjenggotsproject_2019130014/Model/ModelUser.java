package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

public class ModelUser {

    @SerializedName("id_user")
    private int id_user;

    @SerializedName("nama")
    private String nama;

    @SerializedName("username")
    private String username;

    @SerializedName("gender")
    private String gender;

    @SerializedName("password")
    private String password;

    @SerializedName("status")
    private int status;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
