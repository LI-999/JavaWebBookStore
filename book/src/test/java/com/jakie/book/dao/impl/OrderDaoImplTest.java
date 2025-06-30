package com.jakie.book.dao.impl;

import com.jakie.book.dao.OrderDao;
import com.jakie.book.pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImplTest {

    @Test
    void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        Order order = new Order(System.currentTimeMillis()+"",new Date(),new BigDecimal(100),0,1);
        orderDao.saveOrder(order);
    }
}