package com.jakie.book.service.impl;

import com.jakie.book.dao.impl.UserDaoImpl;
import com.jakie.book.pojo.User;
import com.jakie.book.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void registerUser(User user) {
        if (user != null) {
            //获取当前username是否存在数据库中
            boolean b = existsUsername(user.getUsername());
            //b为false 可以注册
            if (!b) {
                new UserDaoImpl().saveUser(user);
                System.out.println("注册成功");
            } else {
                System.out.println("用户名已存在");
            }

        }
    }

    @Override
    public User login(User user) {
        if (user != null) {

            boolean b = existsUsername(user.getUsername());
            //b为true 说明数据库中存在该用户 则可以登录
            if (b) {
                User user1 = new UserDaoImpl().queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
                if (user1 != null) {
                    System.out.println("登录成功");
                    return user1;
                }
            } else {
                System.out.println("用户不存在");
            }


        }
        return null;

    }

    @Override
    public boolean existsUsername(String username) {
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            //返回值为true说明该用户名存在
            return userDao.queryUserByUsername(username) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
