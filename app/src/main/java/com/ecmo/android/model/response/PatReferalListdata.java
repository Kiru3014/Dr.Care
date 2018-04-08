package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fairoze khazi on 08/04/2018.
 */

public class PatReferalListdata {

    @SerializedName("referalformid")
    @Expose
    private String referalformid;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("Pat_name")
    @Expose
    private String Pat_name;

    @SerializedName("Pat_Hospital")
    @Expose
    private String Pat_Hospital;

    @SerializedName("refdate")
    @Expose
    private String refdate;

    @SerializedName("diagnosis")
    @Expose
    private String diagnosis;


    @SerializedName("pat_age")
    @Expose
    private String pat_age;

    public String getReferalformid() {
        return referalformid;
    }

    public void setReferalformid(String referalformid) {
        this.referalformid = referalformid;
    }

    public String getPat_age() {
        return pat_age;
    }

    public void setPat_age(String pat_age) {
        this.pat_age = pat_age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPat_name() {
        return Pat_name;
    }

    public void setPat_name(String pat_name) {
        Pat_name = pat_name;
    }

    public String getPat_Hospital() {
        return Pat_Hospital;
    }

    public void setPat_Hospital(String pat_Hospital) {
        Pat_Hospital = pat_Hospital;
    }

    public String getRefdate() {
        return refdate;
    }

    public void setRefdate(String refdate) {
        this.refdate = refdate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
