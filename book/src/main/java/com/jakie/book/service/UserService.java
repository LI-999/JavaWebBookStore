package com.jakie.book.service;

import com.jakie.book.pojo.User;

public interface UserService {
    public void registerUser(User user);

    public User login(User user);

    public boolean existsUsername(String username);
}
