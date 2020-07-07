package com.dao;

import org.junit.Test;

import java.sql.SQLException;

/**
 * @author shkstart
 * @create 2020-05-30 14:58
 */
public class DruidPoolTest {
    @Test
    public void test() throws SQLException {
        System.out.println(DruidPool.getDataSource().getConnection());
    }
}
