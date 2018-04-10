package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormviewRequest {

//    {
//        "transactiontype": "getpatientinfo",
//            "sessid": "6D04B637-04BA-41CD-BFFE-FF51F24781C9",
//            "refral_id":"1"
//    }

    @SerializedName("refral_id")
    @Expose
    private String refral_id;

    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;

    public FormviewRequest(String transactiontype, String sessionid, String formid) {
        this.transactiontype = transactiontype;
        this.sessid = sessionid;
        this.refral_id=formid;
    }
}
