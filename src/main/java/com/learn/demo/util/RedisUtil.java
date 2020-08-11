package com.learn.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisUtil {

    @Autowired
    public RedisTemplate redisTemplate;

    public Set<String> keys(String keys) {
        try {
            return redisTemplate.keys(keys);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            System.out.println("出错了");
            e.printStackTrace();
            return false;
        }
    }

}
