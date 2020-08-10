package com.learn.demo.DAO;

import com.learn.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    User findByID(int id);
}
