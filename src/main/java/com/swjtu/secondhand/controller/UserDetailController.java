package com.swjtu.secondhand.controller;

import com.swjtu.secondhand.controller.ex.*;
import com.swjtu.secondhand.entity.UserDetail;
import com.swjtu.secondhand.service.IUserDetailService;
import com.swjtu.secondhand.service.IUserService;
import com.swjtu.secondhand.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserDetailController extends BaseController {

    @Autowired
    private IUserDetailService userDetailService;

    @RequestMapping(value = "/users/detail",method = RequestMethod.GET)
    public JsonResult<UserDetail> getById(HttpSession session) {
        // 从HttpSession对象中获取uid
        String id = getIdFromSession(session);
        // 调用业务对象执行获取数据
        UserDetail data = userDetailService.getById(id);
        // 响应成功和数据
        return new JsonResult<UserDetail>(OK, data);
    }

    @RequestMapping(value = "/users/detail",method = RequestMethod.PATCH)
    public JsonResult<Void> changeInfo(@RequestBody UserDetail userDetail, HttpSession session) {
        // 从HttpSession对象中获取uid和username
        String id = getIdFromSession(session);
        // 调用业务对象执行修改用户资料
        userDetailService.changeInfo(id, userDetail);
        // 响应成功
        return new JsonResult<Void>(OK);
    }


    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    @RequestMapping(value = "/users/detail/avatar",method = RequestMethod.PATCH)
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs(); // 不存在则创建目录
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        String id = getIdFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        userDetailService.changeAvatar(id, avatar);

        // 返回成功头像路径
        return new JsonResult<String>(OK, avatar);
    }

}
