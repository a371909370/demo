package com.learn.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    public Integer id;
    public String name;
    public Integer age;
    public String email;
}
