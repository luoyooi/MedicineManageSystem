package com.dao;

import com.bean.Income;
import org.junit.Test;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-12 14:25
 */
public class IncomeDaoTest {
    @Test
    public void test()
    {
        IncomeDaoImpl id = new IncomeDaoImpl();

        List<Income> incomes = id.selectIncomeForList("2020");

        System.out.println(incomes);
    }
}
