package com.swjtu.secondhand.mapper;

import com.swjtu.secondhand.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private IUserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("user01");
        user.setPassword("123456");
        user.setResetQuestion("2");
        user.setResetAnswer("fsndhilubgd");
        user.setState("0");
        user.setCreateTime(Date.from(Instant.now()));
        user.setUpdateTime(Date.from(Instant.now()));
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUsername() {
        String username = "lower111";
        User result = userMapper.findByUsername(username);
        System.out.println(result);
    }


    @Test
    public void updatePasswordById() {
        String id = "0000000005";
        String password = "1234567";
        Date updateTime = new Date();
        Integer rows = userMapper.updatePasswordById(id, password, updateTime);
        System.out.println("受影响行数：rows=" + rows);
    }

    @Test
    public void findById() {
        String id = "0000000005";
        User result = userMapper.findById(id);
        System.out.println(result);
    }
}