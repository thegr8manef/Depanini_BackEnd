package com.dev0mmj.depanini.repository;


import com.dev0mmj.depanini.entity.Admin;
import com.dev0mmj.depanini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByPhone(Integer phone);


}
