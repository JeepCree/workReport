package com.example.demo.controllers;

import com.example.demo.dataclass.User;
import com.example.demo.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(path="/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/add-user")
    public String userPage(Model model) {
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

//    @PostMapping("/all-users")
//    public String editUserPage(Model model) {
//        List<User> data = userRepository.findAll();
//        model.addAttribute("data", data);
//        return "/all-user";
//    }
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
    public String saveUser(int itemId, Model model) {
return "redirect:/all-users";
    }
}
