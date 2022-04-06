package model;

public class MataPelajaran {
    private String nama;

    public MataPelajaran(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return nama;
    }
}
