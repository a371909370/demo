package com.learn.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis漏斗限流示例
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class FunnelRateLimiter {

    public static class Funnel {
        public int capacity;
        public float leakingRate;
        public int leftCapacity;
        public LocalDateTime lastLeakingTime;
        private DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG,FormatStyle.LONG);

        public Funnel(int capacity, float leakingRate){
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftCapacity = capacity;
            this.lastLeakingTime = LocalDateTime.now();
        }

        public void makeSpace() {
            LocalDateTime nowTime = LocalDateTime.now();
            Duration duration = Duration.between(lastLeakingTime, nowTime);
            long deltaTime = duration.toMillis()/1000;
            double deltaQuota = deltaTime * this.leakingRate;
            log.info("时间差："+deltaQuota);
            if (deltaQuota < 1) {
                return;
            }
            this.leftCapacity += deltaQuota;
            this.lastLeakingTime = nowTime;
            if (leftCapacity > capacity)
                leftCapacity = capacity;
        }

        public boolean watering(int quota) {
            makeSpace();
            if (leftCapacity >= quota) {
                leftCapacity -= quota;
                log.info("watering over now leftCapacity:" + leftCapacity +
                        "lastLeakingTime:" + dateTimeFormatter.format(lastLeakingTime));
                return true;
            }
            log.info("watering over now leftCapacity:" + leftCapacity +
                    "lastLeakingTime:" + dateTimeFormatter.format(lastLeakingTime));
            return false;
        }
    }

    Map<String, Funnel> funnelMap = new HashMap<>();

    public boolean isActionAllowed(RedisTemplate redisTemplate , String userid, String action,
                                   Integer capacity, Float leakingRate){
        StringBuilder key = new StringBuilder();
        key.append(userid);key.append("_");key.append(action);
        String user = key.toString();
        if(!funnelMap.containsKey(user)){
            funnelMap.put(user, new Funnel(capacity, leakingRate));
            return true;
        }else{
            return funnelMap.get(user).watering(1);
        }
    }

    @Test
    public void test() throws InterruptedException {
        FunnelRateLimiter redisRateLimited = new FunnelRateLimiter();
        for (int i = 0; i < 14; i++) {
            log.info("result"+(i+1)+":"+redisRateLimited.isActionAllowed(null, "user1", "comment",
                    10, 2.0f));
            Thread.sleep(100);
        }
    }

}
