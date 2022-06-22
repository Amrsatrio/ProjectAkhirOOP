package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;
import com.amrsatrio.school.model.Excul;
import com.amrsatrio.school.model.Student;

import java.util.ArrayList;
import java.util.List;

import static com.amrsatrio.school.util.Utils.joinNiceString;
import static com.amrsatrio.school.util.Utils.promptInt;
import static java.lang.System.out;

public class ExculInterface extends Interface {
    public ExculInterface(Main main) {
        super(main);
    }

    @Override
    public boolean menu() {
        out.println("== Kelola Ekskul ==");
        out.println();
        out.println(getDatabase().getNumExculs() + " ekskul terdaftar");
        out.println("1. Lihat semua");
        out.println("2. Tambah");
        out.println("3. Hapus");
        out.println();
        out.println("0. Kembali");

        int pilih = promptInt(scanner, ">> ", 0, 4);
        if (pilih == 0) return false;
        else if (pilih == 1) showExculs();
        else if (pilih == 2) insertExcul();
        else if (pilih == 3) deleteExcul();

        return true;
    }

    public void insertExcul() {
        String nama;
        while (true) {
            out.print("Nama Ekskul: ");
            nama = scanner.nextLine();
            if (nama.isEmpty()) {
                out.println("Nama tidak boleh kosong");
                continue;
            }
            break;
        }

        getDatabase().insertExcul(new Excul(nama));
        out.println("Ekskul berhasil ditambahkan");
    }

    public void showExculs() {
        boolean first = true;
        for (Excul excul : getDatabase().fetchExculs()) {
            if (first) {
                first = false;
            } else {
                out.println();
            }
            out.println(excul.getName());
            List<Student> participants = findExculParticipants(excul);
            if (participants.isEmpty()) {
                out.println("<Tidak ada anggota ekskul>");
            } else {
                out.println("Anggota: " + joinNiceString(participants));
            }
        }
        out.println();
    }

    public void deleteExcul() {
        Excul excul = findEkskul();
        if (excul != null) {
            // Propagate to all students
            for (Student m : getDatabase().fetchStudents()) {
                List<Excul> exculs = m.getParticipatingExculs();
                exculs.removeIf(e -> e.getName().equals(excul.getName()));
                m.setParticipatingExculs(exculs);
            }
            getDatabase().deleteExcul(excul);
            out.println("Ekskul " + excul.getName() + " berhasil dihapus");
        }
    }

    private Excul findEkskul() {
        String name;
        while (true) {
            out.print("Masukkan Nama Ekskul (atau '0' untuk batal): ");
            name = scanner.nextLine();
            if (name.equals("0")) {
                return null;
            }
            if (name.isEmpty()) {
                continue;
            }
            Excul excul = getDatabase().findExculByName(name);
            if (excul != null) {
                return excul;
            }
            out.println("Ekskul " + name + " tidak ditemukan");
        }
    }

    private List<Student> findExculParticipants(Excul excul) {
        List<Student> result = new ArrayList<>();
        for (Student m : getDatabase().fetchStudents()) {
            for (Excul e : m.getParticipatingExculs()) {
                if (e.getName().equals(excul.getName())) {
                    result.add(m);
                }
            }
        }
        return result;
    }
}
