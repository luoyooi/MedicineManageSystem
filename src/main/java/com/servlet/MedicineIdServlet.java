package com.servlet;

import com.alibaba.fastjson.JSON;
import com.service.MedicineService;
import com.service.MedicineServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-05 15:39
 */
public class MedicineIdServlet extends HttpServlet {

    private MedicineService ms = new MedicineServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取封装好的id，name药品数据
        List list = ms.selectMedicineForIdName();

        String s = JSON.toJSONString(list);

        response.getWriter().write(s);

    }
}
