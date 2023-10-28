package com.example.demo.controllers;

import com.example.demo.dataclass.Base;
import com.example.demo.models.ReportRepository;
import com.example.demo.service.OperationService;
import com.example.demo.service.UserService;
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
public class ReportController {
    private final UserService userService;
    private final OperationService operationService;
    @Autowired
    public ReportController (UserService userService, OperationService operationService) {
        this.userService = userService;
        this.operationService = operationService;
    }
    @Autowired
    ReportRepository reportRepository;
    @GetMapping("/add-operation")
    public String getOperationPage(Model model) {
        List<String> userNameBase = userService.getAllUserNames();
        List<String> operationNameBase = operationService.getAllOperationNames();
        model.addAttribute("userNameBase", userNameBase);
        model.addAttribute("operationNameBase", operationNameBase);
        return "add-operation";
    }
    @PostMapping("/add-operation")
    public String setOperation(String userName, String nameOperation, @RequestParam(name = "sum", required = false) Integer sum, String desc, Date chosenDate, Model model) {
        Base base = new Base();
        base.setUser(userName);
        base.setNameOperation(nameOperation);

        String operationType = operationService.getOperationType(nameOperation);
        if (operationType != null && operationType.equals("expense")) {
            sum = sum * -1;
        }

        base.setSum(sum);
        base.setDescription(desc);
        base.setDate(chosenDate);
        reportRepository.save(base);
        return "redirect:add-operation";
    }

    @GetMapping("/all-operation")
    public String getAllOperationPage(Model model) {
        List<String> userNameBase = userService.getAllUserNames();
        model.addAttribute("userNameBase", userNameBase);
        model.addAttribute("summa", 0);
        model.addAttribute("userName", "");
        return "all-operation";
    }
    @PostMapping("/all-operation")
    public String setAllOperationPage(String userName, Model model) {
        List<String> userNameBase = userService.getAllUserNames();
        model.addAttribute("userNameBase", userNameBase);
        List<Base> data = reportRepository.findByUser(userName);
        model.addAttribute("data", data);
        model.addAttribute("userName", userName);
        Double summa = reportRepository.sumAmount(userName);
        if (summa != null){
            model.addAttribute("summa", summa);
        } else {
            model.addAttribute("summa", 0);
        }
        return "all-operation";
    }
    @PostMapping("/delete-operation")
    public String deleteOperation(@RequestParam("itemId") int itemId, Model model) {
        if (reportRepository.existsById(itemId)) {
            reportRepository.deleteById(itemId);
        } else {
            return "redirect:/all-operation";
        }
        return "redirect:/all-operation"; // Обратите внимание на коррекцию пути
    }
}
