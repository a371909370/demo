package com.learn.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class User implements Serializable {
    public Integer id;
    public String name;
    public Integer age;
    public String email;
}
