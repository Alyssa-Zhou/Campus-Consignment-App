package com.swjtu.secondhand.entity;


//import com.baomidou.mybatisplus.extension.activerecord.Model;

//import java.io.Serializable;
import java.util.Date;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-11-06 21:53:08
 */

//@SuppressWarnings("serial")
// extends Model
public class User{
    //用户号
    private String id;
    //账户名
    private String username;
    //密码
    private String password;
    //密保问题
    private String resetQuestion;
    //密保答案
    private String resetAnswer;
    //用户状态
    private String state;
    //创建时间
    private Date createTime;
    //最近登录时间
    private Date updateTime;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetQuestion() {
        return resetQuestion;
    }

    public void setResetQuestion(String resetQuestion) {
        this.resetQuestion = resetQuestion;
    }

    public String getResetAnswer() {
        return resetAnswer;
    }

    public void setResetAnswer(String resetAnswer) {
        this.resetAnswer = resetAnswer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
//    @Override
//    protected Serializable pkVal() {
//        return this.id;
//    }
    }

