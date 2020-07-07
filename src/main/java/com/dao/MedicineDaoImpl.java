package com.dao;

import com.bean.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 16:19
 */
public class MedicineDaoImpl extends BaseDao implements MedicineDao{


    @Override
    public Integer insertMedicine(Medicine med) {


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertId = -1;

        try {
            String sql = "INSERT INTO medicine_info(med_name, efficacy, taking_method, product_date, " +
                    "shelf_life, price, quantity, is_sale, type, pic_url, is_use)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            conn = DruidPool.getDataSource().getConnection();

            // 取消自动提交
            conn.setAutoCommit(false);

            this.executeTx(conn, sql, med.getMedName(), med.getEfficacy(), med.getTakingMethod(), med.getProductDate(),
                    med.getShelfLife(), med.getPrice(), med.getQuantity(), med.getSale(), med.getType(), med.getPicUrl(), med.getUse()
            );

            ps = conn.prepareStatement("SELECT LAST_INSERT_ID();");
            rs = ps.executeQuery();

            insertId = -1;
            if (rs.next())
            {
                insertId = rs.getInt(1);
            }

            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            closeSource(conn, ps, rs);
        }

        return insertId;
    }

    @Override
    public Integer updateMedicine(Medicine med) {
        String sql = "UPDATE medicine_info SET med_name = ?,\n" +
                "                         efficacy = ?,\n" +
                "                         taking_method = ?,\n" +
                "                         product_date = ?,\n" +
                "                         shelf_life = ?,\n" +
                "                         price = ?,\n" +
                "                         quantity = ?,\n" +
                "                         is_sale = ?,\n" +
                "                         type = ?,\n" +
                "                         pic_url = ?,\n" +
                "                         is_use = ?\n" +
                "WHERE med_id = ?;";

        return this.execute(sql, med.getMedName(), med.getEfficacy(), med.getTakingMethod(), med.getProductDate(),
                med.getShelfLife(), med.getPrice(), med.getQuantity(), med.getSale(), med.getType(), med.getPicUrl(), med.getUse(),
                med.getMedId()
        );
    }

    @Override
    public List<Medicine> selectMedicineForList(String type, Boolean isUse) {
        String sql = "select med_id AS medId, med_name AS medName, efficacy, taking_method AS takingMethod, " +
                "product_date AS productDate, shelf_life AS shelfLife, price, quantity, is_sale AS isSale, type, " +
                "pic_url AS picUrl, is_use AS isUse from medicine_info where type = ? and is_use = ?;";

        return this.queryForList(Medicine.class, sql, type, isUse);
    }

    @Override
    public List<Medicine> selectMedicineForList(Boolean isUse) {
        String sql = "select med_id AS medId, med_name AS medName, efficacy, taking_method AS takingMethod, " +
                "product_date AS productDate, shelf_life AS shelfLife, price, quantity, is_sale AS isSale, type, " +
                "pic_url AS picUrl, is_use AS isUse from medicine_info where is_use = ?;";

        return this.queryForList(Medicine.class, sql, isUse);
    }

    @Override
    public Integer updateMedicineIsUse(Integer medId, Boolean isUse) {
        String sql = "UPDATE medicine_info SET is_use = ? WHERE med_id = ?";

        return this.execute(sql, isUse, medId);
    }
}
