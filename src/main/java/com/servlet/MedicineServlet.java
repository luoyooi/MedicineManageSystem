package com.servlet;

import com.alibaba.fastjson.JSON;
import com.bean.Medicine;
import com.service.MedicineService;
import com.service.MedicineServiceImpl;
import com.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-01 20:51
 */
public class MedicineServlet extends HttpServlet {
    private MedicineService ms = new MedicineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        // 查询符合条件的所有药品信息
        List medicines = ms.selectMedicineForList(type);

        String s = JSON.toJSONString(medicines);

        response.getWriter().write(s);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求体中读取json信息
        String jsonData = ServletUtil.getJSON(request.getReader());

        // 将字符串转换为对象
        Medicine med = JSON.parseObject(jsonData, Medicine.class);

        // 更新药品
        Boolean result = ms.updateMedicine(med);

        response.getWriter().write(result.toString());

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String medId = request.getParameter("medId");

        Boolean isUpdate = ms.updateMedicineIsUse(Integer.valueOf(medId), false);

        response.getWriter().write(isUpdate.toString());
    }
}
