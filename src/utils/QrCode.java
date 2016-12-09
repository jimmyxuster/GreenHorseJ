package utils;

import database.DatabaseBasic;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jimmyhsu on 2016/12/9.
 */
public class QrCode {

    public QrCode() {
        String sql = "select * from qrcode";
        DatabaseBasic dbHelper = new DatabaseBasic();
        ResultSet resultSet = dbHelper.executeSql(sql);
        try {
            while (resultSet.next()) {
                long expireTime = resultSet.getLong("createTime") + resultSet.getInt("expiresIn");
                if (expireTime < System.currentTimeMillis()) {
                    deleteByQrCode(resultSet.getString("qrcode"), dbHelper);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                dbHelper.recycle();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteByQrCode(String qrcode, DatabaseBasic dbHelper) {
        String sql = "delete from qrcode where qrcode = \'" + qrcode + "\'";
        dbHelper.executeSqlUpdate(sql);
    }

    public void insertQRCode(int courseId, String qrcode, int expiresIn) {
        String sql = "insert into qrcode(courseId, qrcode,createTime, expiresIn) values (" +
                courseId + ",\'" + qrcode + "\', " + System.currentTimeMillis() + ", " + expiresIn + ")";
        DatabaseBasic sqlHelper = new DatabaseBasic();
        sqlHelper.executeSqlUpdate(sql);
        sqlHelper.recycle();
    }
}
