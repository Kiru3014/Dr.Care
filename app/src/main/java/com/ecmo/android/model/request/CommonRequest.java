package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonRequest {


    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;

    public CommonRequest(String transactiontype, String sessionid) {
        this.transactiontype = transactiontype;
        this.sessid = sessionid;
    }
}
