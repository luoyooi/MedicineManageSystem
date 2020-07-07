package com.dao;

import com.alibaba.fastjson.JSON;
import com.bean.MedicineType;
import org.junit.Test;

/**
 * @author shkstart
 * @create 2020-05-31 18:57
 */
public class MedicineTypeDaoImplTest {
    MedicineTypeDao m = new MedicineTypeDaoImpl();

    @Test
    public void test()
    {
        System.out.println(m.insertMedicineType(new MedicineType("B", "西药", true)));
    }

    @Test
    public void test1()
    {
        m.updateMedicineType(new MedicineType("A", "西药", true));
    }

    @Test
    public void test3()
    {
        System.out.println(m.selectMedicineTypeForList());
    }

    @Test
    public void test4()
    {
        String s = JSON.toJSONString(new MedicineType("A", "西药", true));

        System.out.println(s);

        MedicineType medicineType = JSON.parseObject(s, MedicineType.class);

        System.out.println(medicineType);
    }
}
