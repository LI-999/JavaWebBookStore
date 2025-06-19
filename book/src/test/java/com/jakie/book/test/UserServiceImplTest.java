package com.jakie.book.test;

import com.jakie.book.pojo.User;
import com.jakie.book.service.UserService;
import com.jakie.book.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    UserService us = new UserServiceImpl();
    @Test
    void registerUser() {
        us.registerUser(new User(null,"admin123","admin123","admin@qq.com"));
    }

    @Test
    void login() {
        us.login(new User(null,"admin1235","admin123","admin@qq.com"));
    }

    @Test
    void existsUsername() {
    }
}