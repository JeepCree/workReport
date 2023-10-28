package com.example.demo.service;

import com.example.demo.dataclass.Operation;
import com.example.demo.models.OperationRepository;
import com.example.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }
    public List<String> getAllOperationNames() {
        List<String> operationNameBase = operationRepository.findAll()
                .stream()
                .map(operation -> operation.getNameOperation())
                .collect(Collectors.toList());
        return operationNameBase;
    }
    public String getOperationType(String operationName) {
        Optional<Operation> operation = operationRepository.findAll()
                .stream()
                .filter(op -> op.getNameOperation().equals(operationName))
                .findFirst();

        return operation.map(Operation::getTypeOperation).orElse(null);
    }
}
