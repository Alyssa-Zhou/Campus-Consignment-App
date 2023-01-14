package com.swjtu.secondhand.mapper;

import com.swjtu.secondhand.entity.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Date;

@SpringBootTest
public class UserDetailMapperTests {
    @Autowired
    private IUserDetailMapper userDetailMapper;

    @Test
    public void insert() {
        UserDetail userDetail = new UserDetail();
        userDetail.setId("0000000003");
        userDetail.setRole("3");
        userDetail.setAvatar("/");
        userDetail.setIdentified("0");
        userDetail.setAlias("strawberry");
        userDetail.setEmail("123456@qq.com");
        userDetail.setDescription("咸鱼一枚呀");
        userDetail.setPhoneNumber("13456789999");
        userDetail.setCreateTime(Date.from(Instant.now()));
        userDetail.setUpdateTime(Date.from(Instant.now()));
        Integer rows = userDetailMapper.insert(userDetail);
        System.out.println("rows=" + rows);

    }

    @Test
    public void updateDetailById() {
        UserDetail userDetail = new UserDetail();
        userDetail.setId("0000000003");
        userDetail.setStudentNumber("2017111444");
        userDetail.setStudentCard("/");
        userDetail.setIdentified("1");
        userDetail.setRole("2");

        userDetail.setAvatar("/");
        userDetail.setAlias("strawberryyyyy");
        userDetail.setEmail("123456@qq.com");
//        userDetail.setDescription("咸鱼一枚呀");
//        userDetail.setPhoneNumber("13456789999");
        userDetail.setUpdateTime(Date.from(Instant.now()));
        Integer rows = userDetailMapper.updateDetailById(userDetail);
        System.out.println("rows=" + rows);

    }

    @Test
    public void updateAvatarById() {
        String id = "0000000003";
        String avatar = "/upload/avatar.png";
        Date updateTime = new Date();
        Integer rows = userDetailMapper.updateAvatarById(id, avatar,updateTime);
        System.out.println("rows=" + rows);
    }
}
