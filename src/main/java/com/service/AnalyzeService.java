package com.service;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-09 16:05
 */
public interface AnalyzeService {
    // 库存数据分析
    public List getStorageHistory(Integer begin, Integer end);

    // 订单数据分析
    public List getOrderHistory(Integer begin, Integer end);
}
