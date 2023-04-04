package com.dev0mmj.depanini.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Worker extends User{

    @Column(name = "speciality")
    private String speciality;
   @Column(name = "cin")
    private String cin;

    public Worker(Long id, String username, String password,String address_gov,String address_municipale, String image, Integer phone, String speciality, String cin) {
        super(id, username, password,address_gov,address_municipale, image, phone);
        this.speciality = speciality;
        this.cin = cin;
    }

    public Worker() {

    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
