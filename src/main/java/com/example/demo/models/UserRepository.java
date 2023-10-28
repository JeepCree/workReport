package com.example.demo.models;

import com.example.demo.dataclass.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    User findUserById(int itemId);
}
