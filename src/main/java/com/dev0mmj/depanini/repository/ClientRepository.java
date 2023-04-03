package com.dev0mmj.depanini.repository;

import com.dev0mmj.depanini.entity.Admin;
import com.dev0mmj.depanini.entity.Client;
import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByUsername(String username);

    Client findByPassword(String password);

}
