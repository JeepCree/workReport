package com.example.demo.controllers;

import com.example.demo.dataclass.Operation;
import com.example.demo.dataclass.User;
import com.example.demo.models.OperationRepository;
import com.example.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/")
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    @GetMapping("/add-operation-name")
    public String operationPage(Model model) {

        return "add-operation-name";
    }


    @PostMapping("/add-operation-name")
    public String addOperation(String nameOperation, String choise, Model model) {
            Operation operation = new Operation();
            operation.setNameOperation(nameOperation);
            operation.setTypeOperation(choise);
            operationRepository.save(operation);
        return "redirect:all-operation-name";
    }
    @GetMapping("/all-operation-name")
    public String editOperationPage(Model model) {
        List<Operation> data = operationRepository.findAll();
        model.addAttribute("data", data);
        return "all-operation-name";
    }
//    @PostMapping("/all-operation-name")
//    public String editOperation(Model model) {
//        return "all-operation-name";
//    }
    @GetMapping("/edit-operation-name")
    public String getEditOperation(Model model) {
        return "redirect:/edit-operation-name";
    }
    @PostMapping("/edit-operation-name")
    public String editOperation(int itemId, Model model) {
        Operation operation = operationRepository.findOperationById(itemId);
        model.addAttribute("operation", operation);
        return "edit-operation-name";
    }
    @PostMapping("/deleteOperationName")
    public String deleteUser(int itemId, Model model) {
        if (operationRepository.existsById(itemId)) {
            operationRepository.deleteById(itemId);
        } else {
            return "redirect:/all-operation-name";
        }
        return "redirect:/all-operation-name";
    }
}
