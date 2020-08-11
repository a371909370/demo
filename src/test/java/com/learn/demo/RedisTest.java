package com.learn.demo;

import com.learn.demo.DAO.UserDAO;
import com.learn.demo.entity.User;
import com.learn.demo.service.UserService;
import com.learn.demo.serviceImp.UserServiceImp;
import com.learn.demo.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    public RedisUtil redisUtil;

    @Autowired
    public UserServiceImp userService;

    @Test
    public void test(){
        User user = userService.findByID(1);
        System.out.println(redisUtil.set("a", user));
        System.out.println(redisUtil.keys("a"));
    }
}
