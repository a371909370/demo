package com.learn.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Redis简单限流示例
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RedisRateLimited {

    @Autowired
    public RedisTemplate redisTemplate;

    //每个user的每个action在period分钟最多maxCount次
    public boolean isActionAllowed(RedisTemplate redisTemplate ,String userid, String action,
                                Integer period, Integer maxCount){
        log.info("进入"+redisTemplate);
        Long nowTime = System.currentTimeMillis();
        StringBuilder key = new StringBuilder();
        key.append(userid);key.append("_");key.append(action);
        String user = key.toString();
        log.info("key:"+user);
        redisTemplate.opsForZSet().removeRangeByScore(user, 0, nowTime-period*10000);
        long count = redisTemplate.opsForZSet().zCard(user);
        if(count<maxCount) {
            redisTemplate.opsForZSet().add(user, nowTime, nowTime);
            return true;
        }
        return false;
    }

    @Test
    public void exec() throws InterruptedException{
        RedisRateLimited redisRateLimited = new RedisRateLimited();
        for (int i = 0; i < 6; i++) {
            log.info("result"+(i+1)+":"+redisRateLimited.isActionAllowed(redisTemplate, "user1", "comment",
                            60, 5));
            Thread.sleep(1000);
        }
    }

}
