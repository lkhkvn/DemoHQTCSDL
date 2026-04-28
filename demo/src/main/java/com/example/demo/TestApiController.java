package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestApiController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Yeu cau thanh cong! Ban dang xem du lieu bao mat.";
    }

    @GetMapping("/status")
    public String checkStatus() {
        return "He thong dang hoat dong on dinh.";
    }
}