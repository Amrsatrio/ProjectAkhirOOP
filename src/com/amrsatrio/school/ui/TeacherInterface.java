package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;
import com.amrsatrio.school.model.Subject;
import com.amrsatrio.school.model.Teacher;

import java.util.ArrayList;
import java.util.List;

import static com.amrsatrio.school.util.Utils.*;
import static java.lang.System.out;

public class TeacherInterface extends PersonInterface {
    public TeacherInterface(Main main) {
        super(main);
    }

    @Override
    public boolean menu() {
        out.println("== Kelola Guru ==");
        out.println();
        out.println(getDatabase().getNumTeachers() + " guru terdaftar");
        out.println("1. Lihat semua");
        out.println("2. Tambah");
        out.println("3. Ubah");
        out.println("4. Hapus");
        out.println();
        out.println("0. Kembali");

        int pilih = promptInt(scanner, ">> ", 0, 4);
        if (pilih == 0) return false;
        else if (pilih == 1) showTeachers();
        else if (pilih == 2) insertTeacher();
        else if (pilih == 3) updateTeacher();
        else if (pilih == 4) deleteTeacher();
        return true;
    }

    public void insertTeacher() {
        Teacher teacher = new Teacher();

        promptPerson(teacher);

        /*List<Subject> subjects = promptSubjects();
        teacher.setSubjects(subjects);
        out.printf("%d pelajaran berhasil ditentukan\n", subjects.size());*/

        getDatabase().insertTeacher(teacher);
        out.println("Guru berhasil ditambahkan");
    }

    public void showTeachers() {
        int i = 166;
        out.println(strRepeat("=", i));
        String fmt = "| %-5s | %-20s | %-2s | %-10s | %-30s | %-12s | %-65s |\n";
        out.printf(fmt, "ID", "Nama", "JK", "Tgl. Lahir", "Email", "No. Telepon", "Alamat");
        out.println(strRepeat("=", i));
        for (Teacher teacher : getDatabase().fetchTeachers()) {
            out.printf(fmt,
                    teacher.getId(),
                    teacher.getName(),
                    teacher.getGender(),
                    DF.format(teacher.getDateOfBirth()),
                    teacher.getEmail(),
                    teacher.getPhone(),
                    teacher.getAddress());
        }
        out.println(strRepeat("=", i));
        out.println();
    }

    public void updateTeacher() {
        Teacher teacher = findTeacher();
        if (teacher == null) {
            return;
        }
        out.printf("Update Data %s - %s\n", teacher.getId(), teacher.getName());
        out.println("1. Nama");
        out.println("2. Gender");
        out.println("3. Tanggal Lahir");
        out.println("4. Email");
        out.println("5. Nomor Telepon");
        out.println("6. Alamat");
        //out.println("7. Pelajaran-pelajaran yang diajar");
        out.println("0. Kembali");

        int choice;
        while (true) {
            out.print("Pilih: ");
            choice = scanner.nextInt();
            if (choice < 0 || choice > 8) {
                out.println("Pilihan tidak valid");
                continue;
            }
            scanner.nextLine();
            break;
        }

        if (updatePerson(teacher, choice)) {
            // Already handled
        } /*else if (choice == 7) {
            List<Subject> subjects = promptSubjects();
            teacher.setSubjects(subjects);
        }*/ else {
            return;
        }

        getDatabase().updateTeacher(teacher);
        out.println("Data berhasil diperbarui");
    }

    public void deleteTeacher() {
        Teacher teacher = findTeacher();
        if (teacher != null) {
            getDatabase().deleteTeacher(teacher);
            out.println("Guru " + teacher.getName() + " dengan ID " + teacher.getId() + " berhasil dihapus");
        }
    }

    private Teacher findTeacher() {
        showTeachers();
        String id;
        while (true) {
            out.print("Masukkan ID Guru (TE(3 angka) atau '0' untuk batal): ");
            id = scanner.nextLine();
            if (id.equals("0")) {
                return null;
            }
            if (id.length() != 5 || !id.startsWith("TE")) {
                continue;
            }
            Teacher teacher = getDatabase().findTeacherById(id);
            if (teacher != null) {
                return teacher;
            }
            out.println("Guru dengan ID " + id + " tidak ditemukan");
        }
    }

    private List<Subject> promptSubjects() {
        // Print daftar pelajaran
        List<Subject> allSubjects = getDatabase().fetchSubjects();
        for (int i = 0; i < allSubjects.size(); i++) {
            out.println((i + 1) + ". " + allSubjects.get(i).getName());
        }

        out.println("Memilih pelajaran yang diajar");
        int max = 2;

        List<Subject> subjects = new ArrayList<>();
        while (true) {
            out.printf("Pilih nomor untuk pelajaran ke-%d dari %d, atau 'next' untuk lanjut: ", subjects.size() + 1, max);
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("next")) {
                break; // Cukup
            }
            int choice;
            try {
                choice = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                continue;
            }
            if (choice < 1 || choice > allSubjects.size()) {
                continue;
            }
            subjects.add(allSubjects.get(choice - 1));
            if (subjects.size() == max) {
                break;
            }
        }
        return subjects;
    }
}
