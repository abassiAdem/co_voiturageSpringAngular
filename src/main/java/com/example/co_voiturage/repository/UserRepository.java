package com.example.co_voiturage.repository;

import com.example.co_voiturage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByid(Long id);


}