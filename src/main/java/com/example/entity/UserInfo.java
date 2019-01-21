package com.example.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto
 * @since 2019-01-21
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 删除标志：0-未删除 1-已删除
     */
    private Integer isDel;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", age=" + age +
        ", email=" + email +
        ", addTime=" + addTime +
        ", modifyTime=" + modifyTime +
        ", isDel=" + isDel +
        "}";
    }
}
