package com.learn.demo.serviceImp;

import com.learn.demo.DAO.UserDAO;
import com.learn.demo.entity.User;
import com.learn.demo.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    //使用@transactional可以让查询事务化。
    //注意select请求不会锁表，可能造成脏数据问题
    //可以使用for update锁行，避免查询到脏数据
    @Transactional
    public User findByID(int id) {
        User user1 = userDAO.findByID(id);
        User user2 = userDAO.findByID(id);
        return user1;
    }
}