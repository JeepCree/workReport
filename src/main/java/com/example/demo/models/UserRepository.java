package com.example.demo.models;

import com.example.demo.dataclass.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    User findUserById(int itemId);
//    User findUserByFioIsLike(String userName);
    User findUserByFioEquals(String userName);
}
