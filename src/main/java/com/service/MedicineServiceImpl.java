package com.service;

import com.bean.Medicine;
import com.dao.MedicineDao;
import com.dao.MedicineDaoImpl;

import java.util.*;

/**
 * @author shkstart
 * @create 2020-06-01 20:53
 */
public class MedicineServiceImpl implements MedicineService{
    private MedicineDao medicineDao = new MedicineDaoImpl();

    // 查询所有药品信息
    @Override
    public List<Medicine> selectMedicineForList(String type) {

        // 若条件不为空，按条件查
        if (!"".equals(type))
        {

            return medicineDao.selectMedicineForList(type, true);
        }

        // 查询全部
        return medicineDao.selectMedicineForList(true);
    }

    @Override
    public List selectMedicineForIdName() {
        List<Medicine> list = medicineDao.selectMedicineForList(true);
        Iterator<Medicine> it = list.iterator();

        List alist = new ArrayList(list.size());

        while (it.hasNext())
        {
            Map map = new HashMap<>(2);
            Medicine next = it.next();
            map.put("value", next.getMedId());
            map.put("label", next.getMedName());
            alist.add(map);
            map = null;
        }

        return alist;
    }

    // 添加药品信息
    @Override
    public Boolean addMedicine(Medicine medicine) {
        return medicineDao.insertMedicine(medicine) > 0;
    }

    @Override
    public Boolean updateMedicine(Medicine medicine) {
        return medicineDao.updateMedicine(medicine) > 0;
    }

    @Override
    public Boolean updateMedicineIsUse(Integer medId, Boolean isUse) {

        return medicineDao.updateMedicineIsUse(medId, isUse) > 0;
    }
}
