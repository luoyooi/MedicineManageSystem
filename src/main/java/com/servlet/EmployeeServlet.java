package com.servlet;

import com.alibaba.fastjson.JSON;
import com.bean.Employee;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
import com.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 21:41
 */
public class EmployeeServlet extends HttpServlet {

    private static EmployeeService emps = new EmployeeServiceImpl();

    // 查询所有的员工信息
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = emps.selectEmployeeForList();

        // 获取员工信息
        String s = JSON.toJSONString(employees);

        response.getWriter().write(s);
    }

    // 添加员工
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 从请求体中读取json信息
        String jsonData = ServletUtil.getJSON(request.getReader());

        Employee emp = JSON.parseObject(jsonData, Employee.class);

        Boolean result = emps.addEmployee(emp);

        response.getWriter().write(result.toString());

    }

    @Override
    protected void doPut(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        // 从请求体中读取json信息
        String jsonData = ServletUtil.getJSON(request.getReader());

        Employee emp = JSON.parseObject(jsonData, Employee.class);

        // 更新员工信息
        Boolean result = emps.updateEmployee(emp);

        response.getWriter().write(result.toString());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");

        // 更新员工可以状态
        Boolean isUpdate = emps.updateEmployeeIsUse(loginName, false);

        response.getWriter().write(isUpdate.toString());
    }
}
