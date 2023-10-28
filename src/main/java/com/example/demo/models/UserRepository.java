package com.example.demo.models;

import com.example.demo.dataclass.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    Iterable<User> findAll(Sort sort);
}