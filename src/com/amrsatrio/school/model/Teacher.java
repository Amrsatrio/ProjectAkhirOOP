package com.amrsatrio.school.model;

import java.util.Date;

public class Teacher extends Person {
    //private final List<Subject> subjects = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String id, String nama, String gender, Date tanggalLahir, String email, String noTelp, String alamat) {
        super(id, nama, gender, tanggalLahir, email, noTelp, alamat);
    }

    @Override
    protected String getIdPrefix() {
        return "TE";
    }

    /*public List<Subject> getSubjects() {
        return new ArrayList<>(subjects);
    }

    public void setSubjects(Collection<Subject> subjects) {
        this.subjects.clear();
        this.subjects.addAll(subjects);
    }*/
}
