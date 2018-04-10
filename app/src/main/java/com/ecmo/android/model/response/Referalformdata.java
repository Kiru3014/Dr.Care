package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Referalformdata {

    @SerializedName("data")
    @Expose
    private List<referalformResponse> data;
    @SerializedName("Result")
    @Expose
    private String result;

    public referalformResponse getData() {
        if(data!=null && data.size()>0)
            return data.get(0);
        else
            return null;
    }

    public void setData(referalformResponse data) {
        this.data.add( data);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
