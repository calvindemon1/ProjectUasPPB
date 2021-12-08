package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultDine {
    @SerializedName("result")
    private List<ModelDine> result;

    public List<ModelDine> getResult() {
        return result;
    }

    public void setResult(List<ModelDine> result) {
        this.result = result;
    }
}
