package com.dev0mmj.depanini.controller;


import com.dev0mmj.depanini.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;



}
