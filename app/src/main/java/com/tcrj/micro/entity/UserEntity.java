package com.tcrj.micro.entity;

/**
 * Created by Administrator on 2016/1/22.
 */
public class UserEntity {
    private String userId;
    private String userName;
    private String userTel;

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

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public UserEntity(String userId, String userName, String userTel) {
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
    }
}
