package com.example.demo.controllers;

import com.example.demo.dataclass.User;
import com.example.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {
    Sort sort = Sort.by(Sort.Order.asc("id"));
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String handleIntegrityConstraintViolation(Exception e, Model model) {
        // Обработка ошибки
        model.addAttribute("error", "Уникальное ограничение нарушено.");
        return "error"; // Вернуть страницу с сообщением об ошибке
    }

    @PostMapping(path="/") // Map ONLY POST Requests
    public String addNewUser (
            String name,
            String surname,
            String midname,
            int age, Model model) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setSurname(surname);
        n.setMidname(midname);
        n.setAge(age);

        userRepository.save(n);
        Iterable<User> data = userRepository.findAll(sort);
        model.addAttribute("data", data);

        return "home";
    }
    @GetMapping(path="/delete")
    public String deleteAllUsers(Model model) {
        userRepository.deleteAll();
        return "home";
    }
    @GetMapping(path="/")
    public String getAllUsers(Model model) {
        // This returns a JSON or XML with the users
        Iterable<User> data = userRepository.findAll(sort);
        model.addAttribute("data", data);
        return "home";
    }
}