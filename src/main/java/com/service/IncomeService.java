package com.service;

import java.util.Map;

/**
 * @author shkstart
 * @create 2020-06-12 14:30
 */
public interface IncomeService {
    // 构造数据分析所需数据
    public Map<String, Object> generateIncomeData(String year);
}
