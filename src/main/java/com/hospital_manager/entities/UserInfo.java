package com.hospital_manager.entities;

import java.util.Objects;

public class UserInfo {

    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private int age;
    private long roleId;

    public UserInfo(String firstname, String lastname, String login, String password, int age, long roleId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.age = age;
        this.roleId = roleId;
    }

    public UserInfo() {
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return age == userInfo.age && roleId == userInfo.roleId && Objects.equals(firstname, userInfo.firstname) && Objects.equals(lastname, userInfo.lastname) && Objects.equals(login, userInfo.login) && Objects.equals(password, userInfo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, login, password, age, roleId);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", roleId=" + roleId +
                '}';
    }
}
