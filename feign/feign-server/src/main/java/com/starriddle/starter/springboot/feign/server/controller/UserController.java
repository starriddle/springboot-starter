package com.starriddle.starter.springboot.feign.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starriddle.starter.springboot.feign.server.entity.Dept;
import com.starriddle.starter.springboot.feign.server.entity.User;
import com.starriddle.starter.springboot.feign.server.service.UserService;

/**
 * @author CYL
 * @date 2018-04-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    boolean create(@RequestBody User user){
        return service.create(user);
    }

    @GetMapping("/getById")
    User getById(long id){
        return service.getById(id);
    }

    @GetMapping("/getNameById")
    String getNameById(long id){
        return service.getNameById(id);
    }

    @GetMapping("/getByDept")
    List<User> getByDept(Dept dept){
        return service.getByDept(dept);
    }

    @GetMapping("/getNamesByDept")
    List<String> getNamessByDept(Dept dept){
        return service.getNamesByDept(dept);
    }
}
