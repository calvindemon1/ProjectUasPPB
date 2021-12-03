package com.example.bangjenggotsproject_2019130014.Model;

import com.google.gson.annotations.SerializedName;

public class ResultUser {
    @SerializedName("result")
    private ModelUser result;

    public ModelUser getResult() {
        return result;
    }

    public void setResult(ModelUser result) {
        this.result = result;
    }
}
