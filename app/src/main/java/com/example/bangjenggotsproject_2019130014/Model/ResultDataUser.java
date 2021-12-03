package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultDataUser {
    @SerializedName("result")
    private List<ModelUser> result;

    public List<ModelUser> getResult() {
        return result;
    }

    public void setResult(List<ModelUser> result) {
        this.result = result;
    }
}
