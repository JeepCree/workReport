package com.example.demo.controllers;

import com.example.demo.dataclass.User;
import com.example.demo.models.UserRepository;
import com.example.demo.service.UserService;
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
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/add-user")
    public String loadUserPage(Model model) {
        return "add-user";
    }
    @PostMapping("/add-user")
    public String addUser(String name, String surname, String midname, Date date, String phone, String address, String sex, Model model) {
        User user = new User();
        user.setFio(surname + " " + name + " " + midname);
        user.setDate(date);
        user.setPhone(phone);
        user.setAddress(address);
        user.setSex(sex);
        userRepository.save(user);
        return "redirect:/all-users";
    }
    @GetMapping("/all-users")
    public String editPage(Model model) {
        List<User> data = userRepository.findAll();
        model.addAttribute("data", data);
        return "all-users";
    }
    @GetMapping("/edit-user")
    public String getEditUser(Model model) {
        return "redirect:/edit-user";
    }
    @PostMapping("/edit-user")
    public String editUser(int itemId, Model model) {
        User user = userRepository.findUserById(itemId);
        model.addAttribute("user", user);
        return "edit-user";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(int itemId, Model model) {
        if (userRepository.existsById(itemId)) {
            userRepository.deleteById(itemId);
        } else {
            return "redirect:/all-users";
        }
        return "redirect:/all-users";
    }
    @PostMapping("/saveUser")
    public String saveUser(Integer id, String fio, String phone, String address, Date date, String sex, Model model) {
        User user = new User();
        user.setFio(fio);
        user.setPhone(phone);
        user.setAddress(address);
        user.setDate(date);
        user.setSex(sex);
        userService.updateUserById(id, user);
        return "redirect:/all-users";
    }
}