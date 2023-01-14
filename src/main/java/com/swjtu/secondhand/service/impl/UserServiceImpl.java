package com.swjtu.secondhand.service.impl;

import com.swjtu.secondhand.entity.User;
import com.swjtu.secondhand.mapper.IUserMapper;
import com.swjtu.secondhand.service.IUserService;
import com.swjtu.secondhand.service.ex.PasswordNotMatchException;
import com.swjtu.secondhand.service.ex.ServiceException;
import com.swjtu.secondhand.service.ex.UpdateException;
import com.swjtu.secondhand.service.ex.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        // 调用持久层的User findByUsername(String username)方法，根据用户名查询用户数据
        User result = userMapper.findByUsername(username);
        if (result != null) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new ServiceException("尝试注册的用户名[" + username + "]已经被占用");
        }
        // 创建当前时间对象
        Date now = new Date();
        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        // 补全数据：保存盐值
        user.setSalt(salt);
        // 补全数据：状态
        user.setState("0");
        // 补全数据：4项日志属性
        user.setCreateTime(Date.from(Instant.now()));
        user.setCreateTime(Date.from(Instant.now()));

        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        Integer rows = userMapper.insert(user);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new ServiceException("添加用户数据出现未知错误，请联系系统管理员");
        }
    }
    /**
     * 执行密码加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密文
     */
    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    @Override
    public User login(String username, String password) {
        // 调用userMapper的findByUsername()方法，根据参数username查询用户数据
        User result = userMapper.findByUsername(username);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在的错误");
        }

        // 判断查询结果中的state是否为0 即注销
        if (result.getState() == "0") {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在的错误");
        }

        // 从查询结果中获取盐值
        String salt = result.getSalt();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String md5Password = getMd5Password(password, salt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!result.getPassword().equals(md5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("密码验证失败的错误");
        }

        // 创建新的User对象
        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setId(result.getId());
        user.setUsername(result.getUsername());
        // 返回新的user对象
        return user;
    }

    @Override
    public void changePassword(String id, String username, String oldPassword, String newPassword) {
        // 调用userMapper的findById()方法，根据参数id查询用户数据
        User result = userMapper.findById(id);
        // 检查查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 检查查询结果中的State是否为0
        if (result.getState().equals(0)) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }

        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 将参数oldPassword结合盐值加密，得到oldMd5Password
        String oldMd5Password = getMd5Password(oldPassword, salt);
        // 判断查询结果中的password与oldMd5Password是否不一致
        if (!result.getPassword().contentEquals(oldMd5Password)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("原密码错误");
        }

        // 将参数newPassword结合盐值加密，得到newMd5Password
        String newMd5Password = getMd5Password(newPassword, salt);
        // 创建当前时间对象
        Date now = new Date();
        // 调用userMapper的updatePasswordByUid()更新密码，并获取返回值
        Integer rows = userMapper.updatePasswordById(id, newMd5Password,now);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }


}
