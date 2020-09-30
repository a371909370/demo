package com.learn.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.learn.demo.entity.User;
import com.learn.demo.serviceImp.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RedisDelayQueue {

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    public UserServiceImp userService;

    public String key = "user";

    @Test
    public void delay(){
        User user = userService.findByID(1+new Random().nextInt(5));
        redisTemplate.opsForZSet()
                .add(key, user, System.currentTimeMillis()+5000);
    }

    @Test
    public void loop() {
        while(!Thread.interrupted()){
            Set set = redisTemplate.opsForZSet()
                    .rangeByScore(key, 0, System.currentTimeMillis(), 0 ,-1);
            log.info("进入");
            if(set.isEmpty()){
                try {
                    log.info("休息");
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    break;
                }
            }else {
                JSONObject jsonObject = (JSONObject)set.iterator().next();
                List<User> list = new ArrayList<>();
                list.add(jsonObject.toJavaObject(User.class));
                User user = list.get(0);
                log.info("To be delete Object: "+user);
                Long result = redisTemplate.opsForZSet().remove(key, user);
                if(result>0){
                    log.info("已删除"+user);
                }else{
                    log.warn("删除失败。返回值："+result);
                }
            }
        }
    }

    @Test
    public void loopThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loop();
            }
        });
        thread.start();
    }

    @Test
    public void zSetAddElement(){
        try {
            List<User> list = userService.selectAll();
            log.info(JSON.toJSONString(list.get(0)));
            list.stream().map(x -> redisTemplate.opsForZSet().add(key, x, System.currentTimeMillis())).count();
            log.info("成功插入"+list.size()+"条数据");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
