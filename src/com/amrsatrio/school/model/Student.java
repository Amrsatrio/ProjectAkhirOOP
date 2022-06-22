package com.amrsatrio.school.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Student extends Person {
    private final List<Excul> participatingExculs = new ArrayList<>();

    public Student() {
    }

    public Student(String id, String nama, String gender, Date tanggalLahir, String email, String noTelp, String alamat) {
        super(id, nama, gender, tanggalLahir, email, noTelp, alamat);
    }

    @Override
    protected String getIdPrefix() {
        return "ST";
    }

    public List<Excul> getParticipatingExculs() {
        return new ArrayList<>(participatingExculs);
    }

    public void setParticipatingExculs(Collection<Excul> exculs) {
        participatingExculs.clear();
        participatingExculs.addAll(exculs);
    }

    public Excul getExcul() {
        return participatingExculs.isEmpty() ? null : participatingExculs.get(0);
    }

    public void setExcul(Excul excul) {
        participatingExculs.clear();
        if (excul != null) {
            participatingExculs.add(excul);
        }
    }
}
