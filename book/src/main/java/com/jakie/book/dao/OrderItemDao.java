package com.jakie.book.dao;

import com.jakie.book.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {
    //保存订单项
    int saveOrderItem(OrderItem orderItem) throws SQLException;


    /**
     * @Author jakie
     * @Description //TODO 根据订单号查询订单明细
     * @Date 17:01 2025/6/28
     * @Param
     * @return
     **/
    List<OrderItem> queryOrderItemByOrderId(Integer orderId) throws SQLException;
}
