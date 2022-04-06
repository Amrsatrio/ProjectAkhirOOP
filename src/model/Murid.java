package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Murid extends Sdm {
    private List<Ekskul> ekskulDiikuti = new ArrayList<>();

    public Murid() {
    }

    public Murid(String id, String nama, String gender, Date tanggalLahir, String email, String noTelp, String alamat, int kelas) {
        super(id, nama, gender, tanggalLahir, email, noTelp, alamat, kelas);
    }

    public List<Ekskul> getEkskulDiikuti() {
        return new ArrayList<>(ekskulDiikuti);
    }

    public void setEkskulDiikuti(Collection<Ekskul> listEkskul) {
        ekskulDiikuti.clear();
        ekskulDiikuti.addAll(listEkskul);
    }
}
