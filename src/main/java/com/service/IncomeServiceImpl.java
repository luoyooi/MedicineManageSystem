package com.service;

import com.bean.Income;
import com.dao.IncomeDao;
import com.dao.IncomeDaoImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-06-12 14:33
 */
public class IncomeServiceImpl implements IncomeService{
    // 月的数量
    private static final Integer MONTH_NUM = 12;
    // 信息条数
    private static final Integer INFO_NUM = 4;

    private IncomeDao incomeDao = new IncomeDaoImpl();

    @Override
    public Map<String, Object> generateIncomeData(String year) {
        // 如果没有参数，直接返回
        if (year == null)
            return null;

        List<Income> incomes = incomeDao.selectIncomeForList(year);


        String[] months = new String[MONTH_NUM];
        BigDecimal[] costSum = new BigDecimal[MONTH_NUM];
        BigDecimal[] priceSum = new BigDecimal[MONTH_NUM];
        BigDecimal[] profit = new BigDecimal[MONTH_NUM];

        for (int i = 0; i < incomes.size(); i++) {
            months[i] = incomes.get(i).getMonths();
            costSum[i] = incomes.get(i).getCostSum();
            priceSum[i] = incomes.get(i).getPriceSum();
            profit[i] = priceSum[i].subtract(costSum[i]);
        }

        // 初始化容器
        Map<String, Object> map = new HashMap(INFO_NUM);
        map.put("months", months);
        map.put("costSum", costSum);
        map.put("priceSum", priceSum);
        map.put("profit", profit);

        return map;
    }
}
