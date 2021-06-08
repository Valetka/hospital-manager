package com.hospital_manager.controllers.builder;

import com.hospital_manager.entities.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class UserInfoBuilder {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";

    private UserInfo userInfo;


    public UserInfoBuilder() {
        this.userInfo = new UserInfo();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void buildUserInfo(HttpServletRequest request) {
        userInfo.setLogin(request.getParameter(LOGIN));
        userInfo.setPassword(request.getParameter(PASSWORD));
        userInfo.setFirstname(request.getParameter(FIRSTNAME));
        userInfo.setLastname(request.getParameter(LASTNAME));
    }

    public UserInfoBuilder setRoleId( int roleId){
        userInfo.setRoleId(roleId);
        return this;
    }
}
