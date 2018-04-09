package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseData
{
    @SerializedName("civilid")
    @Expose
    private Integer civilid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("hospital_id")
    @Expose
    private Integer hospitalId;
    @SerializedName("specialist_id")
    @Expose
    private Integer specialistId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("device_token")
    @Expose
    private Object deviceToken;
    @SerializedName("device_id")
    @Expose
    private Object deviceId;
    @SerializedName("created_by")
    @Expose
    private Object createdBy;
    @SerializedName("transactiontype")
    @Expose
    private Object transactiontype;
    @SerializedName("hospitalname")
    @Expose
    private String hospitalname;
    @SerializedName("specialist")
    @Expose
    private String specialist;
    @SerializedName("sessid")
    @Expose
    private String sessid;
    @SerializedName("docid")
    @Expose
    private Integer docid;

    public Integer getCivilid() {
        return civilid;
    }

    public void setCivilid(Integer civilid) {
        this.civilid = civilid;
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

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Object getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(Object deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(Object transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }
}
