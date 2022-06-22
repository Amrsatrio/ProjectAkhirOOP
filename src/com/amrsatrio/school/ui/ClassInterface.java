package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;
import com.amrsatrio.school.model.SchoolClass;
import com.amrsatrio.school.model.Student;
import com.amrsatrio.school.util.Utils;

import java.util.List;

import static com.amrsatrio.school.util.Utils.promptInt;
import static java.lang.System.out;

public class ClassInterface extends Interface {
    public ClassInterface(Main main) {
        super(main);
    }

    @Override
    public boolean menu() {
        out.println("== Kelola Kelas ==");
        out.println();
        out.println(getDatabase().getNumClasses() + " kelas terdaftar");
        out.println("1. Lihat semua");
        //out.println("2. Tambah");
        //out.println("3. Ubah");
        //out.println("4. Hapus");
        out.println();
        out.println("0. Kembali");

        int pilih = promptInt(scanner, ">> ", 0, 4);
        if (pilih == 0) return false;
        else if (pilih == 1) showClasses();
        //else if (pilih == 2) insertClass();
        //else if (pilih == 3) updateClass();
        //else if (pilih == 4) deleteClass();

        return true;
    }

    public void showClasses() {
        boolean first = true;
        for (SchoolClass schoolClass : getDatabase().fetchClasses()) {
            if (first) {
                first = false;
            } else {
                out.println();
            }
            out.printf("%s T/A %d-%d\n", schoolClass.getName(), schoolClass.getYear(), schoolClass.getYear() + 1);
            List<Student> students = schoolClass.getStudents();
            out.printf("Murid-murid (%d): %s\n", students.size(), Utils.joinNiceString(students));
        }
        out.println();
    }
}
