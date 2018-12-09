package com.starriddle.starter.springboot.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starriddle.starter.springboot.demo.util.Result;

@RestController
public class HelloWorld {
    
    @RequestMapping("/")
    public String home() {
        return "Hello SpringBoot!";
    }

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping("/response/map")
    public Map<String, Object> returnMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("int", 12);
        result.put("Long", 100L);
        result.put("boolean", false);
        result.put("string", "a String");
        return result;
    }

    @RequestMapping("/response/result")
    public Result<Map<String, Object>> returnResult() {
        return Result.result(returnMap());
    }

}
