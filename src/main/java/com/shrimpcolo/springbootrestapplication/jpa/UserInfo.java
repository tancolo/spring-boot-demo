package com.shrimpcolo.springbootrestapplication.jpa;

import java.io.Serializable;

/**
 * 创建用户 接收json 实体类
 */
public class UserInfo implements Serializable {
    private String name;
    private String role;

    // 非常重要，没有默认构造方法，@RequestBody如法解析成功
    public UserInfo() {
    }

    public UserInfo(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
