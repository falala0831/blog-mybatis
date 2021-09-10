package com.bigbone.blog.service;

import com.bigbone.blog.dao.UserDao;
import com.bigbone.blog.entity.User;
import com.bigbone.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService的实现：核查用户身份
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
