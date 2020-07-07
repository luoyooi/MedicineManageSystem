package com.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-05-26 11:56
 */
public abstract class BaseDao {

    // 根据SQL查询出对应的数据行
    public List querySelect(String sql, Object...args) {
        // 获取连接
        try(Connection conn = DruidPool.getDataSource().getConnection();)
        {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            ResultSet rs = ps.executeQuery();

            // 返回的数据信息
            ResultSetMetaData metaData = rs.getMetaData();


            List list = new ArrayList();
            while (rs.next())
            {
                // 初始化单行的容器
                Map map = new HashMap(metaData.getColumnCount());

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    map.put(metaData.getColumnLabel(i), rs.getObject(i));
                }

                list.add(map);
                map = null;
            }

            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通用的增删改操作
     * @param sql
     * @param args
     */
    protected int execute(String sql, Object...args){
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            // 获取连接
            conn = DruidPool.getDataSource().getConnection();

            // 预编译sql
            ps = conn.prepareStatement(sql);

            // 设置占位符的值
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 执行SQL
            ps.execute();

            return ps.getUpdateCount();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 关闭打开的资源
            closeSource(conn, ps);
        }

        return 0;
    }

    /**
     * 通用增删改操作，支持事务
     * @param sql
     * @param args
     */
    protected void executeTx(Connection conn, String sql, Object...args){
        PreparedStatement ps = null;

        try {

            // 预编译sql
            ps = conn.prepareStatement(sql);

            // 设置占位符的值
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 执行SQL
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 关闭打开的资源
            closeSource(null, ps);
        }
    }

    /**
     * 通用的查询一条数据的方法
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    protected  <T> T query(Class<T> clazz,String sql, Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 获取连接
            conn = DruidPool.getDataSource().getConnection();

            // 预编译SQL
            ps = conn.prepareStatement(sql);

            // 赋值
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 获取结果集
            rs = ps.executeQuery();

            // 获取返回的结果集的数据
            ResultSetMetaData metaData = rs.getMetaData();

            // 如果有数据，取一行构造对象
            if (rs.next())
            {
                // 构造装数据的对象
                T t = clazz.newInstance();

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String colLabel = metaData.getColumnLabel(i + 1);
                    Object colValue = rs.getObject(i + 1);

                    // 暴力反射获取属性值
                    Field filed = clazz.getDeclaredField(colLabel);
                    filed.setAccessible(true);

                    // 为属性赋值
                    filed.set(t, colValue);
                }

                return t;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeSource(conn, ps, rs);
        }

        return null;

    }

    /**
     * 通用的查询一条数据的方法，支持事务
     * @param conn
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    protected  <T> T queryTx(Connection conn, Class<T> clazz,String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 预编译SQL
            ps = conn.prepareStatement(sql);

            // 赋值
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 获取结果集
            rs = ps.executeQuery();

            // 获取返回的结果集的数据
            ResultSetMetaData metaData = rs.getMetaData();

            // 如果有数据，取一行构造对象
            if (rs.next())
            {
                // 构造装数据的对象
                T t = clazz.newInstance();

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String colLabel = metaData.getColumnLabel(i + 1);
                    Object colValue = rs.getObject(i + 1);

                    // 暴力反射获取属性值
                    Field filed = clazz.getDeclaredField(colLabel);
                    filed.setAccessible(true);

                    // 为属性赋值
                    filed.set(t, colValue);
                }

                return t;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeSource(null, ps, rs);
        }

        return null;

    }

    /**
     * 通用的查询多条数据的方法
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    protected  <T> List<T> queryForList(Class<T> clazz, String sql, Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 获取连接
            conn = DruidPool.getDataSource().getConnection();

            // 预编译SQL
            ps = conn.prepareStatement(sql);

            // 赋值
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 获取结果集
            rs = ps.executeQuery();

            // 获取返回的结果集的数据
            ResultSetMetaData metaData = rs.getMetaData();

            // 装数据的集合
            List<T> list = new ArrayList<>();

            // 如果有数据，取一行构造对象
            while (rs.next())
            {
                // 构造装数据的对象
                T t = clazz.newInstance();

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String colLabel = metaData.getColumnLabel(i + 1);
                    Object colValue = rs.getObject(i + 1);

                    // 暴力反射获取属性值
                    Field filed = clazz.getDeclaredField(colLabel);
                    filed.setAccessible(true);

                    // 为属性赋值
                    filed.set(t, colValue);
                }

                list.add(t);
            }

            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeSource(conn, ps, rs);
        }

        return null;

    }

    /**
     * 通用的查询多条数据的方法，支持事务
     * @param conn
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    protected  <T> List<T> queryForListTx(Connection conn, Class<T> clazz, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 预编译SQL
            ps = conn.prepareStatement(sql);

            // 赋值
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 获取结果集
            rs = ps.executeQuery();

            // 获取返回的结果集的数据
            ResultSetMetaData metaData = rs.getMetaData();

            // 装数据的集合
            List<T> list = new ArrayList<>();

            // 如果有数据，取一行构造对象
            while (rs.next())
            {
                // 构造装数据的对象
                T t = clazz.newInstance();

                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String colLabel = metaData.getColumnLabel(i + 1);
                    Object colValue = rs.getObject(i + 1);

                    // 暴力反射获取属性值
                    Field filed = clazz.getDeclaredField(colLabel);
                    filed.setAccessible(true);

                    // 为属性赋值
                    filed.set(t, colValue);
                }

                list.add(t);
            }

            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            closeSource(null, ps, rs);
        }

        return null;

    }

    // 关闭资源
    protected void closeSource(Connection conn, PreparedStatement ps){
        if (conn != null)
        {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (ps != null)
        {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // 关闭资源
    protected void closeSource(Connection conn, PreparedStatement ps, ResultSet rs){

        closeSource(conn, ps);

        if (rs != null)
        {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
