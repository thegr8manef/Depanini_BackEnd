package com.dev0mmj.depanini.entity;

import javax.persistence.Entity;

@Entity
public class Client  extends User{
    public Client(Long id, String username,String password, String address, String image, Integer phone) {
        super(id, username, password,address, image, phone);
    }

    public Client() {

    }
}
