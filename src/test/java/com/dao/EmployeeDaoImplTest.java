package com.dao;

import com.alibaba.fastjson.JSON;
import com.bean.Employee;
import com.util.ServletUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author shkstart
 * @create 2020-05-30 15:50
 */
public class EmployeeDaoImplTest {
    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    @Test
    public void test() throws UnsupportedEncodingException {
        int a = employeeDao.insertEmployee(new Employee("乐乐", 18, false, "12365478963", "xx@qq.com", "login", "password", true, true));

        System.out.println(a);
    }

    @Test
    public void test1()
    {
        int a = employeeDao.updateEmployee(new Employee("乐乐", 18, false, "12365478963", "xx@qq.com", "login", "password", true, true));

        System.out.println(a);
    }

    @Test
    public void test2()
    {
        System.out.println(employeeDao.selectEmployeeForList(true));
    }

    @Test
    public void test3()
    {
        Employee emp = employeeDao.selectEmployeeByLoginName("ggg", true);
        System.out.println(emp);
    }

    @Test
    public void test4()
    {
        String s = JSON.toJSONString(new Employee("乐乐", 18, false, "12365478963", "xx@qq.com", "zzt", "password", true, true));
        System.out.println(s);
    }

    @Test
    public void test5()
    {
        String s = "{\n" +
                "        \"age\":20,\n" +
                "        \"email\":\"dd@qq.com\",\n" +
                "        \"emp_id\":1,\n" +
                "        \"emp_name\":\"管理员A\",\n" +
                "        \"gender\":true,\n" +
                "        \"login_name\":\"adminabc\",\n" +
                "        \"login_password\":\"password\",\n" +
                "        \"manager\":true,\n" +
                "        \"phone\":\"12365478963\"\n" +
                "    }";


        Employee employee = JSON.parseObject(s, Employee.class);

        System.out.println(employee);
    }

}
