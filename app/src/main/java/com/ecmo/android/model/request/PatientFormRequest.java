package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PatientFormRequest implements Serializable
{
    @SerializedName("referHospitalId")
    @Expose
    private String referHospitalId;
    @SerializedName("speciallistId")
    @Expose
    private String speciallistId;
    @SerializedName("docId")
    @Expose
    private String docId;
    @SerializedName("referConsulutantName")
    @Expose
    private String referConsulutantName;
    @SerializedName("admissionDiagnosis")
    @Expose
    private String admissionDiagnosis;
    @SerializedName("patientName")
    @Expose
    private String patientName;
    @SerializedName("civilId")
    @Expose
    private String civilId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("ward")
    @Expose
    private String ward;
    @SerializedName("bed")
    @Expose
    private String bed;
    @SerializedName("fileNo")
    @Expose
    private String fileNo;
    @SerializedName("preMorbFunctionalStatus")
    @Expose
    private String preMorbFunctionalStatus;
    @SerializedName("preMorbFunctionalConsciousStatus")
    @Expose
    private String preMorbFunctionalConsciousStatus;
    @SerializedName("e")
    @Expose
    private String e;
    @SerializedName("v")
    @Expose
    private String v;
    @SerializedName("m")
    @Expose
    private String m;
    @SerializedName("totalscore")
    @Expose
    private String totalscore;
    @SerializedName("durOfConventianalMechanicalVentination")
    @Expose
    private String durOfConventianalMechanicalVentination;
    @SerializedName("spO2")
    @Expose
    private String spO2;
    @SerializedName("pO2")
    @Expose
    private String pO2;
    @SerializedName("fiO2")
    @Expose
    private String fiO2;
    @SerializedName("pao2fio2ratio")
    @Expose
    private String pao2fio2ratio;
    @SerializedName("pip")
    @Expose
    private String pip;
    @SerializedName("peep")
    @Expose
    private String peep;
    @SerializedName("tv")
    @Expose
    private String tv;
    @SerializedName("rr")
    @Expose
    private String rr;
    @SerializedName("lungCompliance")
    @Expose
    private String lungCompliance;
    @SerializedName("cxrquadrants")
    @Expose
    private String cxrquadrants;
    @SerializedName("hr")
    @Expose
    private String hr;
    @SerializedName("bp")
    @Expose
    private String bp;
    @SerializedName("cvp")
    @Expose
    private String cvp;
    @SerializedName("temp")
    @Expose
    private String temp;
    @SerializedName("co")
    @Expose
    private String co;
    @SerializedName("cardiacindex")
    @Expose
    private String cardiacindex;
    @SerializedName("leftventricularejectionfraction")
    @Expose
    private String leftventricularejectionfraction;
    @SerializedName("Inotropesagent1")
    @Expose
    private String inotropesagent1;
    @SerializedName("Inotropesdose1")
    @Expose
    private String inotropesdose1;
    @SerializedName("Inotropesagent2")
    @Expose
    private String inotropesagent2;
    @SerializedName("Inotropesdose2")
    @Expose
    private String inotropesdose2;
    @SerializedName("Inotropesagent3")
    @Expose
    private String inotropesagent3;
    @SerializedName("Inotropesdose3")
    @Expose
    private String inotropesdose3;
    @SerializedName("Sedationagent1")
    @Expose
    private String sedationagent1;
    @SerializedName("Sedationdose1")
    @Expose
    private String sedationdose1;
    @SerializedName("Sedationagent2")
    @Expose
    private String sedationagent2;
    @SerializedName("Sedationdose2")
    @Expose
    private String sedationdose2;
    @SerializedName("Sedationagent3")
    @Expose
    private String sedationagent3;
    @SerializedName("Sedationdose3")
    @Expose
    private String sedationdose3;
    @SerializedName("musclerelaxantsagent1")
    @Expose
    private String musclerelaxantsagent1;
    @SerializedName("musclerelaxantsdose1")
    @Expose
    private String musclerelaxantsdose1;
    @SerializedName("urea")
    @Expose
    private String urea;
    @SerializedName("cr")
    @Expose
    private String cr;
    @SerializedName("lactate")
    @Expose
    private String lactate;
    @SerializedName("uo")
    @Expose
    private String uo;
    @SerializedName("dialysis")
    @Expose
    private String dialysis;
    @SerializedName("bloodgasPH")
    @Expose
    private String bloodgasPH;
    @SerializedName("bloodgasPO2")
    @Expose
    private String bloodgasPO2;
    @SerializedName("bloodgasPCO2")
    @Expose
    private String bloodgasPCO2;
    @SerializedName("bloodgasHCO3")
    @Expose
    private String bloodgasHCO3;
    @SerializedName("bloodgasBE")
    @Expose
    private String bloodgasBE;
    @SerializedName("refdocdesignation")
    @Expose
    private String refdocdesignation;
    @SerializedName("refdoctelephone")
    @Expose
    private String refdoctelephone;
    @SerializedName("history")
    @Expose
    private String history;
    @SerializedName("transactiontype")
    @Expose
    private String transactiontype;
    @SerializedName("sessid")
    @Expose
    private String sessid;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("resend")
    @Expose
    private String mResend;
    @SerializedName("murrayscore")
    @Expose
    private String murrayscore;

    public PatientFormRequest(String referHospitalId, String speciallistId, String docId, String referConsulutantName, String admissionDiagnosis, String patientName, String civilId, String gender, String unit, String ward, String bed, String fileNo, String preMorbFunctionalStatus, String preMorbFunctionalConsciousStatus, String e, String v, String m, String totalscore, String durOfConventianalMechanicalVentination, String spO2, String pO2, String fiO2, String pao2fio2ratio, String pip, String peep, String tv, String rr, String lungCompliance, String cxrquadrants, String hr, String bp, String cvp, String temp, String co, String cardiacindex, String leftventricularejectionfraction, String inotropesagent1, String inotropesdose1, String inotropesagent2, String inotropesdose2, String inotropesagent3, String inotropesdose3, String sedationagent1, String sedationdose1, String sedationagent2, String sedationdose2, String sedationagent3, String sedationdose3, String musclerelaxantsagent1, String musclerelaxantsdose1, String urea, String cr, String lactate, String uo, String dialysis, String bloodgasPH, String bloodgasPO2, String bloodgasPCO2, String bloodgasHCO3, String bloodgasBE, String refdocdesignation, String refdoctelephone, String history, String transactiontype, String sessid,String age,String mresend,String murrayscore)
    {
        this.referHospitalId = referHospitalId;
        this.speciallistId = speciallistId;
        this.docId = docId;
        this.referConsulutantName = referConsulutantName;
        this.admissionDiagnosis = admissionDiagnosis;
        this.patientName = patientName;
        this.civilId = civilId;
        this.gender = gender;
        this.unit = unit;
        this.ward = ward;
        this.bed = bed;
        this.fileNo = fileNo;
        this.preMorbFunctionalStatus = preMorbFunctionalStatus;
        this.preMorbFunctionalConsciousStatus = preMorbFunctionalConsciousStatus;
        this.e = e;
        this.v = v;
        this.m = m;
        this.totalscore = totalscore;
        this.durOfConventianalMechanicalVentination = durOfConventianalMechanicalVentination;
        this.spO2 = spO2;
        this.pO2 = pO2;
        this.fiO2 = fiO2;
        this.pao2fio2ratio = pao2fio2ratio;
        this.pip = pip;
        this.peep = peep;
        this.tv = tv;
        this.rr = rr;
        this.lungCompliance = lungCompliance;
        this.cxrquadrants = cxrquadrants;
        this.hr = hr;
        this.bp = bp;
        this.cvp = cvp;
        this.temp = temp;
        this.co = co;
        this.cardiacindex = cardiacindex;
        this.leftventricularejectionfraction = leftventricularejectionfraction;
        this.inotropesagent1 = inotropesagent1;
        this.inotropesdose1 = inotropesdose1;
        this.inotropesagent2 = inotropesagent2;
        this.inotropesdose2 = inotropesdose2;
        this.inotropesagent3 = inotropesagent3;
        this.inotropesdose3 = inotropesdose3;
        this.sedationagent1 = sedationagent1;
        this.sedationdose1 = sedationdose1;
        this.sedationagent2 = sedationagent2;
        this.sedationdose2 = sedationdose2;
        this.sedationagent3 = sedationagent3;
        this.sedationdose3 = sedationdose3;
        this.musclerelaxantsagent1 = musclerelaxantsagent1;
        this.musclerelaxantsdose1 = musclerelaxantsdose1;
        this.urea = urea;
        this.cr = cr;
        this.lactate = lactate;
        this.uo = uo;
        this.dialysis = dialysis;
        this.bloodgasPH = bloodgasPH;
        this.bloodgasPO2 = bloodgasPO2;
        this.bloodgasPCO2 = bloodgasPCO2;
        this.bloodgasHCO3 = bloodgasHCO3;
        this.bloodgasBE = bloodgasBE;
        this.refdocdesignation = refdocdesignation;
        this.refdoctelephone = refdoctelephone;
        this.history = history;
        this.transactiontype = transactiontype;
        this.sessid = sessid;
        this.age=age;
        this.mResend=mresend;
        this.murrayscore=murrayscore;
    }

    public String getReferHospitalId() {
        return referHospitalId;
    }

    public void setReferHospitalId(String referHospitalId) {
        this.referHospitalId = referHospitalId;
    }

    public String getSpeciallistId() {
        return speciallistId;
    }

    public void setSpeciallistId(String speciallistId) {
        this.speciallistId = speciallistId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getReferConsulutantName() {
        return referConsulutantName;
    }

    public void setReferConsulutantName(String referConsulutantName) {
        this.referConsulutantName = referConsulutantName;
    }

    public String getAdmissionDiagnosis() {
        return admissionDiagnosis;
    }

    public void setAdmissionDiagnosis(String admissionDiagnosis) {
        this.admissionDiagnosis = admissionDiagnosis;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getPreMorbFunctionalStatus() {
        return preMorbFunctionalStatus;
    }

    public void setPreMorbFunctionalStatus(String preMorbFunctionalStatus) {
        this.preMorbFunctionalStatus = preMorbFunctionalStatus;
    }

    public String getPreMorbFunctionalConsciousStatus() {
        return preMorbFunctionalConsciousStatus;
    }

    public void setPreMorbFunctionalConsciousStatus(String preMorbFunctionalConsciousStatus) {
        this.preMorbFunctionalConsciousStatus = preMorbFunctionalConsciousStatus;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }

    public String getDurOfConventianalMechanicalVentination() {
        return durOfConventianalMechanicalVentination;
    }

    public void setDurOfConventianalMechanicalVentination(String durOfConventianalMechanicalVentination) {
        this.durOfConventianalMechanicalVentination = durOfConventianalMechanicalVentination;
    }

    public String getSpO2() {
        return spO2;
    }

    public void setSpO2(String spO2) {
        this.spO2 = spO2;
    }

    public String getPO2() {
        return pO2;
    }

    public void setPO2(String pO2) {
        this.pO2 = pO2;
    }

    public String getFiO2() {
        return fiO2;
    }

    public void setFiO2(String fiO2) {
        this.fiO2 = fiO2;
    }

    public String getPao2fio2ratio() {
        return pao2fio2ratio;
    }

    public void setPao2fio2ratio(String pao2fio2ratio) {
        this.pao2fio2ratio = pao2fio2ratio;
    }

    public String getPip() {
        return pip;
    }

    public void setPip(String pip) {
        this.pip = pip;
    }

    public String getPeep() {
        return peep;
    }

    public void setPeep(String peep) {
        this.peep = peep;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getLungCompliance() {
        return lungCompliance;
    }

    public void setLungCompliance(String lungCompliance) {
        this.lungCompliance = lungCompliance;
    }

    public String getCxrquadrants() {
        return cxrquadrants;
    }

    public void setCxrquadrants(String cxrquadrants) {
        this.cxrquadrants = cxrquadrants;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getCvp() {
        return cvp;
    }

    public void setCvp(String cvp) {
        this.cvp = cvp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getCardiacindex() {
        return cardiacindex;
    }

    public void setCardiacindex(String cardiacindex) {
        this.cardiacindex = cardiacindex;
    }

    public String getLeftventricularejectionfraction() {
        return leftventricularejectionfraction;
    }

    public void setLeftventricularejectionfraction(String leftventricularejectionfraction) {
        this.leftventricularejectionfraction = leftventricularejectionfraction;
    }

    public String getInotropesagent1() {
        return inotropesagent1;
    }

    public void setInotropesagent1(String inotropesagent1) {
        this.inotropesagent1 = inotropesagent1;
    }

    public String getInotropesdose1() {
        return inotropesdose1;
    }

    public void setInotropesdose1(String inotropesdose1) {
        this.inotropesdose1 = inotropesdose1;
    }

    public String getInotropesagent2() {
        return inotropesagent2;
    }

    public void setInotropesagent2(String inotropesagent2) {
        this.inotropesagent2 = inotropesagent2;
    }

    public String getInotropesdose2() {
        return inotropesdose2;
    }

    public void setInotropesdose2(String inotropesdose2) {
        this.inotropesdose2 = inotropesdose2;
    }

    public String getInotropesagent3() {
        return inotropesagent3;
    }

    public void setInotropesagent3(String inotropesagent3) {
        this.inotropesagent3 = inotropesagent3;
    }

    public String getInotropesdose3() {
        return inotropesdose3;
    }

    public void setInotropesdose3(String inotropesdose3) {
        this.inotropesdose3 = inotropesdose3;
    }

    public String getSedationagent1() {
        return sedationagent1;
    }

    public void setSedationagent1(String sedationagent1) {
        this.sedationagent1 = sedationagent1;
    }

    public String getSedationdose1() {
        return sedationdose1;
    }

    public void setSedationdose1(String sedationdose1) {
        this.sedationdose1 = sedationdose1;
    }

    public String getSedationagent2() {
        return sedationagent2;
    }

    public void setSedationagent2(String sedationagent2) {
        this.sedationagent2 = sedationagent2;
    }

    public String getSedationdose2() {
        return sedationdose2;
    }

    public void setSedationdose2(String sedationdose2) {
        this.sedationdose2 = sedationdose2;
    }

    public String getSedationagent3() {
        return sedationagent3;
    }

    public void setSedationagent3(String sedationagent3) {
        this.sedationagent3 = sedationagent3;
    }

    public String getSedationdose3() {
        return sedationdose3;
    }

    public void setSedationdose3(String sedationdose3) {
        this.sedationdose3 = sedationdose3;
    }

    public String getMusclerelaxantsagent1() {
        return musclerelaxantsagent1;
    }

    public void setMusclerelaxantsagent1(String musclerelaxantsagent1) {
        this.musclerelaxantsagent1 = musclerelaxantsagent1;
    }

    public String getMusclerelaxantsdose1() {
        return musclerelaxantsdose1;
    }

    public void setMusclerelaxantsdose1(String musclerelaxantsdose1) {
        this.musclerelaxantsdose1 = musclerelaxantsdose1;
    }

    public String getUrea() {
        return urea;
    }

    public void setUrea(String urea) {
        this.urea = urea;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getLactate() {
        return lactate;
    }

    public void setLactate(String lactate) {
        this.lactate = lactate;
    }

    public String getUo() {
        return uo;
    }

    public void setUo(String uo) {
        this.uo = uo;
    }

    public String getDialysis() {
        return dialysis;
    }

    public void setDialysis(String dialysis) {
        this.dialysis = dialysis;
    }

    public String getBloodgasPH() {
        return bloodgasPH;
    }

    public void setBloodgasPH(String bloodgasPH) {
        this.bloodgasPH = bloodgasPH;
    }

    public String getBloodgasPO2() {
        return bloodgasPO2;
    }

    public void setBloodgasPO2(String bloodgasPO2) {
        this.bloodgasPO2 = bloodgasPO2;
    }

    public String getBloodgasPCO2() {
        return bloodgasPCO2;
    }

    public void setBloodgasPCO2(String bloodgasPCO2) {
        this.bloodgasPCO2 = bloodgasPCO2;
    }

    public String getBloodgasHCO3() {
        return bloodgasHCO3;
    }

    public void setBloodgasHCO3(String bloodgasHCO3) {
        this.bloodgasHCO3 = bloodgasHCO3;
    }

    public String getBloodgasBE() {
        return bloodgasBE;
    }

    public void setBloodgasBE(String bloodgasBE) {
        this.bloodgasBE = bloodgasBE;
    }

    public String getRefdocdesignation() {
        return refdocdesignation;
    }

    public void setRefdocdesignation(String refdocdesignation) {
        this.refdocdesignation = refdocdesignation;
    }

    public String getRefdoctelephone() {
        return refdoctelephone;
    }

    public void setRefdoctelephone(String refdoctelephone) {
        this.refdoctelephone = refdoctelephone;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getmResend() {
        return mResend;
    }

    public void setmResend(String mResend) {
        this.mResend = mResend;
    }

    public String getMurrayscore() {
        return murrayscore;
    }

    public void setMurrayscore(String murrayscore) {
        this.murrayscore = murrayscore;
    }
}
