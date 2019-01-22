package com.starriddle.starter.springboot.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author CYL
 * @date 2019-01-17
 */
@RestController
@Slf4j
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("set")
    public String setValue(){
        String key = "name";
        redisTemplate.opsForValue().set(key, "test");
        log.info("redis insert……");
        Object value = redisTemplate.opsForValue().get("name");
        log.info("redis get: key={}, value={}", key, value);
        return value.toString();
    }

    @RequestMapping("delete")
    public Boolean delete(){
        String key = "name";
        Boolean success = redisTemplate.delete(key);
        log.info("redis delete: key={}, success={}", key, success);
        return success;
    }
}
