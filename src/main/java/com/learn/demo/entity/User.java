package com.learn.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    public int id;
    public String name;
    public int age;
    public String email;
}
