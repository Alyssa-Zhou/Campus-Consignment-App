package com.swjtu.secondhand.service;

import com.swjtu.secondhand.entity.UserDetail;

public interface IUserDetailService {
    /**
     * @description:根据id查找用户信息
     * @date: 2022/12/10
     * @param: id
     * @return: com.swjtu.secondhand.entity.UserDetail
     */
    UserDetail getById(String id);

    /**
     * 修改用户资料
     * @param id 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param userDetail 用户的新的数据
     */
    void changeInfo(String id, UserDetail userDetail);

    /**
     * 修改用户头像
     * @param id 当前登录的用户的id
     * @param avatar 用户的新头像的路径
     */
    void changeAvatar(String id, String avatar);
}
