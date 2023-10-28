package com.example.demo.service;

import com.example.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUserNames() {
        List<String> userNameBase = userRepository.findAll()
                .stream()
                .map(user -> user.getFio())
                .collect(Collectors.toList());
        return userNameBase;
    }
}

