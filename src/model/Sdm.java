package model;

import java.util.Date;

public class Sdm {
    private String id;
    private String nama;
    private String gender;
    private Date tanggalLahir;
    private String email;
    private String noTelp;
    private String alamat;
    private int kelas;

    public Sdm() {
    }

    public Sdm(String id, String nama, String gender, Date tanggalLahir, String email, String noTelp, String alamat, int kelas) {
        this.id = id;
        this.nama = nama;
        this.gender = gender;
        this.tanggalLahir = tanggalLahir;
        this.email = email;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.kelas = kelas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getKelas() {
        return kelas;
    }

    public void setKelas(int kelas) {
        this.kelas = kelas;
    }
}
