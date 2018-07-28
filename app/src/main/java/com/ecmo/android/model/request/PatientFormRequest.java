package com.ecmo.android.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PatientFormRequest implements Serializable {
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
    @SerializedName("Inotropesagent4")
    @Expose
    private String inotropesagent4;
    @SerializedName("Inotropesdose4")
    @Expose
    private String inotropesdose4;
    @SerializedName("Inotropesagent5")
    @Expose
    private String inotropesagent5;
    @SerializedName("Inotropesdose5")
    @Expose
    private String inotropesdose5;
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
    @SerializedName("Sedationagent4")
    @Expose
    private String sedationagent4;
    @SerializedName("Sedationdose4")
    @Expose
    private String sedationdose4;
    @SerializedName("Sedationagent5")
    @Expose
    private String sedationagent5;
    @SerializedName("Sedationdose5")
    @Expose
    private String sedationdose5;
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
    @SerializedName("Prone_Positioning")
    @Expose
    private String pronePositioning;
    @SerializedName("Prone_Positioning_improv")
    @Expose
    private String pronePositioningImprov;
    @SerializedName("Nitric_Acid")
    @Expose
    private String nitricAcid;
    @SerializedName("Nitric_Acid_improv")
    @Expose
    private String nitricAcidImprov;
    @SerializedName("Plasmaphersis")
    @Expose
    private String plasmaphersis;
    @SerializedName("Plasmaphersis_improv")
    @Expose
    private String plasmaphersisImprov;
    @SerializedName("Therapuetic_Hypothermia")
    @Expose
    private String therapueticHypothermia;
    @SerializedName("Therapuetic_Hypothermia_improv")
    @Expose
    private String therapueticHypothermiaImprov;
    @SerializedName("Others")
    @Expose
    private String others;
    @SerializedName("Others_improv")
    @Expose
    private String othersImprov;
    @SerializedName("abg_lactate")
    @Expose
    private String abgLactate;
    @SerializedName("bg_sao2")
    @Expose
    private String bgSao2;
    @SerializedName("bg_spo2")
    @Expose
    private String bgSpo2;
    @SerializedName("Inotropes_dose_1_value")
    @Expose
    private String inotropesDose1Value;
    @SerializedName("Inotropes_dose_2_value")
    @Expose
    private String inotropesDose2Value;
    @SerializedName("Inotropes_dose_3_value")
    @Expose
    private String inotropesDose3Value;
    @SerializedName("Inotropes_dose_4_value")
    @Expose
    private String inotropesDose4Value;
    @SerializedName("Inotropes_dose_5_value")
    @Expose
    private String inotropesDose5Value;
    @SerializedName("Sedation_dose_1_value")
    @Expose
    private String sedationDose1Value;
    @SerializedName("Sedation_dose_2_value")
    @Expose
    private String sedationDose2Value;
    @SerializedName("Sedation_dose_3_value")
    @Expose
    private String sedationDose3Value;
    @SerializedName("Sedation_dose_4_value")
    @Expose
    private String sedationDose4Value;
    @SerializedName("Sedation_dose_5_value")
    @Expose
    private String sedationDose5Value;
    @SerializedName("Inotropes_agent_1_others")
    @Expose
    private String inotropesAgent1Others;
    @SerializedName("Inotropes_agent_2_others")
    @Expose
    private String inotropesAgent2Others;
    @SerializedName("Inotropes_agent_3_others")
    @Expose
    private String inotropesAgent3Others;
    @SerializedName("Inotropes_agent_4_others")
    @Expose
    private String inotropesAgent4Others;
    @SerializedName("Inotropes_agent_5_others")
    @Expose
    private String inotropesAgent5Others;
    @SerializedName("Sedation_agent_1_others")
    @Expose
    private String sedationAgent1Others;
    @SerializedName("Sedation_agent_2_others")
    @Expose
    private String sedationAgent2Others;
    @SerializedName("Sedation_agent_3_others")
    @Expose
    private String sedationAgent3Others;
    @SerializedName("Sedation_agent_4_others")
    @Expose
    private String sedationAgent4Others;
    @SerializedName("Sedation_agent_5_others")
    @Expose
    private String sedationAgent5Others;
    @SerializedName("improvothers")
    @Expose
    private String improvothers;



    public PatientFormRequest(String referHospitalId, String speciallistId, String docId, String referConsulutantName, String admissionDiagnosis, String patientName, String civilId, String gender, String unit, String ward, String bed, String fileNo, String preMorbFunctionalStatus, String preMorbFunctionalConsciousStatus, String e, String v, String m, String totalscore, String durOfConventianalMechanicalVentination, String spO2, String pO2, String fiO2, String pao2fio2ratio, String pip, String peep, String tv, String rr, String lungCompliance, String cxrquadrants, String hr, String bp, String cvp, String temp, String co, String cardiacindex, String leftventricularejectionfraction, String inotropesagent1, String inotropesdose1, String inotropesagent2, String inotropesdose2, String inotropesagent3, String inotropesdose3, String inotropesagent4, String inotropesdose4, String inotropesagent5, String inotropesdose5, String sedationagent1, String sedationdose1, String sedationagent2, String sedationdose2, String sedationagent3, String sedationdose3, String sedationagent4, String sedationdose4, String sedationagent5, String sedationdose5, String musclerelaxantsagent1, String musclerelaxantsdose1, String urea, String cr, String lactate, String uo, String dialysis, String bloodgasPH, String bloodgasPO2, String bloodgasPCO2, String bloodgasHCO3, String bloodgasBE, String refdocdesignation, String refdoctelephone, String history, String transactiontype, String sessid, String age, String mresend, String murrayscore, String pronePositioning, String pronePositioningImprov, String nitricAcid, String nitricAcidImprov, String plasmaphersis, String plasmaphersisImprov, String therapueticHypothermia, String therapueticHypothermiaImprov, String others, String othersImprov, String abgLactate, String bgSao2, String bgSpo2, String inotropesDose1Value, String inotropesDose2Value, String inotropesDose3Value, String inotropesDose4Value, String inotropesDose5Value, String sedationDose1Value, String sedationDose2Value, String sedationDose3Value, String sedationDose4Value, String sedationDose5Value, String inotropesAgent1Others, String inotropesAgent2Others, String inotropesAgent3Others, String inotropesAgent4Others, String inotropesAgent5Others, String sedationAgent1Others, String sedationAgent2Others, String sedationAgent3Others, String sedationAgent4Others, String sedationAgent5Others, String improvothers) {
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
        this.inotropesagent4 = inotropesagent4;
        this.inotropesdose4 = inotropesdose4;
        this.inotropesagent5 = inotropesagent5;
        this.inotropesdose5 = inotropesdose5;
        this.sedationagent1 = sedationagent1;
        this.sedationdose1 = sedationdose1;
        this.sedationagent2 = sedationagent2;
        this.sedationdose2 = sedationdose2;
        this.sedationagent3 = sedationagent3;
        this.sedationdose3 = sedationdose3;
        this.sedationagent4 = sedationagent4;
        this.sedationdose4 = sedationdose4;
        this.sedationagent5 = sedationagent5;
        this.sedationdose5 = sedationdose5;
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
        this.age = age;
        this.mResend = mresend;
        this.murrayscore = murrayscore;
        this.pronePositioning = pronePositioning;
        this.pronePositioningImprov = pronePositioningImprov;
        this.nitricAcid = nitricAcid;
        this.nitricAcidImprov = nitricAcidImprov;
        this.plasmaphersis = plasmaphersis;
        this.plasmaphersisImprov = plasmaphersisImprov;
        this.therapueticHypothermia = therapueticHypothermia;
        this.therapueticHypothermiaImprov = therapueticHypothermiaImprov;
        this.others = others;
        this.othersImprov = othersImprov;
        this.abgLactate = abgLactate;
        this.bgSao2 = bgSao2;
        this.bgSpo2 = bgSpo2;
        this.inotropesDose1Value = inotropesDose1Value;
        this.inotropesDose2Value = inotropesDose2Value;
        this.inotropesDose3Value = inotropesDose3Value;
        this.inotropesDose4Value = inotropesDose4Value;
        this.inotropesDose5Value = inotropesDose5Value;
        this.sedationDose1Value = sedationDose1Value;
        this.sedationDose2Value = sedationDose2Value;
        this.sedationDose3Value = sedationDose3Value;
        this.sedationDose4Value = sedationDose4Value;
        this.sedationDose5Value = sedationDose5Value;
        this.inotropesAgent1Others = inotropesAgent1Others;
        this.inotropesAgent2Others = inotropesAgent2Others;
        this.inotropesAgent3Others = inotropesAgent3Others;
        this.inotropesAgent4Others = inotropesAgent4Others;
        this.inotropesAgent5Others = inotropesAgent5Others;
        this.sedationAgent1Others = sedationAgent1Others;
        this.sedationAgent2Others = sedationAgent2Others;
        this.sedationAgent3Others = sedationAgent3Others;
        this.sedationAgent4Others = sedationAgent4Others;
        this.sedationAgent5Others = sedationAgent5Others;
        this.improvothers = improvothers;
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

    public String getInotropesagent4() {
        return inotropesagent4;
    }

    public void setInotropesagent4(String inotropesagent4) {
        this.inotropesagent4 = inotropesagent4;
    }

    public String getInotropesdose4() {
        return inotropesdose4;
    }

    public void setInotropesdose4(String inotropesdose4) {
        this.inotropesdose4 = inotropesdose4;
    }

    public String getSedationagent4() {
        return sedationagent4;
    }

    public void setSedationagent4(String sedationagent4) {
        this.sedationagent4 = sedationagent4;
    }

    public String getSedationdose4() {
        return sedationdose4;
    }

    public void setSedationdose4(String sedationdose4) {
        this.sedationdose4 = sedationdose4;
    }

    public String getPronePositioning() {
        return pronePositioning;
    }

    public void setPronePositioning(String pronePositioning) {
        this.pronePositioning = pronePositioning;
    }

    public String getPronePositioningImprov() {
        return pronePositioningImprov;
    }

    public void setPronePositioningImprov(String pronePositioningImprov) {
        this.pronePositioningImprov = pronePositioningImprov;
    }

    public String getNitricAcid() {
        return nitricAcid;
    }

    public void setNitricAcid(String nitricAcid) {
        this.nitricAcid = nitricAcid;
    }

    public String getNitricAcidImprov() {
        return nitricAcidImprov;
    }

    public void setNitricAcidImprov(String nitricAcidImprov) {
        this.nitricAcidImprov = nitricAcidImprov;
    }

    public String getPlasmaphersis() {
        return plasmaphersis;
    }

    public void setPlasmaphersis(String plasmaphersis) {
        this.plasmaphersis = plasmaphersis;
    }

    public String getPlasmaphersisImprov() {
        return plasmaphersisImprov;
    }

    public void setPlasmaphersisImprov(String plasmaphersisImprov) {
        this.plasmaphersisImprov = plasmaphersisImprov;
    }

    public String getTherapueticHypothermia() {
        return therapueticHypothermia;
    }

    public void setTherapueticHypothermia(String therapueticHypothermia) {
        this.therapueticHypothermia = therapueticHypothermia;
    }

    public String getTherapueticHypothermiaImprov() {
        return therapueticHypothermiaImprov;
    }

    public void setTherapueticHypothermiaImprov(String therapueticHypothermiaImprov) {
        this.therapueticHypothermiaImprov = therapueticHypothermiaImprov;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getOthersImprov() {
        return othersImprov;
    }

    public void setOthersImprov(String othersImprov) {
        this.othersImprov = othersImprov;
    }

    public String getAbgLactate() {
        return abgLactate;
    }

    public void setAbgLactate(String abgLactate) {
        this.abgLactate = abgLactate;
    }

    public String getBgSao2() {
        return bgSao2;
    }

    public void setBgSao2(String bgSao2) {
        this.bgSao2 = bgSao2;
    }

    public String getBgSpo2() {
        return bgSpo2;
    }

    public void setBgSpo2(String bgSpo2) {
        this.bgSpo2 = bgSpo2;
    }

    public String getInotropesagent5() {
        return inotropesagent5;
    }

    public void setInotropesagent5(String inotropesagent5) {
        this.inotropesagent5 = inotropesagent5;
    }

    public String getInotropesdose5() {
        return inotropesdose5;
    }

    public void setInotropesdose5(String inotropesdose5) {
        this.inotropesdose5 = inotropesdose5;
    }

    public String getSedationagent5() {
        return sedationagent5;
    }

    public void setSedationagent5(String sedationagent5) {
        this.sedationagent5 = sedationagent5;
    }

    public String getSedationdose5() {
        return sedationdose5;
    }

    public void setSedationdose5(String sedationdose5) {
        this.sedationdose5 = sedationdose5;
    }

    public String getInotropesDose1Value() {
        return inotropesDose1Value;
    }

    public void setInotropesDose1Value(String inotropesDose1Value) {
        this.inotropesDose1Value = inotropesDose1Value;
    }

    public String getInotropesDose2Value() {
        return inotropesDose2Value;
    }

    public void setInotropesDose2Value(String inotropesDose2Value) {
        this.inotropesDose2Value = inotropesDose2Value;
    }

    public String getInotropesDose3Value() {
        return inotropesDose3Value;
    }

    public void setInotropesDose3Value(String inotropesDose3Value) {
        this.inotropesDose3Value = inotropesDose3Value;
    }

    public String getInotropesDose4Value() {
        return inotropesDose4Value;
    }

    public void setInotropesDose4Value(String inotropesDose4Value) {
        this.inotropesDose4Value = inotropesDose4Value;
    }

    public String getInotropesDose5Value() {
        return inotropesDose5Value;
    }

    public void setInotropesDose5Value(String inotropesDose5Value) {
        this.inotropesDose5Value = inotropesDose5Value;
    }

    public String getSedationDose1Value() {
        return sedationDose1Value;
    }

    public void setSedationDose1Value(String sedationDose1Value) {
        this.sedationDose1Value = sedationDose1Value;
    }

    public String getSedationDose2Value() {
        return sedationDose2Value;
    }

    public void setSedationDose2Value(String sedationDose2Value) {
        this.sedationDose2Value = sedationDose2Value;
    }

    public String getSedationDose3Value() {
        return sedationDose3Value;
    }

    public void setSedationDose3Value(String sedationDose3Value) {
        this.sedationDose3Value = sedationDose3Value;
    }

    public String getSedationDose4Value() {
        return sedationDose4Value;
    }

    public void setSedationDose4Value(String sedationDose4Value) {
        this.sedationDose4Value = sedationDose4Value;
    }

    public String getSedationDose5Value() {
        return sedationDose5Value;
    }

    public void setSedationDose5Value(String sedationDose5Value) {
        this.sedationDose5Value = sedationDose5Value;
    }

    public String getInotropesAgent1Others() {
        return inotropesAgent1Others;
    }

    public void setInotropesAgent1Others(String inotropesAgent1Others) {
        this.inotropesAgent1Others = inotropesAgent1Others;
    }

    public String getInotropesAgent2Others() {
        return inotropesAgent2Others;
    }

    public void setInotropesAgent2Others(String inotropesAgent2Others) {
        this.inotropesAgent2Others = inotropesAgent2Others;
    }

    public String getInotropesAgent3Others() {
        return inotropesAgent3Others;
    }

    public void setInotropesAgent3Others(String inotropesAgent3Others) {
        this.inotropesAgent3Others = inotropesAgent3Others;
    }

    public String getInotropesAgent4Others() {
        return inotropesAgent4Others;
    }

    public void setInotropesAgent4Others(String inotropesAgent4Others) {
        this.inotropesAgent4Others = inotropesAgent4Others;
    }

    public String getInotropesAgent5Others() {
        return inotropesAgent5Others;
    }

    public void setInotropesAgent5Others(String inotropesAgent5Others) {
        this.inotropesAgent5Others = inotropesAgent5Others;
    }

    public String getSedationAgent1Others() {
        return sedationAgent1Others;
    }

    public void setSedationAgent1Others(String sedationAgent1Others) {
        this.sedationAgent1Others = sedationAgent1Others;
    }

    public String getSedationAgent2Others() {
        return sedationAgent2Others;
    }

    public void setSedationAgent2Others(String sedationAgent2Others) {
        this.sedationAgent2Others = sedationAgent2Others;
    }

    public String getSedationAgent3Others() {
        return sedationAgent3Others;
    }

    public void setSedationAgent3Others(String sedationAgent3Others) {
        this.sedationAgent3Others = sedationAgent3Others;
    }

    public String getSedationAgent4Others() {
        return sedationAgent4Others;
    }

    public void setSedationAgent4Others(String sedationAgent4Others) {
        this.sedationAgent4Others = sedationAgent4Others;
    }

    public String getSedationAgent5Others() {
        return sedationAgent5Others;
    }

    public void setSedationAgent5Others(String sedationAgent5Others) {
        this.sedationAgent5Others = sedationAgent5Others;
    }

    public String getImprovothers() {
        return improvothers;
    }

    public void setImprovothers(String improvothers) {
        this.improvothers = improvothers;
    }
}
