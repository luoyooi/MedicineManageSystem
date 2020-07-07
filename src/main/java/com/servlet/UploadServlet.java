package com.servlet;


import com.bean.Medicine;
import com.service.MedicineService;
import com.service.MedicineServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-06 11:04
 */
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "upload";
    private MedicineService ms = new MedicineServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
         DiskFileItemFactory factory = new DiskFileItemFactory();
         ServletFileUpload upload = new ServletFileUpload(factory);
         upload.setHeaderEncoding("UTF-8");

        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            List<FileItem> formItems = upload.parseRequest(request);


            if (formItems != null && formItems.size() > 0)
            {
                FileItem picture = null;

                Class clazz = Medicine.class;

                Medicine med = (Medicine) clazz.newInstance();

                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        // 保存表单中的图片项
                        picture = item;
                    }
                    else
                    {
                        // 根据表单值设置对象属性值
                        String name = item.getFieldName();

                        // 获取对应属性
                        Field field = clazz.getDeclaredField(name);
                        field.setAccessible(true);

                        // 获取对应属性的类型
                        Class<?> type = field.getType();
                        // 获取构造函数
                        Constructor<?> constructor = type.getConstructor(String.class);
                        // 获取传过来的表单值
                        Object value = item.getString("UTF-8");
                        // 构造对应类型的值
                        Object typeValue = constructor.newInstance(value);
                        // 传入对应类型的值，初始化药品对象
                        field.set(med, typeValue);
                    }
                }


                picture.getName();
                // 按药品名字和当前时间拼接图片名称
                String fileName = new File(System.currentTimeMillis() + ".jpg").getName();

                String filePath = uploadPath + File.separator + fileName;

                File storeFile = new File(filePath);

                // 初始化med对象属性
                med.setQuantity(0);
                med.setSale(false);
                med.setUse(true);
                med.setPicUrl(fileName);

                // 保存到数据库
                Boolean result = ms.addMedicine(med);

                // 如果保存成功
                if (result)
                {
                    // 保存文件到硬盘
                    picture.write(storeFile);
                }

                // 发送保存结果
                response.getWriter().write(result.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
