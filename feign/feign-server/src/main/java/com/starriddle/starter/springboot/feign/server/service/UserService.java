package com.starriddle.starter.springboot.feign.server.service;

import com.starriddle.starter.springboot.feign.server.entity.Dept;
import com.starriddle.starter.springboot.feign.server.entity.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CYL
 * @date 2018-04-25
 */
@Service
public class UserService {

    public boolean create(User user) {
        System.out.println("create Emp: " + (user==null?null:user.toString()));
        System.out.println("return: " + true);
        return true;
    }

    public User getById(long id) {
        System.out.println("get User by Id: " + id);
        User user = new User(9001, "jack", 27, "1952-02-02", "shanghai, china", 2,2);
        System.out.println("return: " + user);
        return user;
    }

    public String getNameById(long id) {
        System.out.println("get Name by Id: " + id);
        String result = "jack";
        System.out.println("return: " + result);
        return result;
    }

    public List<User> getByDept(Dept dept) {
        System.out.println("get Users by Dept: " + dept);
        User user1 = new User(90011, "jack", 27, "1952-02-02", "shanghai, china", dept.getId(),dept.getOrgId());
        User user2 = new User(90012, "jacky", 28, "1952-02-03", "shanghai, china", dept.getId(),dept.getOrgId());
        List<User> result = new ArrayList<>();
        result.add(user1);
        result.add(user2);
        System.out.println("return: " + result);
        return result;
    }

    public List<String> getNamesByDept(Dept dept) {
        System.out.println("get Names by Dept: " + dept);
        List<String> result = new ArrayList<>();
        result.add("jack");
        result.add("jacky");
        System.out.println("return: " + result);
        return result;
    }
}
