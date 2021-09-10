package com.bigbone.blog.dao;

import com.bigbone.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao{
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
