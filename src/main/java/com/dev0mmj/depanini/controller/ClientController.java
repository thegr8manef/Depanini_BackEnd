package com.dev0mmj.depanini.controller;

import com.dev0mmj.depanini.entity.Client;
import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.exception.ResourceNotFoundException;
import com.dev0mmj.depanini.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;


    public ClientController(){
        passwordEncoder = new BCryptPasswordEncoder();
    }
    @PostMapping(value = "/client/signup")
    public Client createClient(@RequestBody Client client) throws Exception {
        String encryptedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encryptedPassword);
        if (clientRepository.findByUsername(client.getUsername()) == null) {
            clientRepository.save(client);
            return client;
        } else {
            throw new Exception("username is already exist");
        }
    }
    @GetMapping("/clients")
    public List<Client> findAllClient(){
        return clientRepository.findAll();
    }

    @GetMapping("/client/search/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        return ResponseEntity.ok().body(client);
    }
    @DeleteMapping("/client/delete/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @PutMapping("/client/modify/{id}")
    public ResponseEntity<User> updateClient(@PathVariable(value = "id") Long clientId,
                                             @RequestBody User userDetails) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        client.setUsername(userDetails.getUsername());
            String encryptedPassword = passwordEncoder.encode(client.getPassword());
            client.setPassword(encryptedPassword);
            final Client updateClient = clientRepository.save(client);
            return ResponseEntity.ok(updateClient);
        }

}
