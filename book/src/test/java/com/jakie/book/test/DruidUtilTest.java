package com.jakie.book.test;

import com.jakie.book.utils.DruidUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;


public class DruidUtilTest {

    @Test
    public void getConnection() {
        for(int i=0;i<100;i++) {
            Connection connection = DruidUtil.getConnection();
            System.out.println(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}