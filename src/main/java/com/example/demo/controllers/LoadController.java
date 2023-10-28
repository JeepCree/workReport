package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class LoadController {
    private List<Object> collection = new ArrayList<>();
    int totalItems = 1000;

    @GetMapping("/status")
    public String runAddCollection() throws InterruptedException {
        collection = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(1); // Создание пула потоков с одним потоком

    executorService.submit(() -> {
        for (int i = 0; i < totalItems; i++) {
            collection.add(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    executorService.shutdown(); // Завершение пула потоков после выполнения задачи

    return "homepage/collection-status"; // Перенаправьте пользователя на страницу /collection-status
}
    @GetMapping("/collection-status")
    @ResponseBody
    public int getCollectionStatus() {
        int currentItems = collection.size();
        int percentage = (currentItems * 100) / totalItems;
        return percentage;
    }
}