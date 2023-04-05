package com.dev0mmj.depanini.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="workers")
public class Worker extends User{

    @Column(name = "speciality")
    private String speciality;
    @Column(name = "cin")
    private String cin;



}
