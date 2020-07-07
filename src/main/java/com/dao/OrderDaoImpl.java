package com.dao;

import com.bean.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 19:16
 */
public class OrderDaoImpl extends BaseDao implements OrderDao{


    @Override
    public Integer insertOrder(Order order) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertId = -1;

        try {
            String sql = "insert into orders(med_id, quantity, cost, price, date, hand_person) values (?, ?, ?, ?, ?, ?);";

            conn = DruidPool.getDataSource().getConnection();

            // 取消自动提交
            conn.setAutoCommit(false);

            this.executeTx(conn, sql,order.getMedId(), order.getQuantity(), order.getCost(), order.getPrice(), order.getDate(), order.getHandPerson());

            ps = conn.prepareStatement("SELECT LAST_INSERT_ID();");
            rs = ps.executeQuery();

            insertId = -1;
            if (rs.next())
            {
                insertId = rs.getInt(1);
            }

            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            closeSource(conn, ps, rs);
        }

        return insertId;
    }

    @Override
    public List<Order> selectOrderForList() {
        String sql = "select order_id AS orderId, med_id AS medId, quantity, cost, price, date, hand_person AS handPerson from orders;";
        return this.queryForList(Order.class, sql);
    }

    @Override
    public List selectOrderHistory(Integer begin, Integer end) {
        String sql = "select med.order_id,med.med_id, med.med_name, med.quantity, med.cost, med.price, med.date, emp.login_name, emp.emp_name\n" +
                "from employee emp\n" +
                "         join\n" +
                "     (\n" +
                "         select a.order_id,a.med_id, b.med_name, a.quantity, a.cost, a.price, a.date, a.hand_person\n" +
                "         from orders a\n" +
                "                  join medicine_info b\n" +
                "                       on a.med_id = b.med_id and a.date between ? and ?\n" +
                "     ) med\n" +
                "     on emp.login_name = med.hand_person;";

        return this.querySelect(sql, begin, end);
    }

    @Override
    public Integer deleteOrderById(Integer order_id) {
        String sql = "DELETE FROM orders WHERE order_id = ?";

        return this.execute(sql, order_id);
    }
}
