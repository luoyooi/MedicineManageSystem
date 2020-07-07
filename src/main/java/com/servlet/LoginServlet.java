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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 登录
 * @author shkstart
 * @create 2020-06-01 19:32
 */
public class LoginServlet extends HttpServlet {
    private static EmployeeService emps = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("login_name");

        Boolean isExit = emps.verifyEmployee(loginName);

        response.getWriter().write(isExit.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 如果客户端没有登录，创建session
        HttpSession session = request.getSession(true);

        // 从请求体中读取json信息
        String jsonData = ServletUtil.getJSON(request.getReader());

        // 构造成员工对象
        Employee emp = JSON.parseObject(jsonData, Employee.class);

        Map map = emps.verifyEmployee(emp.getLoginName(), emp.getLoginPassword());

        if ((Boolean) map.get("is_login"))
        {
            // 给客户端添加session，记录客户端的登录状态
            session.setAttribute("username", emp.getLoginName());
        }

        // 发送验证数据给客户端
        response.getWriter().write(JSON.toJSONString(map));
    }
}
