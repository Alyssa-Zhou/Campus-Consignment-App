package com.swjtu.secondhand.service.impl;

import com.swjtu.secondhand.entity.User;
import com.swjtu.secondhand.entity.UserDetail;
import com.swjtu.secondhand.mapper.IUserDetailMapper;
import com.swjtu.secondhand.mapper.IUserMapper;
import com.swjtu.secondhand.service.IUserDetailService;
import com.swjtu.secondhand.service.IUserService;
import com.swjtu.secondhand.service.ex.PasswordNotMatchException;
import com.swjtu.secondhand.service.ex.UpdateException;
import com.swjtu.secondhand.service.ex.UserDetailNotFoundException;
import com.swjtu.secondhand.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserDetailServiceImpl implements IUserDetailService {
    @Autowired
    private IUserDetailMapper userDetailMapper;
    @Autowired
    private IUserMapper userMapper;

    @Override
    public UserDetail getById(String id) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        UserDetail result = userDetailMapper.findById(id);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserDetailNotFoundException("用户数据不存在");
        }

        User resultUser = userMapper.findById(id);
        // 判断查询结果中的isDelete是否为1
        if (resultUser.getState() == "0") {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户不存在");
        }
//
//        // 创建新的User对象
//        UserDetail userDetail = new UserDetail();
//        // 将以上查询结果中的username/phone/email/gender封装到新User对象中
//        userDetail.setUsername(result.getUsername());
//        userDetail.setPhone(result.getPhone());
//        userDetail.setEmail(result.getEmail());
//        userDetail.setGender(result.getGender());

        // 返回新的User对象
        return result;
    }

    @Override
    public void changeInfo(String id, UserDetail userDetail) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findById(id);
        // 检查查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 检查查询结果中的isDelete是否为1
        if (result.getState() == "0") {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 向参数user中补全数据：uid
        userDetail.setId(id);
        // 向参数user中补全数据：modifiedTime(new Date())
        userDetail.setUpdateTime(new Date());
        // 调用userMapper的updateInfoByUid(User user)方法执行修改，并获取返回值
        Integer rows = userDetailMapper.updateDetailById(userDetail);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public void changeAvatar(String id, String avatar) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findById(id);
        // 检查查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }

        // 检查查询结果中的State是否为0
        if (result.getState().equals(0)) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        UserDetail userDetail = userDetailMapper.findById(id);
        // 判断查询结果是否为null
        if (userDetail == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserDetailNotFoundException("用户数据不存在");
        }

        // 创建当前时间对象
        Date now = new Date();
        // 调用userMapper的updateAvatarByUid()方法执行更新，并获取返回值
        Integer rows = userDetailMapper.updateAvatarById(id, avatar, now);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }
}
