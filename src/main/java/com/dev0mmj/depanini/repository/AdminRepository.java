package com.dev0mmj.depanini.repository;

import com.dev0mmj.depanini.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query(value = "Select * FROM users  WHERE phone = ?", nativeQuery = true)
    Admin findByPhone(Integer phone);

}
