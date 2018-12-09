package com.starriddle.starter.springboot.feign.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starriddle.starter.springboot.feign.client.feign.ServerApi;

/**
 *
 * 使用@Controller，
 * 返回String时，会根据该String查找相应文件如html返回
 * 如找不到，则返回404
 *
 * 使用@RestController
 * 返回String时，直接将该String作为内容返回
 *
 * @author CYL
 * @date 2018-04-26
 */
@RestController
public class ServerController {

    @Autowired
    ServerApi server;

    @GetMapping("/index")
    String index(){
        System.out.println("GET from: /index");
        String result = "Hello World —— Feign Client";
        System.out.println("will return: " + result);
        return result;
    }

    @GetMapping("/server")
    String server() {
        System.out.println("GET from: /server");
        boolean ret = true;
        String result;

        try {
            System.out.println("GET to server/getIndex");
            result = server.getIndex();
            System.out.println("return: " + result);
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        }

        try {
            System.out.println("POST to server/PostIndex");
            result = server.postIndex();
            System.out.println("return: " + result);
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        }

        try {
            System.out.println("GET to server/GetHello");
            result = server.getHello();
            System.out.println("return: " + result);
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        }

        try {
            System.out.println("POST to server/PostHello");
            result = server.postHello();
            System.out.println("return: " + result);
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        }

        if (ret){
            result = "http to server successfully!";
        } else {
            result = "http to server failure!";
        }
        System.out.println("will return: " + result);
        return result;
    }

}
