package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jimmyhsu on 2016/12/2.
 */
public class TakeCourseDb {
    private static DatabaseBasic mSqlHelper;

    public static void addCourseForStudent(String studentId, int courseId) {
        String sql = "insert into takecourse (studentId, courseId) values (\'"+ studentId + "\', " + courseId + ")";
        execSql(sql);
    }

    private static void execSql(String sql) {
        mSqlHelper = new DatabaseBasic();
        mSqlHelper.executeSql(sql);
        mSqlHelper.recycle();
    }

    public static void removeStudentFromCourse(String studentId, int courseId) {
        String sql = "delete from takecourse where studentId = \'" + studentId + "\' and courseId = " + courseId;
        execSql(sql);
    }

    public static boolean studentAlreadyInCourse(String studentId, int courseId) {
        String sql = "select * from takecourse where studentId = \'" + studentId + "\' and courseId = " + courseId;
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        boolean result = false;
        try {
            result = resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
        return result;
    }

}
