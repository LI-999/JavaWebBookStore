package com.jakie.book.dao;

import com.jakie.book.pojo.User;

public interface UserDao {
    /**
     * 通过用户名和密码查找用户
     * */
    public User queryUserByUsernameAndPassword(String name,String password);

    public User queryUserByUsername(String name);

    public Number saveUser(User user);
}
