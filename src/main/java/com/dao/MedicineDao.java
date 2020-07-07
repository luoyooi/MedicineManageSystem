package com.dao;

import com.bean.Medicine;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 16:15
 */
public interface MedicineDao {
    // 插入药品信息，返回药品id
    public Integer insertMedicine(Medicine med);

    // 更新药品信息
    public Integer updateMedicine(Medicine med);

    // 查询药品信息
    public List<Medicine> selectMedicineForList(String type, Boolean isUse);

    // 查询药品信息
    public List<Medicine> selectMedicineForList(Boolean isUse);

    // 设置药品可用状态
    public Integer updateMedicineIsUse(Integer medId, Boolean isUse);

}
