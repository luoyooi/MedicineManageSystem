package com.dao;

import com.bean.PutStorage;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 21:22
 */
public class PutStorageDaoImplTest {
    PutStorageDaoImpl p = new PutStorageDaoImpl();

    @Test
    public void test()
    {
        System.out.println(p.insertPutStorage(new PutStorage(2, new BigDecimal(30), 60, 163653245, "")));
    }

    @Test
    public void test1()
    {
        System.out.println(p.selectPutStorageForList());
    }

    @Test
    public void test2()
    {
        p.deletePutStorageById(2);
    }

    @Test
    public void test3()
    {
//        List list = p.selectPutStorageHistory(1590940800000L, 1591545600000L);
//        System.out.println(list);
    }
}
