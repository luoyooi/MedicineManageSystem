package com.dao;

import com.bean.Income;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-12 14:17
 */
public interface IncomeDao {
    public List<Income> selectIncomeForList(String year);
}
