package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static database.CourseDb.getUsers;

/**
 * Created by Thinkpad on 2016/12/5.
 */
public class ClassDb {
    private static DatabaseBasic mSqlHelper;

    public static List<Class> selectAllClasses() {
        List<Class> classes = new ArrayList<>();
        String sql = "select * from class order by id";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getClasses(classes, resultSet);
        return classes;
    }

    private static void getClasses(List<Class> result, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Class aClass = new Class(resultSet.getInt("id"),
                        resultSet.getString("name"));
                result.add(aClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
    }

    public static List<User> selectUserInClassByClassId(int classId)
    {
        List<User> users = new ArrayList<>();
        String sql = "select * from userinclass LEFT OUTER JOIN user "
                + "on user.id = userinclass.userId where classId = "
                + classId;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getUsers(users, resultSet);
        return users;
    }

//    private static void getUsers(List<User> result, ResultSet resultSet) {
//        try {
//            while (resultSet.next()) {
//                User user = new User(resultSet.getString("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("sex"),
//                        resultSet.getString("institute"),
//                        resultSet.getString("branch"),
//                        resultSet.getString("grade"),
//                        resultSet.getString("type"),
//                        resultSet.getString("dormitory"),
//                        resultSet.getString("permission"),
//                        resultSet.getString("tel"),
//                        resultSet.getString("email"),
//                        resultSet.getString("createTime"),
//                        resultSet.getString("password"));
//                result.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (mSqlHelper != null) {
//                mSqlHelper.recycle();
//                mSqlHelper = null;
//            }
//        }
//    }
    /*
    * 插入一个class
    */
    public static void insertClass(String name)
    {
        String sql = "insert into class(name)" +
                "values (\'" +
                name + "\')";
        execSql(sql);
    }

    private static void execSql(String sql) {
        mSqlHelper = new DatabaseBasic();
        mSqlHelper.executeSqlUpdate(sql);
        mSqlHelper.recycle();
    }

    public static List<User> selectStudentsNotInClass()
    {
        List<User> users = new ArrayList<>();
        String sql = "select * from user where id not in (select DISTINCT userId from userinclass)";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getUsers(users, resultSet);
        return users;
    }

    public void insertUserInClass(String userId, int classId, int position) {
        String sql = "insert into userinclass(userId,classId,position) " +
                "values(\'" +
                userId + "\'," +
                classId + "," +
                position + ")";
        execSql(sql);
    }


    public static Class selectClassById(int id) {
        Class aClass = null;
        String sql = "select * from class where id = " + id;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        aClass = getClass(resultSet, mSqlHelper);
        return aClass;
    }

    private static Class getClass(ResultSet resultSet, DatabaseBasic mSqlHelper) {
        Class aClass = null;
        try {
            if (resultSet.next()) {
                aClass = new Class(resultSet.getInt("id"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
        return aClass;
    }

    public static Class selectClassByName(String name) {
        Class aClass = null;
        String sql = "select * from class where name = \'" + name + "\'";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        aClass = getClass(resultSet, mSqlHelper);
        return aClass;
    }

    public static void deleteUserInClass(String userId, int classId)
    {
        String sql = "delete from userinclass where userId = \'" + userId
                + "\' and classId = " + classId;
        execSql(sql);
    }

    public static void removeClass(int classId)
    {
        String sql = "delete from class where id = " + classId;
        execSql(sql);
    }

    public static void deleteAllUsersInClass(int classId)
    {
        String sql = "delete from userinclass where classId = " + classId;
        execSql(sql);
    }
}
