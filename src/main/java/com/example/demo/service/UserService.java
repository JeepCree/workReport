package com.example.demo.service;

import com.example.demo.dataclass.User;
import com.example.demo.models.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public User updateUserById(Integer id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + id + " не найден"));
        existingUser.setFio(updatedUser.getFio());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setDate(updatedUser.getDate());
        existingUser.setSex(updatedUser.getSex());
        return userRepository.save(existingUser);
    }
}

