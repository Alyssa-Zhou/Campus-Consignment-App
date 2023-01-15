package com.swjtu.secondhand.controller;

import com.swjtu.secondhand.controller.ex.*;
import com.swjtu.secondhand.service.ex.*;
//import com.swjtu.secondhand.service.impl.ex.*;
import com.swjtu.secondhand.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/** 控制器类的基类 */
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;

    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessage("用户数据不存在");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessage("密码验证失败");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册失败，请联系系统管理员");
        }else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessage("密码修改失败！");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("上传文件为空！");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("上传文件大小错误！");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("上传文件类型错误！");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("上传文件状态错误！");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("上传文件错误！");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品不存在！");
        }

        return result;
    }

    /**
     * 从HttpSession对象中获取uid
     * @param session HttpSession对象
     * @return 当前登录的用户的id
     */
    protected final String getIdFromSession(HttpSession session) {
        return session.getAttribute("id").toString();
    }

    /**
     * 从HttpSession对象中获取用户名
     * @param session HttpSession对象
     * @return 当前登录的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
