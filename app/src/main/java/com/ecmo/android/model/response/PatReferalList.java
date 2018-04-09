package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatReferalList {



    @SerializedName("Result")
    @Expose
    private String Result;


    @SerializedName("data")
    @Expose
    private List<PatReferalListitem> data;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public List<PatReferalListitem> getData() {
        return data;
    }

    public void setData(List<PatReferalListitem> data) {
        this.data = data;
    }
}
