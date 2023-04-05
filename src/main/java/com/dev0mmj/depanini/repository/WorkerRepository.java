package com.dev0mmj.depanini.repository;

import com.dev0mmj.depanini.entity.Admin;
import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {
    Worker findByUsername(String username);

    @Query(value = "Select * FROM users  INNER JOIN workers ON users.id=workers.id AND (address_municipale = ? OR address_gov= ?) ", nativeQuery = true)
    List<Worker> findByAddressMunicipale(String address_municipale, String address_gov);

    Worker findByPhone(Integer phone);

    List<Worker> findBySpeciality(String Speciality);


    List<Worker> findByUsernameContaining(String username);

}
