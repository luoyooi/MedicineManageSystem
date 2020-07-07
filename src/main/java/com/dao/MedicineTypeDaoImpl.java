package com.dao;

import com.bean.MedicineType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 18:49
 */
public class MedicineTypeDaoImpl extends BaseDao implements MedicineTypeDao{
    @Override
    public Integer insertMedicineType(MedicineType mt) {

        String sql = "INSERT INTO medicine_type(type, type_name, is_use) VALUES(?, ?, ?)";

        return  this.execute(sql, mt.getType(), mt.getTypeName(), mt.isUse());
    }

    @Override
    public Integer updateMedicineType(MedicineType mt) {
        String sql = "UPDATE medicine_type set type_name = ?, is_use = ? WHERE type = ?;";

        return this.execute(sql, mt.getTypeName(), mt.isUse(), mt.getType());
    }

    @Override
    public List<MedicineType> selectMedicineTypeForList() {
        String sql = "SELECT type, type_name AS typeName FROM medicine_type WHERE is_use = 1;";

        return this.queryForList(MedicineType.class, sql);
    }
}
