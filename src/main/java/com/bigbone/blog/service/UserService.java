package com.bigbone.blog.service;

import com.bigbone.blog.entity.User;

/**
 * 接口:核查管理员身份
 */
public interface UserService {
    User checkUser(String username, String password);
}
