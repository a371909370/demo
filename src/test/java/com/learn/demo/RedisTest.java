package com.learn.demo;

import com.learn.demo.util.RedisUtil;
import org.junit.Test;

public class RedisTest {

    @Test
    public void test(){
        RedisUtil redisUtil = new RedisUtil();
        System.out.println(redisUtil.keys("a"));
    }
}
