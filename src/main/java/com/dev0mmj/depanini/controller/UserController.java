package com.dev0mmj.depanini.controller;


import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;



}
