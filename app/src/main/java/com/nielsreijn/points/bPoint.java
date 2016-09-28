package com.nielsreijn.points;

import java.io.Serializable;

/**
 * Created by reijn on 28-9-2016.
 */

public class bPoint implements Serializable{
    private long id;
    private String teacher, subject, grade;

    public bPoint() {
    }

    public bPoint(String teacher, String subject, String grade) {
        this.teacher = teacher;
        this.subject = subject;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return teacher + ", " + subject;
    }
}
