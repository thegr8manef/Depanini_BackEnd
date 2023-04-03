package com.dev0mmj.depanini.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Worker extends User{

    @Column(name = "niveau")
    private String niveau;
   @Column(name = "cin")
    private String cin;

    public Worker(Long id, String username, String password,String address_gov,String address_municipale, String image, Integer phone, String niveau, String cin) {
        super(id, username, password,address_gov,address_municipale, image, phone);
        this.niveau = niveau;
        this.cin = cin;
    }

    public Worker() {

    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
