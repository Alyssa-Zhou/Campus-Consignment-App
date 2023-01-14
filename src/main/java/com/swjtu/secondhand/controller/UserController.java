package com.swjtu.secondhand.controller;

import com.swjtu.secondhand.entity.User;
import com.swjtu.secondhand.service.IUserService;
import com.swjtu.secondhand.service.ex.InsertException;
import com.swjtu.secondhand.service.ex.UsernameDuplicateException;
import com.swjtu.secondhand.service.impl.UserServiceImpl;
import com.swjtu.secondhand.mapper.IUserMapper;
import com.swjtu.secondhand.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @Autowired
    IUserMapper userMapper;

    @RequestMapping(value = "/user")
    public List<User> userMapper(Model m){
        List<User> users = userMapper.findAll();
        return users;
    }

    @RequestMapping(value = "/hello")
    public String hello(){ return "Hello~"; }


    // 注册 json
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public JsonResult<Void> reg(@RequestBody User user){
        // 调用业务对象执行注册
        userService.reg(user);
        // 返回
        return new JsonResult<Void>(OK);
    }

    // 登录 json
    @RequestMapping(value = "/users/sessions",method = RequestMethod.POST)
    public JsonResult<User> login(@RequestBody Map<String, String> user, HttpSession session) {
        // 将接收的json参数转为对象user，并分别读取用户名与密码值
        String username = user.get("username");
        String password = user.get("password");

        // 调用业务对象的方法执行登录，并获取返回值
        User data = userService.login(username, password);

        //登录成功后，将uid和username存入到HttpSession中
        session.setAttribute("id", data.getId());
        session.setAttribute("username", data.getUsername());

        // 调试用输出
        System.out.println("输入：" + username + " " + password);
        System.out.println("Session中的id=" + getIdFromSession(session));
        System.out.println("Session中的username=" + getUsernameFromSession(session));

        // 将以上返回值和状态码OK封装到响应结果中并返回
        return new JsonResult<User>(OK, data);
    }

    // 修改密码
    @RequestMapping(value = "/users/password",method = RequestMethod.PATCH)
    public JsonResult<Void> changePassword(@RequestBody Map<String, String> password, HttpSession session) {
        // json对象中获取密码
        String oldPassword = password.get("oldPassword");
        String newPassword = password.get("newPassword");

        // 调用session.getAttribute("")获取uid和username
        String id = getIdFromSession(session);
        String username = getUsernameFromSession(session);

        // 调用业务对象执行修改密码
        userService.changePassword(id, username, oldPassword, newPassword);

        // 返回成功
        return new JsonResult<Void>(OK);
    }
}
