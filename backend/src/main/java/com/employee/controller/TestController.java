package com.employee.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hi")
    public String hello(){
        return "hi prasad wellcome the the spring boot";
    }
}
