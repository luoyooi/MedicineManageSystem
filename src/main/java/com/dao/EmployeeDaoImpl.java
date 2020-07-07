package com.dao;

import com.bean.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-05-31 15:21
 */
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao{

    @Override
    public Integer insertEmployee(Employee emp) {

        String sql = "INSERT INTO " +
                "employee(emp_name, age, gender, phone, email, " +
                "login_name, login_password, is_use, is_manager) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return this.execute(sql,
                emp.getEmpName(), emp.getAge(), emp.getGender(), emp.getPhone(), emp.getEmail(),
                emp.getLoginName(), emp.getLoginPassword(),emp.getUse(), emp.getManager());
    }

    @Override
    public Boolean insertEmployees(List list) {
        String sql = "INSERT INTO employee(emp_name, age, gender, phone, email, login_name, login_password, is_use, is_manager) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?);";

        Connection conn = null;

        try {
            // 获取连接
            conn = DruidPool.getDataSource().getConnection();
            // 关闭自动提交
            conn.setAutoCommit(false);

            // 预编译SQL
            PreparedStatement ps = conn.prepareStatement(sql);

            // 攒SQL语句
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);

                ps.setObject(1, map.get("emp_name"));
                ps.setObject(2, map.get("age"));
                ps.setObject(3, map.get("gender"));
                ps.setObject(4, map.get("phone"));
                ps.setObject(5, map.get("email"));
                ps.setObject(6, map.get("login_name"));
                ps.setObject(7, map.get("login_password"));
                ps.setObject(8, true);
                ps.setObject(9, map.get("is_manager"));

                ps.addBatch();
            }

            // 一次性提交所有SQL
            ps.executeBatch();
            // 提交
            conn.commit();

            // 返回成功的结果
            return true;

        } catch (Exception e) {

            try {
                // 若发生错误，回滚
                conn.rollback();

            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            try {
                // 关闭连接
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public Integer updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET emp_name = ?, " +
                "                    age = ?," +
                "                    gender = ?, " +
                "                    phone = ?, " +
                "                    email = ?, " +
                "                    login_password = ?," +
                "                    is_manager = ? " +
                " WHERE login_name = ?;";
        return this.execute(sql, emp.getEmpName(), emp.getAge(), emp.getGender(),
                emp.getPhone(), emp.getEmail(), emp.getLoginPassword(), emp.getManager(), emp.getLoginName());
    }

    /**
     *
     * @param isUse 是否可用
     * @return
     */
    @Override
    public List<Employee> selectEmployeeForList(Boolean isUse) {
        String sql = "SELECT emp_name AS empName, " +
                "age, gender, phone, email, login_name AS loginName, " +
                "login_password AS loginPassword, is_manager AS isManager " +
                "FROM employee WHERE is_use = ?";

        return this.queryForList(Employee.class, sql, isUse);
    }

    @Override
    public Employee selectEmployeeByLoginName(String loginName, Boolean isUse) {
        // 筛选出可用状态的管理员验证信息
        String sql = "SELECT emp_name AS empName, login_name AS loginName, login_password AS loginPassword, is_manager AS isManager " +
                "FROM employee WHERE login_name = ? AND is_use = ?";

        return this.query(Employee.class, sql, loginName, isUse);
    }

    @Override
    public Employee selectEmployeeByLoginName(String loginName) {
        // 筛选出可用状态的管理员验证信息
        String sql = "SELECT emp_name AS empName, login_name AS loginName, login_password AS loginPassword, is_manager AS isManager " +
                "FROM employee WHERE login_name = ?";

        return this.query(Employee.class, sql, loginName);
    }

    @Override
    public Integer updateEmployeeIsUse(String loginName, Boolean isUse) {
        String sql = "UPDATE employee SET is_use = ? WHERE login_name = ?";

        return this.execute(sql, isUse, loginName);
    }
}
