package com.example.college_space.administration;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Teacher_Values {

    private int id;
    private String email;
    private String first_name;
    private String last_name;

    public Teacher_Values(int id, String first_name, String last_name, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;

    }

    public String getGmail() {
        return email;
    }

    public void setGmail(String gmail) {
        this.email = gmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
