package com.ecmo.android.model;

public class HospitalList
{
    private String listOfHospitals;
    private int id;

    public HospitalList(String hospitalname, int id) {
        this.listOfHospitals = hospitalname;
        this.id = id;
    }

    public String getHospitalName() {
        return listOfHospitals;
    }

    public void setListOfHospitals(String hospitalname) {
        this.listOfHospitals = hospitalname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
