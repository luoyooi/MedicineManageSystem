package com.servlet;

import com.alibaba.fastjson.JSON;
import com.bean.PutStorage;
import com.service.AnalyzeService;
import com.service.AnalyzeServiceImpl;
import com.service.PutStorageService;
import com.service.PutStorageServiceImpl;
import com.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-05 16:27
 */
public class PutStorageServlet extends HttpServlet {
    private PutStorageService pss = new PutStorageServiceImpl();
    private AnalyzeService as = new AnalyzeServiceImpl();

    // 库存模块数据分析
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        List list = as.getStorageHistory(Integer.valueOf(begin), Integer.valueOf(end));

        response.getWriter().write(JSON.toJSONString(list));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求体中读取json信息
        String jsonData = ServletUtil.getJSON(request.getReader());

        // 将字符串转换为对象
        PutStorage storage = JSON.parseObject(jsonData, PutStorage.class);

        // 插入库存
        Boolean isAdd = pss.addStorage(storage);

        response.getWriter().write(isAdd.toString());
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eno = request.getParameter("eno");

        // 获取库存编号，删除库存
        Boolean result = pss.delStorage(Integer.valueOf(eno));

        response.getWriter().write(result.toString());
    }
}
