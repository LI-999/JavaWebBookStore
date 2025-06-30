package com.jakie.book.dao;

import com.jakie.book.pojo.Order;
import javafx.scene.layout.BorderWidths;

import java.util.List;

public interface OrderDao {
    //保存订单
    int saveOrder(Order order);

    //查询全部订单
    List<Order> queryOrders();

    //修改订单状态
    int changeOrderStatus(Integer orderId, Short status);

    //根据用户编号查询订单信息
    List<Order> queryOrderByUserId(Integer userId);


}
