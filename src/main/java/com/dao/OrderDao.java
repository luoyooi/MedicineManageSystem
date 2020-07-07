package com.dao;

import com.bean.Medicine;
import com.bean.Order;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 19:08
 */
public interface OrderDao {
    // 插入药品订单信息，返回历史记录id
    public Integer insertOrder(Order order);

    // 查询药品订单信息
    public List<Order> selectOrderForList();

    // 查询订单历史信息，附带日期参数
    public List selectOrderHistory(Integer begin, Integer end);

    // 删除药品订单信息
    public Integer deleteOrderById(Integer order_id);
}
