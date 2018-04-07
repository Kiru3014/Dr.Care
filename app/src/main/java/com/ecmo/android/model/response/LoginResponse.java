package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse
{
    @SerializedName("data")
    @Expose
    private LoginResponseData data;
    @SerializedName("Result")
    @Expose
    private String result;

    public LoginResponseData getData() {
        return data;
    }

    public void setData(LoginResponseData data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
