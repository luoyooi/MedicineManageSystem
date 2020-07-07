package com.servlet;

import com.alibaba.fastjson.JSON;
import com.bean.MedicineType;
import com.service.MedicineTypeService;
import com.service.MedicineTypeServiceImpl;
import com.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-05 13:36
 */
public class MedicineTypeServlet extends HttpServlet {
    private MedicineTypeService mts = new MedicineTypeServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询数据
        List list = mts.selectAllMedicineType();

        String listStr = JSON.toJSONString(list);

        response.getWriter().write(listStr);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求体中读取json信息
        String jsonData = ServletUtil.getJSON(request.getReader());

        // 将字符串转换为对象
        MedicineType med = JSON.parseObject(jsonData, MedicineType.class);
        med.setUse(true);

        // 更新
        Boolean result = mts.updateMedicineType(med);

        response.getWriter().write(result.toString());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求体中读取json信息
        String jsonData = ServletUtil.getJSON(request.getReader());

        // 将字符串转换为对象
        MedicineType med = JSON.parseObject(jsonData, MedicineType.class);
        med.setUse(true);

        // 更新
        Boolean result = mts.insertMedicineType(med);

        response.getWriter().write(result.toString());
    }
}
