package com.amrsatrio.school.model;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {
    private String id;
    private int year;
    private String name;
    //private Teacher teacher;
    private final List<Student> students = new ArrayList<>();
    private final List<Subject> subjects = new ArrayList<>();

    public SchoolClass() {
    }

    public SchoolClass(String name, int year/*, Teacher teacher*/) {
        //this.id = String.format("%s%d", name, year);
        this.name = name;
        this.year = year;
        //this.teacher = teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }*/

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void setStudents(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
    }

    public List<Subject> getSubjects() {
        return new ArrayList<>(subjects);
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects.clear();
        this.subjects.addAll(subjects);
    }
}
