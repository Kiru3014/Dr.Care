package com.ecmo.android.model.request;

import com.ecmo.android.utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgentsReq
{
    @SerializedName("actiontype")
    @Expose
    private String actiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;

    public AgentsReq(String transactiontype) {
        this.actiontype = transactiontype;
        this.sessid = Constants.SESSIONID;
    }
}
