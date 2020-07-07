package com.servlet;

import com.alibaba.fastjson.JSON;
import com.service.AnalyzeService;
import com.service.AnalyzeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-09 16:45
 */
public class OrderServlet extends HttpServlet {
    private AnalyzeService as = new AnalyzeServiceImpl();

    // 订单模块数据分析
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        List list = as.getOrderHistory(Integer.valueOf(begin), Integer.valueOf(end));

        response.getWriter().write(JSON.toJSONString(list));
    }
}
