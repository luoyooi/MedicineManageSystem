package com.service;

import com.bean.Medicine;

import java.util.List;

/**
 * @author shkstart
 * @create 2020-06-01 20:52
 */
public interface MedicineService {

    // 按条件查询所有的药品信息
    public List<Medicine> selectMedicineForList(String type);

    // 封装所有的id，药名数据
    public List selectMedicineForIdName();

    // 添加药品
    public Boolean addMedicine(Medicine medicine);

    // 更新药品信息
    public Boolean updateMedicine(Medicine medicine);

    // 设置药品的可用状态
    public Boolean updateMedicineIsUse(Integer medId, Boolean isUse);
}
