package com.starriddle.starter.springboot.feign.server.controller;

import com.starriddle.starter.springboot.feign.server.entity.User;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

/**
 * @author CYL
 * @date 2018-04-27
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping("/int")
    int testInt1(@RequestParam int i){
        System.out.println("GET test int i=" + i);
        int result = i * 5;
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/int")
    int testInt2(@RequestBody int i){
        System.out.println("POST test int i=" + i);
        int result = i * 5;
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/string")
    String testString1(String s){
        System.out.println("GET test string s=" + s);
        String result = "GET test \"string\" - over";
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/string")
    String testString2(@RequestBody String s){
        System.out.println("POST test string s=" + s);
        String result = "POST test \"string\" - over";
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/bigdecimal")
    BigDecimal testBigDecimal1(BigDecimal num){
        System.out.println("GET test BigDecimal: " +num);
        BigDecimal result = new BigDecimal("98765432109876543210");
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/bigdecimal")
    BigDecimal testBigDecimal2(@RequestBody BigDecimal num){
        System.out.println("POST test BigDecimal: " +num);
        BigDecimal result = new BigDecimal("987654321098765432109876543210");
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/array")
    String[] testArray1(String[] array){
        System.out.println("GET test array : " + array);
        String[] result = new String[]{"ccc","bbb","aaa"};
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/array")
    String[] testArray2(@RequestBody String[] array){
        System.out.println("POST test array : " + array);
        String[] result = new String[]{"ccc","bbb","aaa"};
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/list")
    List<String> testList1(@RequestParam List<String> list){
        System.out.println("GET test list : " + list);
        List<String> result = Arrays.asList("ccc","bbb","aaa");
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/list")
    List<String> testList2(@RequestBody List<String> list){
        System.out.println("POST test list : " + list);
        List<String> result = Arrays.asList("ccc","bbb","aaa");
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/map")
    Map<String, Long> testMap1(@RequestParam Map<String, Long> map){
        System.out.println("GET test map : " + map);
        Map<String, Long> result = new HashMap<>();
        result.put("today", 1000L);
        result.put("tomorrow", 2000L);
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/map")
    Map<String, Long> testMap2(@RequestBody Map<String, Long> map){
        System.out.println("POST test map : " + map);
        Map<String, Long> result = new HashMap<>();
        result.put("today", 1001L);
        result.put("tomorrow", 2002L);
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/date")
    Date testDate1(Date date){
        System.out.println("GET test date: " + date);
        Date result = new Date();
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/date")
    Date testDate2(@RequestBody Date date){
        System.out.println("POST test date: " + date);
        Date result = new Date();
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/time")
    Time testTime1(@RequestParam Time time){
        System.out.println("GET test Time: " + time);
        Time result = new Time(11,11,11);
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/time")
    Time testDateTime2(@RequestBody Time time){
        System.out.println("GET test Time: " + time);
        Time result = new Time(11,11,11);
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/user")
    User testUser1(@RequestParam User user){
        System.out.println("GET test User : " + user);
        User result = new User(1002, "1002", 22, "2000-10-02", "china", 10002, 100002);
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/user")
    User testUser2(@RequestBody User user){
        System.out.println("POST test User : " + user);
        User result = new User(1002, "1002", 22, "2000-10-02", "china", 10002, 100002);
        System.out.println("return: " + result);
        return result;
    }


    @GetMapping("/localdate")
    LocalDate testLocalDate1(@RequestParam LocalDate date){
        System.out.println("GET test localdate date=" + date);
        LocalDate result = date.plusMonths(2);
        System.out.println("return: " + result);
        return result;
    }

    @PostMapping("/localdate")
    LocalDate testLocalDate2(@RequestBody LocalDate date){
        System.out.println("POST test localdate date=" + date);
        LocalDate result = date.plusMonths(2);
        System.out.println("return: " + result);
        return result;
    }

    @GetMapping("/localdate_")
    LocalDate testLocalDate3(){
        System.out.println("GET test localdate for back");
        LocalDate result = LocalDate.now();
        System.out.println("return: " + result);
        return result;
    }

    // ---------------------


    @PostMapping("/test102")
    User test2(int i, String s, List<Integer> l, Map<Integer, String> m, User user){
        System.out.println("POST test2 i=" + i + ", s=" + s + ", l=" + l + ",m=" + m + ",u=" + user);
        return user;
    }

}
