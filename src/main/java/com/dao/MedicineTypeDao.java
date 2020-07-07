package com.dao;

import com.bean.MedicineType;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 18:47
 */
public interface MedicineTypeDao {
    // 插入药品类型
    public Integer insertMedicineType(MedicineType mt);

    // 修改药品类型
    public Integer updateMedicineType(MedicineType mt);

    // 查询药品类型
    public List<MedicineType> selectMedicineTypeForList();
}
