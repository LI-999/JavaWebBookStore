package com.jakie.book.dao.impl;

import com.jakie.book.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql,Object...args){
        Connection connection = DruidUtil.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DruidUtil.close(connection,null);
        }
        return -1;
    }


    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        Connection connection = DruidUtil.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DruidUtil.close(connection,null);
        }
        return null;
    }

    public <T> List<T> queryForList(Class<T>type,String sql,Object...args){
        Connection connection = DruidUtil.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DruidUtil.close(connection, null);
        }
        return null;
    }


    public Number queryForSingleValue(String sql,Object...args){
        Connection connection = DruidUtil.getConnection();
        try {
            return (queryRunner.query(connection,sql,new ScalarHandler<>(),args));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DruidUtil.close(connection,null);
        }
        return null;

    }
}
