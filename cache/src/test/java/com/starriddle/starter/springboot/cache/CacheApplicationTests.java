package com.starriddle.starter.springboot.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author CYL
 * @date 2019-01-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringJUnitWebConfig
@Slf4j
public class CacheApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testString(){
        String key = "string";
        Object result = redisTemplate.opsForValue().get(key);
        log.info("before set: key={}, value={}", key, result);
        // 向string设置value
        redisTemplate.opsForValue().set(key, "测试");
        result = redisTemplate.opsForValue().get(key);
        log.info("after set: key={}, value={}", key, result);
        // 删除key，key存在且被成功删除，返回true，否则false
        Boolean success = redisTemplate.delete(key);
        log.info("delete key: {}", success);
        success = redisTemplate.delete(key);
        log.info("delete key: {}", success);
    }

    @Test
    public void testList(){
        String key = "list";
        List<String> values = new ArrayList<>();
        values.add("张三");
        values.add("李四");
        values.add("李四");
        values.add("王五");
        values.add("赵六");
        // 向values列表左添加值
        values.forEach(value -> redisTemplate.opsForList().leftPush(key, value));
        Object result = redisTemplate.opsForList().range(key, 0, 10);
        log.info("after push: key={}, value={}", key, result);
        // 向values列表右添加值
        redisTemplate.opsForList().rightPush(key, "王五");
        result = redisTemplate.opsForList().range(key, 0, 10);
        log.info("after push: key={}, value={}", key, result);
        // 向values列表 替换 指定坐标的值
        redisTemplate.opsForList().set(key, 2, "赵六");
        result = redisTemplate.opsForList().range(key, 0, 10);
        log.info("after set: key={}, value={}", key, result);
        // 坐标超出当前列表坐标范围[0,2]
//        redisTemplate.opsForList().set(key, 3, "王五");

        // 从values列表移除前count个指定的值，
        // count>0从左开始删除，count<0从右边开始删除，count=0删除所有指定值
        redisTemplate.opsForList().remove(key, 1, "张三");
        result = redisTemplate.opsForList().range(key, 0, 10);
        log.info("after left remove: key={}, value={}", key, result);
        redisTemplate.opsForList().remove(key, -2, "赵六");
        result = redisTemplate.opsForList().range(key, 0, 10);
        log.info("after right remove: key={}, value={}", key, result);
        redisTemplate.opsForList().remove(key, 0, "王五");
        result = redisTemplate.opsForList().range(key, 0, 10);
        log.info("after all remove: key={}, value={}", key, result);
    }

    @Test
    public void testSet(){
        String key = "set";
        List<String> values = new ArrayList<>();
        values.add("张三");
        values.add("李四");
        values.add("李四");
        values.add("王五");
        values.add("赵六");
        Object result = redisTemplate.opsForSet().members(key);
        log.info("before insert: key={}, value={}", key, result);
        // 向set添加值：无序，不可重复，重复值不再添加
        values.forEach(value -> log.info("add {} into {}", redisTemplate.opsForSet().add(key, value), key));
        result = redisTemplate.opsForSet().members(key);
        log.info("after insert: key={}, value={}", key, result);
        Long count = redisTemplate.opsForSet().remove(key, "张三", "李四");
        log.info("remove {} from {}", count, key);
        result = redisTemplate.opsForSet().members(key);
        log.info("after remove: key={}, value={}", key, result);
    }

    @Test
    public void testHash() {
        String key = "hash";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 20);
        map.put("address", "beijing");
        map.put("date", new Date());
        log.info("{}",map);
        Set<Object> keys = redisTemplate.opsForHash().keys(key);
        log.info("before insert: key={}, keys={}", key, keys);
        if (keys != null) {
            List<Object> values = redisTemplate.opsForHash().values(key);
            log.info("values={}", values);
        }
        // 向hash添加map的所有键值对
        redisTemplate.opsForHash().putAll(key, map);
        // 获取key对应map的所有键集合
        keys = redisTemplate.opsForHash().keys(key);
        log.info("after insert: key={}, keys={}", key, keys);
        if (keys != null && !keys.isEmpty()) {
            // 获取key对应map的所有值列表
            List<Object> values = redisTemplate.opsForHash().values(key);
            log.info("values={}", values);
        }
        // 向hash设置一对键值对，如键不存在则添加，存在则替换值
        redisTemplate.opsForHash().put(key, "phone", "10086");
        redisTemplate.opsForHash().put(key, "address", "shanghai");
        keys = redisTemplate.opsForHash().keys(key);
        log.info("after modify: key={}, keys={}", key, keys);
        if (keys != null && !keys.isEmpty()) {
            List<Object> values = redisTemplate.opsForHash().values(key);
            log.info("values={}", values);
        }
        // 移除hash对应map中指定的键值对，如不存在则忽略，返回移除成功的键值对数量
        Long count = redisTemplate.opsForHash().delete(key, "age", "kkk");
        log.info("remove {} from {}", count, key);
        keys = redisTemplate.opsForHash().keys(key);
        log.info("after delete: key={}, keys={}", key, keys);
        if (keys != null && !keys.isEmpty()) {
            List<Object> values = redisTemplate.opsForHash().values(key);
            log.info("values={}", values);
        }
    }

}
