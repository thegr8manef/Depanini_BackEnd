package com.dev0mmj.depanini.repository;

import com.dev0mmj.depanini.entity.Admin;
import com.dev0mmj.depanini.entity.User;
import com.dev0mmj.depanini.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {
    Worker findByUsername(String username);
    @Query(value = "Select * FROM users  WHERE phone = ?", nativeQuery = true)
    Worker findByPhone(Integer phone);

}
