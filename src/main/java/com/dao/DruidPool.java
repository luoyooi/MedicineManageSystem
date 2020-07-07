package com.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author shkstart
 * @create 2020-05-30 14:33
 */
public class DruidPool {
    private static DataSource dataSource = null;

    static {
        try (InputStream is = DruidPool.class.getResourceAsStream("/db.properties")){

            // 读取配置文件
            Properties pro = new Properties();
            pro.load(is);

            // 根据配置文件，获取数据源
            dataSource = DruidDataSourceFactory.createDataSource(pro);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
