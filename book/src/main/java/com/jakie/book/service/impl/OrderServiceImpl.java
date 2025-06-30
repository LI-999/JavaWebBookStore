package com.jakie.book.service.impl;

import com.jakie.book.dao.OrderDao;
import com.jakie.book.dao.OrderItemDao;
import com.jakie.book.dao.impl.OrderDaoImpl;
import com.jakie.book.dao.impl.OrderItemDaoImpl;
import com.jakie.book.pojo.Cart;
import com.jakie.book.pojo.CartItem;
import com.jakie.book.pojo.Order;
import com.jakie.book.pojo.OrderItem;
import com.jakie.book.service.OrderService;

import javax.print.DocFlavor;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        //时间戳+用户名 唯一性
        String orderid = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderid,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        int i = 12 / 0;
        for(Map.Entry<Integer, CartItem>entry: cart.getItemMap().entrySet()){
            CartItem value = entry.getValue();
            OrderItem orderItem = new OrderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalPrice(),orderid);
            orderItemDao.saveOrderItem(orderItem);
        }

        cart.clear();
        return orderid;
    }
}
