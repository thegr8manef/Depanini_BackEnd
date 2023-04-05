package com.dev0mmj.depanini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "address_gov")
    private String address_gov;

    @Column(name = "address_municipale")
    private String address_municipale;
    @Column(name = "image")
    private String image;
    @Column(name = "phone")
    private Integer phone;


}
