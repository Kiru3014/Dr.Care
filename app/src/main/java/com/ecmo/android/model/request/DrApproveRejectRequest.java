package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrApproveRejectRequest {

    @SerializedName("docid")
    @Expose
    private String docid;
    @SerializedName("created_by")
    @Expose
    private String approvedBy;

    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;


    public DrApproveRejectRequest(String docid,String approvedby,String transactiontype, String sessionid) {
        this.transactiontype = transactiontype;
        this.sessid = sessionid;
        this.docid=docid;
        this.approvedBy=approvedby;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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

}
