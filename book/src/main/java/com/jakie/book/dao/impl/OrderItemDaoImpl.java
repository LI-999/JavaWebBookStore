package com.jakie.book.dao.impl;

import com.jakie.book.dao.OrderItemDao;
import com.jakie.book.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_orderItem(name,count,price,totalPrice,orderId) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(Integer orderId){
        String sql = "select * from  t_orderItem where id =?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
