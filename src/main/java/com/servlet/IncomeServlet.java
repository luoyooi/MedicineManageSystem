package com.servlet;

import com.alibaba.fastjson.JSON;
import com.service.IncomeService;
import com.service.IncomeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-06-12 14:28
 */
public class IncomeServlet extends HttpServlet {
    private IncomeService is = new IncomeServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");

        // 获取数据
        Map<String, Object> incomeData = is.generateIncomeData(year);

        // 转换成json
        String json = JSON.toJSONString(incomeData);

        response.getWriter().write(json);
    }
}
