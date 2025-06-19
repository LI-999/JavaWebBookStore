package com.jakie.book.dao.impl;

import com.jakie.book.dao.UserDao;
import com.jakie.book.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public User queryUserByUsername(String username) {
        String sql ="select * from t_user where username = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public Number saveUser(User user) {
        String sql ="insert into t_user values(?,?,?,?)";
        return update(sql,null,user.getUsername(),user.getPassword(),user.getEmail());
    }


    public void count(){
        String sql = "select count(*) from t_user";
        System.out.println(queryForSingleValue(sql));
    }
}
