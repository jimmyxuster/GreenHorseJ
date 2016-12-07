package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinkpad on 2016/12/5.
 */
public class CreditDb {
    private static DatabaseBasic mSqlHelper;

    /*
    * 查找某学生的所有已修学分
    */
//    public static int selectAllUserFinishedCredits(String userId)
//    {
//        int credit = 0;
//        String sql = "select sum(credit) from finishedcredit where studentId = \'"
//                + userId + "\'";
//        mSqlHelper = new DatabaseBasic();
//        ResultSet resultSet = mSqlHelper.executeSql(sql);
//        credit = getCredit(resultSet, mSqlHelper);
//        return credit;
//    }

    private static List<Credit> getCredits(ResultSet resultSet, DatabaseBasic mSqlHelper) {
        List<Credit> credits = new ArrayList<Credit>();
        try {
            while (resultSet.next()) {
                Credit credit = new Credit();
                credit.setCredit(resultSet.getInt("credit"));
                credit.setUserId(resultSet.getString("userId"));
                credits.add(credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
        return credits;
    }

    /*
     * 查看某个班的所有学生修过的学分
     */
    public static List<Credit> selectUserCreditsByClassId(int classId)
    {
        String sql = "select userId,classId,credit from userinclass "
                + "left outer join usercredit "
                + "on userinclass.userId = usercredit.studentId "
                + "where classId = " + classId;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        return getCredits(resultSet, mSqlHelper);
    }
}
