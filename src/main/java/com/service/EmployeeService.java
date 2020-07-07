package com.service;

import com.bean.Employee;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-05-31 21:43
 */
public interface EmployeeService {
    // 验证用户名密码，返回员工信息
    public Map verifyEmployee(String username, String password);

    // 查询是否存在该账号的员工
    public Boolean verifyEmployee(String username);

    // 添加用户
    public Boolean addEmployee(Employee employee);

    // 更新用户信息
    public Boolean updateEmployee(Employee employee);

    // 设置员工的可用状态
    public Boolean updateEmployeeIsUse(String loginName, Boolean isUse);

    // 查询所有员工信息
    public List<Employee> selectEmployeeForList();
}
