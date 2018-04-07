package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fairoze khazi on 07/04/2018.
 */

public class ChangepasswordRequest {

    @SerializedName("oldpassword")
    @Expose
    String oldpassword;
    @SerializedName("newpassword")
    @Expose
    String newpassword;
    @SerializedName("docid")
    @Expose
    String docid;
    @SerializedName("transactiontype")
    @Expose
    String transactiontype;

    @SerializedName("sessid")
    @Expose
    String sessid;




    public ChangepasswordRequest(String oldpassword, String newpassword,String docid, String transactiontype, String sessid) {
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.docid=docid;
        this.transactiontype = transactiontype;
        this.sessid = sessid;
    }





    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
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
