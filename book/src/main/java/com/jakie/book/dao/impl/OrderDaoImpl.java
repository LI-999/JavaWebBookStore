package com.jakie.book.dao.impl;

import com.jakie.book.dao.OrderDao;
import com.jakie.book.pojo.Order;
import com.jakie.book.utils.DruidUtil;

import java.sql.Connection;
import java.util.List;

public class OrderDaoImpl extends  BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(orderid,createTime,price,status,userId) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select * from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public int changeOrderStatus(Integer orderId, Short status) {
        String sql = "update t_order set status = ? where orderId = ?";
        return update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select * from t_order where userId = ?";
        return queryForList(Order.class,sql,userId);
    }
}
