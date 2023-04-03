package com.dev0mmj.depanini.controller;


import com.dev0mmj.depanini.entity.AuthRequest;
import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.dev0mmj.depanini.util.JwtUtil;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    public AuthController(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public User generateToken(@RequestBody AuthRequest authRequest)throws Exception{
        ResponseEntity token;
        User _user = userRepository.findByUsername(authRequest.getUsername());
        try {


            User user = userRepository.findByUsername(authRequest.getUsername());
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword()) &&
                    user.getUsername().equals(authRequest.getUsername())){

                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), user.getPassword())
                );
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("authorization", jwtUtil.generateToken(authRequest.getUsername()));

                //token = ResponseEntity.ok().header(responseHeaders.toString()).body("Success");
                token = new ResponseEntity(responseHeaders, HttpStatus.OK);
            }else {
                throw new Exception("Invalid username or password");

            }



        }catch (Exception e){

            throw new Exception("Invalid username or password");
        }

        return _user;
    }

    @PostMapping("/authenticate/register")
    public void login(@RequestBody User user) throws Exception {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        if (userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
        }
        else {
            throw new Exception("username is already exist");
        }
    }


    // if response is true, the token is expired
    // if response is false, the token is valid
    @PostMapping("/authenticate/validate_token")
    public Boolean validateToken(@RequestBody  String token){
        boolean isValidateToken= true;
        try {
            isValidateToken =  jwtUtil.isTokenExpired(token);
        }catch (Exception e){
            isValidateToken = true;
        }
        return isValidateToken;
    }


}
