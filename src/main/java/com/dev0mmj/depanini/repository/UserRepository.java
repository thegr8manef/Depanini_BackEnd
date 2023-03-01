package com.dev0mmj.depanini.repository;


import com.dev0mmj.depanini.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    @Query(value = "Select * FROM users  WHERE phone = ?", nativeQuery = true)
    User findByPhone(Integer phone);
}
