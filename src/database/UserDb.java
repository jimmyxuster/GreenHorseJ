package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jimmyhsu on 2016/11/29.
 */
public class UserDb{
    private static DatabaseBasic mSqlHelper;
    public static User selectUserById(String id){
        User user = null;
        String sql = "select * from user where id = \'" + id + "\'";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        user = getUserNoJoin(resultSet, mSqlHelper);
        return user;
    }

    public static List<User> selectAdmin()
    {
        List<User> admins = new ArrayList<>();
        String sql = "select * from user where permission = 'admin' order by id";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getUsers(admins, resultSet);
        return admins;
    }

    public static User selectIndividual(String id, String name)
    {
        User user = null;
        String sql = "select * from user where id = \'" + id + "\' and name = \'" + name + "\'";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        user = getUser(resultSet, mSqlHelper);
        mSqlHelper.recycle();
        return user;
    }

    private static User getUserNoJoin(ResultSet resultSet, DatabaseBasic mSqlHelper) {
        User user = null;
        try {
            if (resultSet.next()) {
                user = new User(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("sex"),
                        resultSet.getString("institute"),
                        resultSet.getString("branch"),
                        resultSet.getString("grade"),
                        resultSet.getString("type"),
                        resultSet.getString("dormitory"),
                        resultSet.getString("permission"),
                        resultSet.getString("tel"),
                        resultSet.getString("email"),
                        resultSet.getString("createTime"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
        return user;
    }


    private static User getUser(ResultSet resultSet, DatabaseBasic mSqlHelper) {
        User user = null;
        try {
            if (resultSet.next()) {
                user = new User(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("sex"),
                        resultSet.getString("institute"),
                        resultSet.getString("branch"),
                        resultSet.getString("grade"),
                        resultSet.getString("type"),
                        resultSet.getString("dormitory"),
                        resultSet.getString("permission"),
                        resultSet.getString("tel"),
                        resultSet.getString("email"),
                        resultSet.getString("createTime"),
                        resultSet.getString("password"),
                        resultSet.getString("className"),
                        resultSet.getInt("credit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
        return user;
    }

    public static void updateAdmin(String permission, String id, String name)
    {
        String sql = "update user set permission = \'" + permission + "\' where id = \'" + id + "\' and name = \'" + name + "\'";
        execSql(sql);
    }

    public static void insertStudent(String id, String name, String sex, String institute, String dormitory, String tel
            , String branch, String grade, String email, String type)
    {
        String sql = "insert into user (id, name, sex, institute, dormitory, tel, branch, grade, email, type) " +
                "values (\'" +
                id + "\',\'" +
                name + "\',\'" +
                sex + "\',\'" +
                institute + "\',\'" +
                dormitory + "\',\'" +
                tel + "\',\'" +
                branch + "\',\'" +
                grade + "\',\'" +
                email + "\',\'" +
                type + "\') on duplicate key update " +
                "name = \'" + name + "\', sex = \'" + sex + "\', institute = \'" + institute + "\', dormitory = \'" + dormitory +
                "\', tel = \'" + tel + "\', branch = \'" + branch + "\', grade = \'" + grade + "\', email = \'" + email + "\', type = \'" +
                type + "\'";
        execSql(sql);
    }

    public static void insertStudentClass(String id, String stuClass)
    {
        String sql = "insert into userinclass (userId, classId)" +
            "values (\'" + id + "\', \'" + stuClass + "\')";
        execSql(sql);
    }

    private static void execSql(String sql) {
        mSqlHelper = new DatabaseBasic();
        mSqlHelper.executeSql(sql);
        mSqlHelper.recycle();
    }

    private static void execSqlUpdate(String sql) {
        mSqlHelper = new DatabaseBasic();
        mSqlHelper.executeSqlUpdate(sql);
        mSqlHelper.recycle();
    }

    /*
     * update用户的权限和密码
     */
    public static void updateUserPermission(String permission, String id, String password)
    {
        String sql = "update user set permission = " + permission + ", password = \'" + password + "\' where id = \'" + id + "\'";
        execSql(sql);
    }

    /*
     * 自定义用户的条件的搜索
     */
    public static List<User> selectUserByConditions(Map<String, String> conditions, String orderby
            , int start, int length) {
        List<User> result = new ArrayList<>();
        String sql = "select * from userWithClass ";
        if(conditions != null && conditions.size() > 0) {
            boolean isFirst = true;
            Set<String> keys = conditions.keySet();
            for (String key : keys) {
                if (conditions.get(key) == null || "".equals(conditions.get(key)) || conditions.get(key).equals("null")) {
                    continue;
                }
                if (isFirst) {
                    sql += " where ";
                    isFirst = false;
                }
                String value = conditions.get(key);
                sql += (key + " = \'" + value + "\' and ");
            }
            if (!isFirst) {
                sql = sql.substring(0, sql.lastIndexOf("and"));
            }
        }
        if(orderby != null && !orderby.equals("")){
            sql += " order by " + orderby;
        }
        sql += " limit " + start + ',' + length;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getUsers(result, resultSet);
        return result;
    }

    private static void getUsers(List<User> result, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("sex"),
                        resultSet.getString("institute"),
                        resultSet.getString("branch"),
                        resultSet.getString("grade"),
                        resultSet.getString("type"),
                        resultSet.getString("dormitory"),
                        resultSet.getString("permission"),
                        resultSet.getString("tel"),
                        resultSet.getString("email"),
                        resultSet.getString("createTime"),
                        resultSet.getString("password"),
                        resultSet.getString("className"),
                        resultSet.getInt("credit"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (mSqlHelper != null) {
                mSqlHelper.recycle();
                mSqlHelper = null;
            }
        }
    }

    public static boolean userNotExists(String id)
    {
        String sql = "select * from user where id = \'" + id + "\'";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
        return false;
    }

    public static void removeStudent(String studentId)
    {
        String sql = "delete from user where id = \'" + studentId + "\'";
        execSqlUpdate(sql);
    }
}
