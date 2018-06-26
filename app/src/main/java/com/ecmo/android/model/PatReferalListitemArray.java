package com.ecmo.android.model;

public class PatReferalListitemArray
{
    String referalformid;
    String status;
    String pat_name;
    String pat_hospital;
    String diagnosis;
    String pat_age;
    String refdate;
    String refresend;

    public PatReferalListitemArray(String referalformid, String status, String pat_name, String pat_hospital, String diagnosis, String pat_age, String refdate,String refresend) {
        this.referalformid = referalformid;
        this.status = status;
        this.pat_name = pat_name;
        this.pat_hospital = pat_hospital;
        this.diagnosis = diagnosis;
        this.pat_age = pat_age;
        this.refdate = refdate;
        this.refresend=refresend;
    }

    public String getReferalformid() {
        return referalformid;
    }

    public void setReferalformid(String referalformid) {
        this.referalformid = referalformid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
    }

    public String getPat_hospital() {
        return pat_hospital;
    }

    public void setPat_hospital(String pat_hospital) {
        this.pat_hospital = pat_hospital;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPat_age() {
        return pat_age;
    }

    public void setPat_age(String pat_age) {
        this.pat_age = pat_age;
    }

    public String getRefdate() {
        return refdate;
    }

    public void setRefdate(String refdate) {
        this.refdate = refdate;
    }

    public String getRefresend() {
        return refresend;
    }

    public void setRefresend(String refresend) {
        this.refresend = refresend;
    }
}
