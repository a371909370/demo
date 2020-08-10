//package com.learn.demo;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.learn.demo.entity.User;
//import com.learn.demo.mapper.UserMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//class DemoApplicationTests {
//
//	@Autowired
//	private UserMapper userMapper;
//
//	@Test
//	void test() {
//		List<User> userList = userMapper.selectList(null);
//		User user = userMapper.selectById(1);
//		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//		System.out.println(user);
//		userList.forEach(System.out::println);
//	}
//
//}
