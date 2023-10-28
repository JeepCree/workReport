package com.example.demo.controllers;
import com.example.demo.dataclass.Base;
import com.example.demo.models.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Date;
import java.util.List;
@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class WorkStatController {
        String[] sorter = {"Date up", "Date down"};
        String[] userNameOptions = {
                "Все пользователи",
                "Дмитрий",
                "Александр",
                "Андрей",
                "Михаил",
                "Сергей"
        };
        String[] nameOperationOptions = {
                "Рабочие часы",
                "Плиты",
                "Рулоны",
                "Выплата ЗП",
                "Выходной"
        };
    @Autowired
    private WorkRepository workRepository;
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String handleIntegrityConstraintViolation(Exception e, Model model) {
        // Обработка ошибки
        model.addAttribute("error", "Уникальное ограничение нарушено.");
        return "error"; // Вернуть страницу с сообщением об ошибке
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/add")
    public String addData(
            @RequestParam("userName") String userName,
            @RequestParam("nameOperation") String nameOperation,
            @RequestParam("sum") String sum,
            @RequestParam("desc") String desc,
            @RequestParam("chosenDate") Date date,
            Model model
    ) {
        model.addAttribute("userName", userName);
        model.addAttribute("nameOperation", nameOperation);
        model.addAttribute("sum", sum);
        model.addAttribute("desc", desc);
        model.addAttribute("date", date);
        Base base = new Base();
        base.setUser(userName);
        base.setNameOperation(nameOperation);
        base.setDescription(desc);
        base.setDate(date);
        if (nameOperation.equals("Выплата ЗП")){
            base.setSum(Integer.parseInt(sum) * -1);
        } else {
            base.setSum(Integer.parseInt(sum));
        }
        workRepository.save(base);
        return "redirect:/work"; // Вернуть страницу с результатами
    }

    @GetMapping("/work")
    public String workPage(Model model) {
        // Создайте массив опций и добавьте его в модель

        model.addAttribute("userNameOptions", userNameOptions);
        model.addAttribute("nameOperationOptions", nameOperationOptions);

        return "homepage/work";
    }
    @PostMapping("/statistic")
    public String getStatistic(String userName, Model model) {
//        List<Base> data = workRepository.findByUser(userName);
//        model.addAttribute("data", data);
//        Double summa = workRepository.sumAmount(userName);
//        if (summa != null)
//        model.addAttribute("summa", summa);
//        model.addAttribute("userName", userName);
//        model.addAttribute("sorter", sorter);
        return "homepage/statistic";
    }
    @GetMapping ("/statistic")
    public String getStatisticPage(Model model) {
        model.addAttribute("userNameOptions", userNameOptions);
        return "homepage/statistic";
    }
}