package database;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2016/11/30.
 */
public class Course {
    private int id;
    private int credit;
    private String name;
    private Timestamp datetime;
    private int duration;
    private String location;
    private int moduleId;
    private int maxNumber;
    private String restriction;
    private List<User> students;

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public Course(int id, int credit, String name, Timestamp datetime, int duration
            , String location, int moduleId, int maxNumber, String restriction) {
        this.id = id;
        this.credit = credit;
        this.name = name;
        this.datetime = datetime;
        this.duration = duration;
        this.location = location;
        this.moduleId = moduleId;
        this.maxNumber = maxNumber;
        this.restriction = restriction;
    }

     public Course(int id, int credit, String name, Timestamp datetime, int duration
            , String location, int moduleId, int maxNumber, String restriction, List<User> students) {
        this.id = id;
        this.credit = credit;
        this.name = name;
        this.datetime = datetime;
        this.duration = duration;
        this.location = location;
        this.moduleId = moduleId;
        this.maxNumber = maxNumber;
        this.restriction = restriction;
        this.students = students;
    }

    public Course() {
    }
}
