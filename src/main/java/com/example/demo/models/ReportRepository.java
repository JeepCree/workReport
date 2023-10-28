package com.example.demo.models;

import com.example.demo.dataclass.Base;
import com.example.demo.dataclass.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Base, Integer> {
    List<Base> findAll(Sort sort);
    @Query("SELECT base FROM Base base WHERE base.user = :userName")
    List<Base> findByUser(@Param("userName") String userName);
    @Query("SELECT SUM(b.sum) FROM Base b WHERE b.user = :userName")
    Double sumAmount(@Param("userName") String userName);
}
