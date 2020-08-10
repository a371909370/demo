package com.learn.demo.service;

import com.learn.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findByID(int id);

}
