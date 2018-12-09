package com.starriddle.starter.springboot.feign.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CYL
 * @date 2018-04-26
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @RequestMapping("/index")
    String index(){
        System.out.println("http from: /index");
        String result = "Hello World —— Feign Server from index";
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/hello")
    String getHello(){
        System.out.println("http from: GET /hello");
        String result = "Hello World —— Feign Server from hello by GET";
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/hello")
    String postHello(){
        System.out.println("http from: POST /hello");
        String result = "Hello World —— Feign Server from hello by POST";
        System.out.println("return: " + result);
        return result;
    }
}
