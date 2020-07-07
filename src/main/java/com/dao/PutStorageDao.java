package com.dao;

import com.bean.Order;
import com.bean.PutStorage;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 21:11
 */
public interface PutStorageDao {
    // 插入药品入库信息，返回历史记录id
    public Integer insertPutStorage(PutStorage putSto);

    // 查询药品入库信息
    public List<PutStorage> selectPutStorageForList();

    // 查询药品入库信息，附带日期参数
    public List selectPutStorageHistory(Integer begin, Integer end);

    // 删除药品入库信息
    public Integer deletePutStorageById(Integer eno);
}
