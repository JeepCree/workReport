package com.example.demo.models;

import com.example.demo.dataclass.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findAll();
    Operation findOperationById(long itemId);
}
