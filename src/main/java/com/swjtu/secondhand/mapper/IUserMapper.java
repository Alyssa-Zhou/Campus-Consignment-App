package com.swjtu.secondhand.mapper;

import com.swjtu.secondhand.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface IUserMapper {

    @Select("select * from user")
    List<User> findAll();

    /**
     * @description:注册新用户
     * @date: 2022/11/13
     * @param: user
     * @return: java.lang.Integer 受影响的行数
     */
    Integer insert(User user);

    /**
     * @description:查找当前用户名是否存在
     * @date: 2022/11/13
     * @param: username
     * @return: com.swjtu.secondhand.entity.User 如果没找到返回null
     */
    User findByUsername(String username);

    /**
     * 根据uid更新用户的密码
     * @param id 用户的id
     * @param password 新密码
     * @param updateTime 最后修改时间
     * @return 受影响的行数
     */
    Integer updatePasswordById(
            @Param("id") String id,
            @Param("password") String password,
            @Param("updateTime") Date updateTime
    );

    /**
     * 根据用户id查询用户数据
     * @param id 用户id
     * @return 匹配的用户数据，如果没有匹配的用户数据，则返回null
     */
    User findById(String id);


}
