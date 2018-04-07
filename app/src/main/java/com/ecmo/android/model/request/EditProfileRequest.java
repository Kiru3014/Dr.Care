package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fairoze khazi on 07/04/2018.
 */

public class EditProfileRequest {

    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("phone")
    @Expose
    String phone;
    @SerializedName("hospital_id")
    @Expose
    String hospital_id;
    @SerializedName("specialist_id")
    @Expose
    String specialist_id;
    @SerializedName("transactiontype")
    @Expose
    String transactiontype;
    @SerializedName("docid")
    @Expose
    String docid;
    @SerializedName("sessid")
    @Expose
    String sessid;


    public EditProfileRequest( String name,String email, String phone,String hospital_id,String specialist_id,String docid, String transactiontype, String sessid) {
        this.name=name;
        this.email = email;
        this.phone = phone;
        this.hospital_id=hospital_id;
        this.specialist_id=specialist_id;
        this.docid=docid;
        this.transactiontype = transactiontype;
        this.sessid = sessid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getSpecialist_id() {
        return specialist_id;
    }

    public void setSpecialist_id(String specialist_id) {
        this.specialist_id = specialist_id;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }
}
