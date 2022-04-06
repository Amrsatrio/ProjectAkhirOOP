package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Guru extends Sdm {
    private List<MataPelajaran> subjects = new ArrayList<>();

    public Guru() {
    }

    public Guru(String id, String nama, String gender, Date tanggalLahir, String email, String noTelp, String alamat, int kelas) {
        super(id, nama, gender, tanggalLahir, email, noTelp, alamat, kelas);
    }

    public List<MataPelajaran> getSubjects() {
        return new ArrayList<>(subjects);
    }

    public void setSubjects(Collection<MataPelajaran> subjects) {
        this.subjects.clear();
        this.subjects.addAll(subjects);
    }
}
