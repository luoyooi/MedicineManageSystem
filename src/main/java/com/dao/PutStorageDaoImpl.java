package com.dao;

import com.bean.PutStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-05-31 21:13
 */
public class PutStorageDaoImpl extends BaseDao implements PutStorageDao{

    @Override
    public Integer insertPutStorage(PutStorage putSto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertId = -1;

        try {
            String sql = "insert into put_storage(med_id, cost, quantity, date, hand_person) values(?, ?, ?, ?, ?);";

            conn = DruidPool.getDataSource().getConnection();

            // 取消自动提交
            conn.setAutoCommit(false);

            this.executeTx(conn, sql, putSto.getMedId(), putSto.getCost(), putSto.getQuantity(), putSto.getDate(), putSto.getHandPerson());

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
    public List<PutStorage> selectPutStorageForList() {
        String sql = "select eno, med_id AS medId, cost, quantity, date, hand_person AS handPerson from put_storage;";

        return this.queryForList(PutStorage.class, sql);
    }

    @Override
    public List selectPutStorageHistory(Integer begin, Integer end) {
        String sql = "select med.eno,med.med_id, med.med_name, med.cost, med.quantity, med.date, emp.login_name, emp.emp_name\n" +
                "from employee emp\n" +
                "join\n" +
                "(\n" +
                "    select a.eno,a.med_id, b.med_name, a.cost, a.quantity, a.date, a.hand_person\n" +
                "    from put_storage a\n" +
                "             join medicine_info b\n" +
                "                  on a.med_id = b.med_id and a.date between ? and ?\n" +
                ") med\n" +
                "on emp.login_name = med.hand_person;";

        return this.querySelect(sql, begin, end);

    }

    @Override
    public Integer deletePutStorageById(Integer eno) {
        String sql = "DELETE FROM put_storage WHERE eno = ?";

        return this.execute(sql, eno);
    }
}
