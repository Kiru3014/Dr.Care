package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fairoze khazi on 08/04/2018.
 */

public class HospitalList {


    @SerializedName("Result")
    @Expose
    private String result;


    @SerializedName("data")
    @Expose
    private List<Hospitalitem> data;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Hospitalitem> getData() {
        return data;
    }

    public void setData(List<Hospitalitem> data) {
        this.data = data;
    }
}
