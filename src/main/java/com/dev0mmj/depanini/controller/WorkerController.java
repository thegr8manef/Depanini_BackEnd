package com.dev0mmj.depanini.controller;

import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.entity.Worker;
import com.dev0mmj.depanini.exception.ResourceNotFoundException;
import com.dev0mmj.depanini.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WorkerController {

    @Autowired
    WorkerRepository workerRepository;
    private  PasswordEncoder passwordEncoder;


    public WorkerController(){
        passwordEncoder = new BCryptPasswordEncoder();
    }
    @PutMapping("/worker/modify/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable(value = "id") Long workerId,
                                               @RequestBody User workerDetails) throws ResourceNotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + workerId));
        worker.setUsername(workerDetails.getUsername());
        String encryptedPassword = passwordEncoder.encode(worker.getPassword());
        worker.setPassword(encryptedPassword);
        final Worker updatedUser = workerRepository.save(worker);
        return ResponseEntity.ok(updatedUser);
    }
    @PutMapping("/worker/modify/speciality/{id}")
    public ResponseEntity<Worker> updateWorkerSpeciality(@PathVariable(value = "id") Long workerId,
                                               @RequestBody Worker workerDetails) throws ResourceNotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + workerId));
        worker.setSpeciality(workerDetails.getSpeciality());
        final Worker updatedUser = workerRepository.save(worker);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/worker/search/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable(value = "id") Long workerId)
            throws ResourceNotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + workerId));
        return ResponseEntity.ok().body(worker);
    }
    @GetMapping("/workers")
    public List<Worker> findAll(){
        return workerRepository.findAll();
    }

    @PostMapping(value = "/worker/signup")
    public Worker createWorker( @RequestBody Worker worker) throws Exception {
        String encryptedPassword = passwordEncoder.encode(worker.getPassword());
        worker.setPassword(encryptedPassword);
        if (workerRepository.findByUsername(worker.getUsername()) == null){
            workerRepository.save(worker);
            return worker;
        }
        else {
            throw new Exception("username is already exist");
        }
    }

    @DeleteMapping("/worker/delete/{id}")
    public Map<String, Boolean> deleteWorkerById(@PathVariable(value = "id") Long workerId)
            throws ResourceNotFoundException {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found for this id :: " + workerId));

        workerRepository.delete(worker);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
