package com.swjtu.secondhand.service;

import com.swjtu.secondhand.entity.UserDetail;
import com.swjtu.secondhand.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDetailServiceTests {

    @Autowired
    private IUserDetailService iUserDetailService;

    @Test
    public void getById() {
        try {
            String id = "0000000004";
            UserDetail userDetail = iUserDetailService.getById(id);
            System.out.println(userDetail);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo() {
        try {
            String id = "0000000004";
            UserDetail userDetail = new UserDetail();
            userDetail.setStudentNumber("2017111444");
            userDetail.setStudentCard("/");
            userDetail.setIdentified("1");
            userDetail.setRole("2");

            userDetail.setAvatar("/");
            userDetail.setAlias("strawberryyyyy");
            userDetail.setEmail("123456@qq.com");
            iUserDetailService.changeInfo(id, userDetail);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeAvatar() {
        try {
            String id = "0000000004";
            String avatar = "/upload/avatar.png";
            iUserDetailService.changeAvatar(id, avatar);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
