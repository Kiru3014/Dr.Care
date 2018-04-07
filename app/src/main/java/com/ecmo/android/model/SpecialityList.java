package com.ecmo.android.model;

public class SpecialityList
{
    private String specialtyName;
    private int id;

    public SpecialityList(String specialtyName, int id) {
        this.specialtyName = specialtyName;
        this.id = id;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
