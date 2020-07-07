package com.service;

import com.bean.Employee;
import com.dao.EmployeeDao;
import com.dao.EmployeeDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-06-01 11:13
 */
public class EmployeeServiceImpl implements EmployeeService{

    private static EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public Map verifyEmployee(String username, String password) {

        // 获取可用状态的员工
        Employee employee = employeeDao.selectEmployeeByLoginName(username, true);

        // 用于保存验证信息
        Map map = new HashMap();

        // 验证通过
        if (employee != null && password.equals(employee.getLoginPassword()))
        {
            // 保存登录成功相关信息
            map.put("is_login", true);
            map.put("login_name", employee.getLoginName());
            map.put("emp_name", employee.getEmpName());
            map.put("is_manager", employee.getManager());
        }
        else
        {
            // 保存登录失败相关信息
            map.put("is_login", false);
        }

        return map;
    }

    @Override
    public Boolean verifyEmployee(String username) {

        return employeeDao.selectEmployeeByLoginName(username) != null;
    }

    // 添加员工，返回插入结果
    @Override
    public Boolean addEmployee(Employee employee) {
        return employeeDao.insertEmployee(employee) > 0;
    }

    // 更新员工信息
    @Override
    public Boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee) > 0;
    }

    // 修改员工的可用状态
    @Override
    public Boolean updateEmployeeIsUse(String loginName, Boolean isUse) {
        return employeeDao.updateEmployeeIsUse(loginName, isUse) > 0;
    }

    // 查询所有可用的员工信息
    @Override
    public List<Employee> selectEmployeeForList() {
        // true代表可用
        List<Employee> employees = employeeDao.selectEmployeeForList(true);

        return employees;
    }
}
