package com.service;

import com.dao.EmployeeDao;
import com.dao.EmployeeDaoImpl;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-11 16:09
 */
public class UploadServiceImpl implements UploadService{
    private EmployeeDao ed = new EmployeeDaoImpl();

    @Override
    public Boolean saveEmpInfo(List list) {

        return ed.insertEmployees(list);
    }
}
