package com.starriddle.starter.springboot.swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * index
 *
 * @author chen
 */
@RestController
@Api(value = "index")
public class IndexController {
    
    @RequestMapping("/")
    public String home() {
        return "Hello Spring Boot Swagger2!";
    }

    @RequestMapping("/swagger2/health")
    public String health() {
        return "success";
    }

    @RequestMapping("/swagger2/test")
    @ApiOperation(value = "测试", notes = "测试接口", tags = {"init"})
    public String test() {
        return "test";
    }

}
