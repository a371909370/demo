package com.learn.demo.controller;

import com.learn.demo.entity.User;
import com.learn.demo.service.UserService;
import com.learn.demo.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/service")
public class ControllerTest {

    @Autowired
    public UserServiceImp userService;

    @RequestMapping("/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable int id) {
        System.out.println(userService.testIOC());
        return userService.findByID(id);
    }

}
