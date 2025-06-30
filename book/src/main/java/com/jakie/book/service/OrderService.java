package com.jakie.book.service;

import com.jakie.book.pojo.Cart;
import com.jakie.book.pojo.Order;
import com.jakie.book.pojo.OrderItem;

import java.sql.SQLException;

public interface OrderService {
    String createOrder(Cart cart, Integer userId) throws SQLException;
}
