package com.service;

import com.bean.Medicine;
import com.bean.MedicineType;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-05 13:09
 */
public interface MedicineTypeService {
    // 查询所有药品类别
    public List selectAllMedicineType();

    // 修改药品类型
    public Boolean updateMedicineType(MedicineType medicineType);

    // 添加药品类型
    public Boolean insertMedicineType(MedicineType medicineType);
}
