package com.ecmo.android.model.request;

import com.ecmo.android.utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fairoze khazi on 08/04/2018.
 */

public class HospitalReq {

    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;

    public HospitalReq(String transactiontype) {
        this.transactiontype = transactiontype;
        this.sessid = Constants.SESSIONID;
    }
}
