package com.dev0mmj.depanini.controller;


import com.dev0mmj.depanini.entity.Admin;
import com.dev0mmj.depanini.entity.Client;
import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.entity.Worker;
import com.dev0mmj.depanini.repository.AdminRepository;
import com.dev0mmj.depanini.repository.ClientRepository;
import com.dev0mmj.depanini.repository.UserRepository;
import com.dev0mmj.depanini.repository.WorkerRepository;
import com.dev0mmj.depanini.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;

    @PutMapping("/admin/modify/worker/{id}")
    public ResponseEntity<Worker> adminUpdateWorker(@PathVariable(value = "id") Long workerId,
                                                    @RequestBody Worker workerDetails) throws ResourceNotFoundException {
        Worker workerUpdate = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + workerId));
        workerUpdate.setUsername(workerDetails.getUsername());
        workerUpdate.setAddressMunicipale(workerDetails.getAddressMunicipale());
        workerUpdate.setAddressGov(workerDetails.getAddressGov());
        workerUpdate.setImage(workerDetails.getImage());
        workerUpdate.setPhone(workerDetails.getPhone());
        workerUpdate.setCin(workerDetails.getCin());
        workerUpdate.setSpeciality(workerDetails.getSpeciality());
        final Worker adminUpdateWorker = workerRepository.save(workerUpdate);
        return ResponseEntity.ok(adminUpdateWorker);
    }

    @PutMapping("/admin/modify/client/{id}")
    public ResponseEntity<Client> adminUpdateClient(@PathVariable(value = "id") Long clientId,
                                                    @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client clientUpdate = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        clientUpdate.setUsername(clientDetails.getUsername());
        clientUpdate.setAddressMunicipale(clientDetails.getAddressMunicipale());
        clientUpdate.setAddressGov(clientDetails.getAddressGov());
        clientUpdate.setImage(clientDetails.getImage());
        clientUpdate.setPhone(clientDetails.getPhone());
        clientUpdate.setPassword(clientDetails.getPassword());
        final Client adminUpdateClient = clientRepository.save(clientUpdate);
        return ResponseEntity.ok(adminUpdateClient);
    }

    @GetMapping("/admin/search/worker/{id}")
    public ResponseEntity<Worker> adminGetWorkerById(@PathVariable(value = "id") Long workerId)
            throws ResourceNotFoundException {
        Worker workerSearch = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + workerId));
        return ResponseEntity.ok().body(workerSearch);
    }

    @GetMapping("/admin/search/client/{id}")
    public ResponseEntity<Client> adminGetClientById(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client clientSearch = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        return ResponseEntity.ok().body(clientSearch);
    }

    @GetMapping("/admin/workers")
    public List<Worker> adminFindAllWorkers() {
        return workerRepository.findAll();
    }

    @GetMapping("/admin/clients")
    public List<Client> adminFindAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping(value = "/admin/worker/signup")
    public Worker adminCreateWorker(@RequestBody Worker workerSave) {
        return workerRepository.save(workerSave);
    }

    @PostMapping(value = "/admin/client/signup")
    public Client adminCreateClient(@RequestBody Client clientSave) {
        return clientRepository.save(clientSave);
    }

    @DeleteMapping("/admin/delete/worker/{id}")
    public Map<String, Boolean> adminDeleteWorkerById(@PathVariable(value = "id") Long workerId)
            throws ResourceNotFoundException {
        Worker workerDelete = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + workerId));

        workerRepository.delete(workerDelete);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/admin/delete/client/{id}")
    public Map<String, Boolean> adminDeleteClientById(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        Client clientDelete = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));

        clientRepository.delete(clientDelete);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/admin/all")
    public List<User> adminFindAll() {
        List<User> allList = new ArrayList<>();
        allList.addAll(clientRepository.findAll());
        allList.addAll(workerRepository.findAll());
        allList.addAll(userRepository.findAll());
        return allList;
    }

    /*    @GetMapping("/admin/search/user/{phone}")
        public ResponseEntity<Client> adminGetClientById(@PathVariable(value = "id") Long clientId)
                throws ResourceNotFoundException {
            Client clientSearch = clientRepository.findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
            return ResponseEntity.ok().body(clientSearch);
        }*/
    @GetMapping("/admin/search/user/{phone}")
    public Boolean findByPhone(@PathVariable int phone)  {
        if (userRepository.findByPhone(phone) == null) {
            return true;
        } else {

            return false;
        }


    }

    @GetMapping("/admin/search/{address_municipale}/{address_gov}")
    public List<Worker> adminByAdrdess(@PathVariable(value = "address_municipale") String address_municipale,
                                       @PathVariable(value = "address_gov") String address_gov) {
        List<Worker> clientSearch = workerRepository.findByAddressMunicipale(address_municipale,address_gov);
        return clientSearch;
    }
}
