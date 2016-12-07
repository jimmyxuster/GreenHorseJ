package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by jimmyhsu on 2016/12/8.
 */
public class Attendance {
    private String courseName;
    private String courseTime;
    private String studentId;
    private String studentName;
    private String attendance;

    public Attendance(String courseName, Timestamp courseTime, String studentId, String studentName, String attendance) {
        this.courseName = courseName;
        this.courseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(courseTime);
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendance = attendance;
    }

    public Attendance() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Timestamp courseTime) {
        this.courseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(courseTime);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
