package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest
{
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;

    public LoginRequest(String email, String password, String transactiontype, String sessid, String pushwooshToken) {
        this.email = email;
        this.password = password;
        this.transactiontype = transactiontype;
        this.sessid = sessid;
        this.deviceToken=pushwooshToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
