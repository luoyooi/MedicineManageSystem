package com.service;

import com.dao.OrderDao;
import com.dao.OrderDaoImpl;
import com.dao.PutStorageDao;
import com.dao.PutStorageDaoImpl;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-09 16:06
 */
public class AnalyzeServiceImpl implements AnalyzeService{

    private PutStorageDao psd = new PutStorageDaoImpl();
    private OrderDao od = new OrderDaoImpl();

    // 获取库存历史记录
    @Override
    public List getStorageHistory(Integer begin, Integer end) {

        return psd.selectPutStorageHistory(begin, end);
    }

    // 获取订单历史记录
    @Override
    public List getOrderHistory(Integer begin, Integer end) {
        return od.selectOrderHistory(begin, end);
    }
}
