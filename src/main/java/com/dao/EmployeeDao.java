package com.dao;

import com.bean.Employee;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-30 15:24
 */
public interface EmployeeDao {
    // 插入员工信息，返回插入的条数
    public Integer insertEmployee(Employee emp);

    // 批量插入员工信息
    public Boolean insertEmployees(List list);

    // 更新员工信息，返回影响条数
    public Integer updateEmployee(Employee emp);

    // 查询所有员工信息，仅限可用员工
    public List<Employee> selectEmployeeForList(Boolean isUse);

    // 根据登录名查找用户信息
    public Employee selectEmployeeByLoginName(String loginName, Boolean isUse);

    // 查找是否有相同登录名的员工
    public Employee selectEmployeeByLoginName(String loginName);

    // 设置员工可用状态
    public Integer updateEmployeeIsUse(String loginName, Boolean isUse);

}
