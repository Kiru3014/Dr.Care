package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest
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
    private String password;
    @SerializedName("hospital_id")
    @Expose
    private Integer hospitalId;
    @SerializedName("specialist_id")
    @Expose
    private Integer specialistId;
    @SerializedName("user_type")
    @Expose
    private Integer userType;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;

    public RegisterRequest(String civilid, String name, String email, String phone, String password, Integer hospitalId, Integer specialistId, Integer userType, String deviceToken, String deviceId, Integer createdBy, String transactiontype, String sessid) {
        this.civilid = Integer.parseInt(civilid);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.hospitalId = hospitalId;
        this.specialistId = specialistId;
        this.userType = userType;
        this.deviceToken = deviceToken;
        this.deviceId = deviceId;
        this.createdBy = createdBy;
        this.transactiontype = transactiontype;
        this.sessid = sessid;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
