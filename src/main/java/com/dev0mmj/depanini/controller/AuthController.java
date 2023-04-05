package com.dev0mmj.depanini.controller;


import com.dev0mmj.depanini.entity.AuthRequest;
import com.dev0mmj.depanini.entity.Client;
import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.entity.Worker;
import com.dev0mmj.depanini.repository.ClientRepository;
import com.dev0mmj.depanini.repository.UserRepository;
import com.dev0mmj.depanini.repository.WorkerRepository;
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

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @PostMapping("/authenticate")
    public User login(@RequestBody AuthRequest authRequest)throws Exception{
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

    @PostMapping("/authenticate/client")
    public User login_client(@RequestBody AuthRequest authRequest)throws Exception{
        ResponseEntity token;
        Client _user = clientRepository.findByUsername(authRequest.getUsername());
        try {


            User user = clientRepository.findByUsername(authRequest.getUsername());
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

    @PostMapping("/authenticate/worker")
    public User login_worker(@RequestBody AuthRequest authRequest)throws Exception{
        ResponseEntity token;
        Worker _user = workerRepository.findByUsername(authRequest.getUsername());
        try {


            User user = workerRepository.findByUsername(authRequest.getUsername());
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

    @PostMapping("/authenticate/register/client")
    public void signup_client(@RequestBody Client client) throws Exception {
        String encryptedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encryptedPassword);
        if (userRepository.findByUsername(client.getUsername()) == null){
            clientRepository.save(client);
        }
        else {
            throw new Exception("username  already exist");
        }
    }

    @PostMapping("/authenticate/register/worker")
    public void signup_worker(@RequestBody Worker worker) throws Exception {
        String encryptedPassword = passwordEncoder.encode(worker.getPassword());
        worker.setPassword(encryptedPassword);
        if (userRepository.findByUsername(worker.getUsername()) == null){
            workerRepository.save(worker);
        }
        else {
            throw new Exception("username already exist");
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
