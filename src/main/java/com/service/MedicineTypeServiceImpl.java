package com.service;

import com.bean.MedicineType;
import com.dao.MedicineTypeDao;
import com.dao.MedicineTypeDaoImpl;

import java.util.*;

/**
 * @author shkstart
 * @create 2020-06-05 12:47
 */
public class MedicineTypeServiceImpl implements MedicineTypeService{

    private MedicineTypeDao mtd = new MedicineTypeDaoImpl();

    @Override
    public List selectAllMedicineType() {

        List<MedicineType> list = mtd.selectMedicineTypeForList();

        Iterator<MedicineType> it = list.iterator();

        List alist = new ArrayList(list.size());

        while (it.hasNext())
        {
            Map map = new HashMap<>(2);
            MedicineType next = it.next();
            map.put("value", next.getType());
            map.put("label", next.getTypeName());
            alist.add(map);
            map = null;
        }


        return alist;
    }

    @Override
    public Boolean updateMedicineType(MedicineType medicineType) {
        return mtd.updateMedicineType(medicineType) > 0;
    }

    @Override
    public Boolean insertMedicineType(MedicineType medicineType) {
        return mtd.insertMedicineType(medicineType) > 0;
    }
}
