package com.jakie.book.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtil {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {

        Properties properties = new Properties();
        try {
            properties.load(DruidUtil.class.getClassLoader().getResourceAsStream("mysql.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
//        try {
//            return dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        //确保在释放数据库连接前使用的是同一连接   引入MySQL事务
        Connection connection = conns.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                //开启事务 手动提交
                connection.setAutoCommit(false);

                conns.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static DataSource getDatasource() {
        return dataSource;
    }


    public static void closeConnectionAndCommit() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        conns.remove();
    }

    public static void closeConnectionAndRollback() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        conns.remove();

    }


//    public static void close(Connection conn, PreparedStatement ps) {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (ps != null) {
//            try {
//                ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
//        close(conn, ps);
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
