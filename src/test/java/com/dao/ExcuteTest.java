package com.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author shkstart
 * @create 2020-06-06 21:36
 */
public class ExcuteTest extends BaseDao{

    @Test
    public void test() throws Exception
    {
        String sql = "SELECT * FROM employee LIMIT 30;";
        // 获取连接
        Connection conn = DruidPool.getDataSource().getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

    }
}
