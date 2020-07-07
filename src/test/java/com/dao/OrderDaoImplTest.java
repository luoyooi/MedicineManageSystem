package com.dao;

import com.bean.Order;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author shkstart
 * @create 2020-05-31 19:30
 */
public class OrderDaoImplTest {
    OrderDao o = new OrderDaoImpl();

    @Test
    public void test()
    {
        System.out.println(o.insertOrder(new Order(1, 3, new BigDecimal(12), new BigDecimal(140), 1590940800, "")));
    }

    @Test
    public void test2()
    {
        System.out.println(o.deleteOrderById(2));
    }

    @Test
    public void test3()
    {
        System.out.println(o.selectOrderForList());
    }
}
