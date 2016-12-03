package database;

/**
 * Created by jimmyhsu on 2016/12/2.
 */
public class TakeCourse {
    private String studentId;
    private int courseId;
    private String attendence;

    public TakeCourse() {
    }

    public TakeCourse(String studentId, int courseId, String attendence) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendence = attendence;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }
}
