package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2016/11/30.
 */
public class CourseDb {
    private static DatabaseBasic mSqlHelper;

    public static Course selectCourseById(String id) {
        Course course = null;
        String sql = "select * from course where id = \'" + id +"\'";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        course = getCourse(resultSet, mSqlHelper);
        return course;
    }

    private static Course getCourse(ResultSet resultSet, DatabaseBasic mSqlHelper) {
        Course course = null;
        try {
            if (resultSet.next()) {
                course = new Course(resultSet.getInt("id"),
                        resultSet.getInt("credit"),
                        resultSet.getString("name"),
                        resultSet.getDate("datetime"),
                        resultSet.getInt("duration"),
                        resultSet.getString("location"),
                        resultSet.getInt("moduleId"),
                        resultSet.getInt("maxNumber"),
                        resultSet.getString("restriction"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
        return course;
    }

    public static List<Course> selectCourses(String start, String length) {
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course limit " + start + ", " + length;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    private static void getCourses(List<Course> result, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Course course = new Course(resultSet.getInt("id"),
                        resultSet.getInt("credit"),
                        resultSet.getString("name"),
                        resultSet.getDate("datetime"),
                        resultSet.getInt("duration"),
                        resultSet.getString("location"),
                        resultSet.getInt("moduleId"),
                        resultSet.getInt("maxNumber"),
                        resultSet.getString("restriction"));
                result.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mSqlHelper.recycle();
        }
    }

    public static List<Course> selectAllUserTakenCourses(String id){
        List<Course> courses = new ArrayList<>();
        String sql = "select takecourse.* from takecourse left outer join course on course.id = takecourse.courseId where studentId = \'"
                + id + "\' order by course.datetime";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    public static List<Course> selectAllUserPresentedCourses(String id){
        List<Course> courses = new ArrayList<>();
        String sql = "select takecourse.* from takecourse left outer join course on course.id = takecourse.courseId where studentId = \'"
                + id + "\' and takecourse.attendance = '出席' order by course.datetime";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    /*
    * 选出用户没有选过的所有还没到时间的课
    */
    public static List<Course> selectFutureCoursesNotSelectedByUser(String userId){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course where datetime >= now() and id not in"
                + "(select courseId from takecourse where studentId = \'"
                + userId + "\')";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    /*
    * 选出某模块下，用户没有选过的所有还没有到时间的课
    */
    public static List<Course> selectFutureCoursesNotSelectedByUserInModule(String userId, int moduleId){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course where datetime >= now() and moduleId = "
                + moduleId + " and id not in" + "(select courseId from takecourse where studentId = \'"
                + userId + "\') order by datetime";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    //参数类型...? 数组用法和php不同...
    //0 stands for studentId, 1 for courseId, 2 for attendance
    public static void addTakecourse(String studentId, int courseId, String attendence){
        String sql = "insert into takecourse(studentId, courseId, attendance)"
                + "values(\'" + studentId + "\', "
                + courseId + ", \'"
                + attendence + "\')";
        execSql(sql);
    }

    private static void execSql(String sql) {
        mSqlHelper = new DatabaseBasic();
        mSqlHelper.executeSql(sql);
        mSqlHelper.recycle();
    }

    /*
     * 根据studentId和courseId选出takecourse
     */
    public static Takecourse selectTakecourse(String studentId, int courseId){
        Takecourse takecourse = null;
        String sql = "select * from takecourse where studentId = \'" + studentId
                + "\' and courseId = " + courseId;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        takecourse = getTakecourse(resultSet, mSqlHelper);
        return takecourse;
    }

    /*
     * 选出选了某门课的所有学生
     */
    public static List<User> selectStudentsInCourse(int courseId){
        List<User> students = new ArrayList<>();
        String sql = "select * from user where id in " + "(select studentId from takecourse where courseId = "
                + courseId + ")";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getUsers(students, resultSet);
        return students;
    }

    public static void deleteTakecourse(String studentId, int courseId){
        String sql = "delete from takecourse where studentId = \'" + studentId
                + "\' and courseId = " + courseId;
        execSql(sql);
    }

    public static void updateTakecourse(String studentId, int courseId, String attendence){
        String sql = "update takecourse set attendance = \'" + attendence
                + "\' where studentId = \'" + studentId
                + "\' and courseId = " + courseId;
        execSql(sql);
    }

    public static List<Course> selectAllFutureCourses(int start, int length){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course where datetime > now() order by datetime desc limit "
                + start + "," + length;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    public static void insertCourse(String name, int credit, Date datetime, String location, int duration,
                                    int maxNumber, int moduleId)
    {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
        String sql = "insert into course (name, credit, datetime, location, duration, maxNumber, moduleId) " +
                "values (\'" +
                name + "\'," +
                credit + ",\'" +
                dateStr + "\'," +
                location + "," +
                duration + "," +
                maxNumber + "," +
                moduleId + ")";
        execSql(sql);
    }

    /*
    * 分页选出所有已经结束了的课程,按时间倒序
    * ****这里的已结束是指，过了开课时间的课程
    */
    public static List<Course> selectAllFinishedCourses(int start, int length){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course where datetime < now() " + "order by datetime desc limit "
                + start + "," + length;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    /*
     * 选出所有正在进行中的课程
     * 按时间倒序
     */
    public static List<Course> selectOngoingCourses(int start, int length){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course where datetime<now() and datetime+SEC_TO_TIME(duration*60)>=now() "
                + "order by datetime desc limit " + start + "," + length;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    /*
     * 选出所有已经结束的课程
     * *****这里的已经结束是指时间>datetime+duration的课程
     * *****因为duration不是必填项，所以duration可能是null或0，
     * *****所以对这类课程，只要超过开始时间，就算已经结束
     * 按时间倒序
     */
    public static List<Course> selectCoursesAfterDuration(int start, int length){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course where datetime+SEC_TO_TIME(duration*60)<now() union "
                + "select * from course where (ISNULL(duration)or duration = 0) and datetime < now()"
                + "order by datetime desc limit " + start + "," + length;
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

//    public function selectCoursesAfterDurationCount($start=0,$length=999){
//        $text = "select count(*) from course where datetime+SEC_TO_TIME(duration*60)<now() limit %s, %s";
//        $sql = sprintf($text,$start,$length);
//        $db = new database();
//        $link = $db->start();
//        $result = $db->execute_sql("select", $sql);
//        if(mysql_num_rows($result)) {
//            $rs = mysql_fetch_array($result);
//            $count = $rs[0];
//        }else{
//            $count = 0;
//        }
//        mysql_close($link);
//        return $count;
//    }

//    /*
//    * 选出某门课所有的takecourse，并且还有用户的一些数据
//    */
//    public function selectTakecourseAndUserInfoByCourseId($courseId){
//        $text = "select takecourse.*,user.name from takecourse left outer join user " .
//        "on user.id = takecourse.studentId where takecourse.courseId = %s";
//        $sql = sprintf($text,$courseId);
//        $db = new database();
//        $link = $db->start();
//        $result = $db->execute_sql("select", $sql);
//        mysql_close($link);
//        return $result;
//    }

    public static boolean courseNotExists(String name){
        String sql = "select * from course where name = \'" + name + "\'";
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

    public static List<Course> selectAllCourses(){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course";
        mSqlHelper = new DatabaseBasic();
        ResultSet resultSet = mSqlHelper.executeSql(sql);
        getCourses(courses, resultSet);
        return courses;
    }

    public static void addQuitCourse(String studentId, int courseId, String reason) {
        String sql = "insert into quitcourse(studentId, courseId, reason)" +
                "values (\'" +
                studentId + "\'," +
                courseId + ",\'" +
                reason + "\')";
        execSql(sql);
    }

//    public function selectCoursesCount(){
//        $text = "select count(*) from course";
//        $sql = sprintf($text);
//        $db = new database();
//        $link = $db->start();
//        $result = $db->execute_sql("select", $sql);
//        if(mysql_num_rows($result)) {
//            $rs = mysql_fetch_array($result);
//            $count = $rs[0];
//        }else{
//            $count = 0;
//        }
//        mysql_close($link);
//        return $count;
//    }


}

