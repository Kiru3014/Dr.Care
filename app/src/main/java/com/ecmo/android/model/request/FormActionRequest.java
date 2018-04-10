package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormActionRequest {

    @SerializedName("sessid")
    @Expose
    private String sessid;

    @SerializedName("action")
    @Expose
    private String action;

    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;

    @SerializedName("refral_id")
    @Expose
    private String refral_id;

    @SerializedName("comment")
    @Expose
    private String comment;


    public FormActionRequest(String action, String formid,String comment, String transactiontype, String sessid) {
        this.action = action;
        this.refral_id = formid;
        this.transactiontype = transactiontype;
        this.sessid = sessid;
        this.comment=comment;
    }



    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getRefral_id() {
        return refral_id;
    }

    public void setRefral_id(String refral_id) {
        this.refral_id = refral_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
