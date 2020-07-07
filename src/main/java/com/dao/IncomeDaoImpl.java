package com.dao;

import com.bean.Income;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-12 14:18
 */
public class IncomeDaoImpl extends BaseDao implements IncomeDao{

    @Override
    public List<Income> selectIncomeForList(String year) {
        String sql = "SELECT months, cost_sum AS costSum, price_sum AS priceSum \n" +
                "FROM income\n" +
                "where months like ? order by months;";

        return this.queryForList(Income.class, sql, year + "%");
    }
}
