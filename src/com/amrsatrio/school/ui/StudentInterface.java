package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;
import com.amrsatrio.school.model.Excul;
import com.amrsatrio.school.model.SchoolClass;
import com.amrsatrio.school.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.amrsatrio.school.util.Utils.*;
import static java.lang.System.out;

public class StudentInterface extends PersonInterface {
    public StudentInterface(Main main) {
        super(main);
    }

    @Override
    public boolean menu() {
        out.println("== Kelola Murid ==");
        out.println();
        out.println(getDatabase().getNumStudents() + " murid terdaftar");
        out.println("1. Lihat semua");
        out.println("2. Tambah");
        out.println("3. Ubah");
        out.println("4. Hapus");
        out.println();
        out.println("0. Kembali");

        int pilih = promptInt(scanner, ">> ", 0, 4);
        if (pilih == 0) return false;
        else if (pilih == 1) showStudents();
        else if (pilih == 2) insertStudent();
        else if (pilih == 3) updateStudent();
        else if (pilih == 4) deleteStudent();

        return true;
    }

    public void insertStudent() {
        Student student = new Student();

        promptPerson(student);

        List<Excul> exculDiikuti = promptExcul();
        student.setParticipatingExculs(exculDiikuti);
        out.printf("%d ekskul berhasil ditentukan\n", exculDiikuti.size());

        getDatabase().insertStudent(student);

        List<SchoolClass> classes = getDatabase().fetchClasses();

        int random = new Random().nextInt(classes.size());
        SchoolClass chosenClass = classes.get(random);
        getDatabase().addStudentToClass(student, chosenClass);
        out.println("Murid '" + student.getName() + "' berhasil ditambahkan ke kelas " + chosenClass.getName());
    }

    public void showStudents() {
        int w = 144;
        out.println(strRepeat("=", w));
        String fmt = "| %-5s | %-15s | %-2s | %-10s | %-20s | %-12s | %-25s | %-30s |\n";
        out.printf(fmt, "ID", "Nama", "JK", "Tgl. Lahir", "Email", "No. Telepon", "Alamat", "Ekskul");
        out.println(strRepeat("=", w));
        for (Student student : getDatabase().fetchStudents()) {
            out.printf(fmt,
                    student.getId(),
                    student.getName(),
                    student.getGender(),
                    DF.format(student.getDateOfBirth()),
                    student.getEmail(),
                    student.getPhone(),
                    student.getAddress(),
                    student.getParticipatingExculs());
        }
        out.println(strRepeat("=", w));
        out.println();
    }

    public void updateStudent() {
        Student student = findStudent();
        if (student == null) {
            return;
        }
        out.printf("== Update Data %s - %s ==\n", student.getId(), student.getName());
        out.println("1. Nama");
        out.println("2. Gender");
        out.println("3. Tanggal Lahir");
        out.println("4. Email");
        out.println("5. Nomor Telepon");
        out.println("6. Alamat");
        out.println("7. Ekskul");
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

        if (choice == 0) {
            return;
        }
        if (updatePerson(student, choice)) {
            // Already handled
        } else if (choice == 7) {
            List<Excul> exculDiikuti = promptExcul();
            student.setParticipatingExculs(exculDiikuti);
        } else {
            return;
        }

        getDatabase().updateStudent(student);
        out.println("Data berhasil diperbarui");
    }

    public void deleteStudent() {
        Student student = findStudent();
        if (student != null) {
            getDatabase().deleteStudent(student);
            out.println("Murid " + student.getName() + " dengan ID " + student.getId() + " berhasil dihapus");
        }
    }

    private Student findStudent() {
        showStudents();
        String id;
        while (true) {
            out.print("Masukkan ID Murid (ST(3 angka) atau '0' untuk batal): ");
            id = scanner.nextLine();
            if (id.equals("0")) {
                return null;
            }
            if (id.length() != 5 || !id.startsWith("ST")) {
                continue;
            }
            Student student = getDatabase().findStudentById(id);
            if (student != null) {
                return student;
            }
            out.println("Murid dengan ID " + id + " tidak ditemukan");
        }
    }

    private List<Excul> promptExcul() {
        out.println("Memilih Ekskul");
        //int max = 2;

        // Print daftar ekskul
        List<Excul> allExculs = getDatabase().fetchExculs();
        for (int i = 0; i < allExculs.size(); i++) {
            out.println((i + 1) + ". " + allExculs.get(i).getName());
        }

        List<Excul> exculDiikuti = new ArrayList<>();
        while (true) {
            out.print("Pilih ekskul yang ingin diikuti, atau '0' untuk lanjut: ");
            String response = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                continue;
            }
            if (choice == 0) {
                break;
            }
            if (choice < 1 || choice > allExculs.size()) {
                continue;
            }
            exculDiikuti.add(allExculs.get(choice - 1));
            //if (exculDiikuti.size() == max) {
            break;
            //}
        }
        return exculDiikuti;
    }
}
