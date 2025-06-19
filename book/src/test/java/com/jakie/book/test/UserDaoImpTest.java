package com.jakie.book.test;

import com.jakie.book.dao.impl.UserDaoImpl;
import com.jakie.book.dao.UserDao;
import com.jakie.book.pojo.User;
import org.junit.jupiter.api.Test;


class UserDaoImpTest {

    UserDao user = new UserDaoImpl();
    @Test
    void queryUserByUsernameAndPassword() {
        User admin = user.queryUserByUsernameAndPassword("admin","admin");
        if(admin==null){
            System.out.println("用户不存在");
        }else{
            System.out.println("用户存在");
        }
    }

    @Test
    void queryUserByUsername() {
        User admin = user.queryUserByUsername("admin");
        if(admin==null){
            System.out.println("用户不存在");
        }else{
            System.out.println("用户存在");
        }
    }

    @Test
    void saveUser() {
        User user1 = new User(null, "zhangsan", "123456", "zhangsan@qq.com");
        System.out.println(user.saveUser(user1));
    }
}