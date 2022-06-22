package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;

import static com.amrsatrio.school.util.Utils.promptInt;
import static java.lang.System.out;

public class MainInterface extends Interface {
    private final StudentInterface studentInterface = new StudentInterface(main);
    private final TeacherInterface teacherInterface = new TeacherInterface(main);
    private final ClassInterface classInterface = new ClassInterface(main);
    private final SubjectInterface subjectInterface = new SubjectInterface(main);
    private final ExculInterface exculInterface = new ExculInterface(main);

    public MainInterface(Main main) {
        super(main);
    }

    @Override
    public boolean menu() {
        out.println("== Menu Sekolah ==");
        out.println();
        out.println("1. Kelola Murid");
        out.println("2. Kelola Guru");
        out.println("3. Lihat Daftar Kelas");
        out.println("4. Lihat Daftar Mata Pelajaran");
        out.println("5. Kelola Ekstrakurikuler");
        out.println();
        out.println("0. Keluar");

        int input = promptInt(scanner, ">> ", 0, 5);
        if (input == 0) return false;
        else if (input == 1) openInterface(studentInterface);
        else if (input == 2) openInterface(teacherInterface);
        else if (input == 3) classInterface.showClasses();
        else if (input == 4) subjectInterface.showSubjects();
        else if (input == 5) openInterface(exculInterface);

        return true;
    }
}
