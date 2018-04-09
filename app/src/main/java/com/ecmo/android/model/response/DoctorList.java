package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DoctorList {
    @SerializedName("Result")
    @Expose
    private String Result;


    @SerializedName("data")
    @Expose
    private List<DocListItem> data;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public List<DocListItem> getData() {
        return data;
    }

    public void setData(List<DocListItem> data) {
        this.data = data;
    }
}
