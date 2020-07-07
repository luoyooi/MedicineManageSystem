package com.servlet;

import com.service.UploadService;
import com.service.UploadServiceImpl;
import com.util.ServletUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-11 15:17
 */
public class UploadEmpServlet extends HttpServlet {
    private UploadService us = new UploadServiceImpl();

    private static final String UPLOAD_DIRECTORY = "upload/empinfo";

    // 上传员工信息文件
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
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
                for (FileItem item : formItems)
                {
                    // 处理不在表单中的字段
                    if (!item.isFormField())
                    {
                        List list = ServletUtil.getListByStream(item.getInputStream());

                        // 若有数据
                        if (list.size() > 0)
                        {
                            // 保存进数据库
                            Boolean isSave = us.saveEmpInfo(list);
                            if (isSave)
                            {
                                // 上传成功
                                response.getWriter().write(Boolean.valueOf("true").toString());
                            }
                            else
                            {
                                // 上传失败
                                response.getWriter().write(Boolean.valueOf("false").toString());
                            }
                            response.getWriter().write(Boolean.valueOf("false").toString());
                        }
                        else
                        {
                            // 上传失败
                            response.getWriter().write(Boolean.valueOf("false").toString());
                        }

                        // 只上传一个文件
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
