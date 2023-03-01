package com.dev0mmj.depanini.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Worker extends User{

    @Column(name = "niveau")
    private String niveau;
   @Column(name = "cin")
    private Integer cin;

    public Worker(Long id, String username, String password,String address, String image, Integer phone, String niveau, Integer cin) {
        super(id, username, password,address, image, phone);
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

    public Integer getCin() {
        return cin;
    }

    public void setCin(Integer cin) {
        this.cin = cin;
    }
}
