package com.swjtu.secondhand.mapper;
import com.swjtu.secondhand.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface IUserDetailMapper {

    /**
     * @description:插入新的用户资料
     * @date: 2022/12/10
     * @param: userDetail
     * @return: java.lang.Integer
     */
    Integer insert(UserDetail userDetail);

    /**
     * @description:根据信息表id查询
     * @date: 2022/12/10
     * @param: id
     * @return: com.swjtu.secondhand.entity.UserDetail
     */
    UserDetail findById(String id);

    /**
     * 根据userid更新用户资料
     * @param userDetail 封装了用户id和新个人资料的对象
     * @return 受影响的行数
     */
    Integer updateDetailById(UserDetail userDetail);

    /**
     * 根据uid更新用户的头像
     * @param id 用户的id
     * @param avatar 新头像的路径
     * @param updateTime 更新时间
     * @return 受影响的行数
     */
    Integer updateAvatarById(String id, String avatar, Date updateTime);

    /**
     * 根据uid更新用户的证件照照片
     * @param id 用户的id
     * @param studentCard 新头像的路径
     * @param updateTime 更新时间
     * @return 受影响的行数
     */
    Integer updateStudentCardById(String id, String studentCard, Date updateTime);
}
