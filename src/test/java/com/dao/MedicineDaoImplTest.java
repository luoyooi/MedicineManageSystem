package com.dao;

import com.alibaba.fastjson.JSON;
import com.bean.Medicine;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 16:35
 */
public class MedicineDaoImplTest {
    MedicineDao medicineDao = new MedicineDaoImpl();
    @Test
    public void test()
    {
        int a = medicineDao.insertMedicine(new Medicine("qiqi", "感冒", "一天三次", 188606014, 188606999, new BigDecimal(50), 0, true, "A", "pic/url", false));
        System.out.println(a);
    }

    @Test
    public void test1()
    {
        int a = medicineDao.updateMedicine(new Medicine(2,"阿萨德", "感冒", "一天三次", 188606014, 188606999, new BigDecimal(50), 0, true, "A", "pic/url", false));
        System.out.println(a);
    }

    @Test
    public void test3()
    {
        List<Medicine> medicines = medicineDao.selectMedicineForList("A", true);
        System.out.println(medicines);
    }

    @Test
    public void test4()
    {
        String s = JSON.toJSONString(new Medicine(3,"uu", "感冒", "一天三次", 188606014, 188606999, new BigDecimal(50), 0, true, "A", "pic/url", true));
        System.out.println(s);
    }

    @Test
    public void test5()
    {

    }
}
