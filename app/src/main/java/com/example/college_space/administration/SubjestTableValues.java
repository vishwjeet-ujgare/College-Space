package com.example.college_space.administration;


public class SubjestTableValues {

    private String name;
    private String teacher;
    private String abbreviation;

    public SubjestTableValues(String name,String abbreviation, String teacher) {
        this.name = name;
        this.teacher = teacher;
        this.abbreviation = abbreviation;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
