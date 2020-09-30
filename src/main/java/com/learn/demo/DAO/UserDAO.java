package com.learn.demo.DAO;

import com.learn.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {
    User findByID(int id);
    List<User> selectAll();
}
