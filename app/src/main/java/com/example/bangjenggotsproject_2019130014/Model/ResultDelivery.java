package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultDelivery {
    @SerializedName("result")
    private List<ModelDelivery> result;

    public List<ModelDelivery> getResult() {
        return result;
    }

    public void setResult(List<ModelDelivery> result) {
        this.result = result;
    }
}
