package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;
import com.amrsatrio.school.model.Subject;

import static java.lang.System.out;

public class SubjectInterface extends Interface {
    public SubjectInterface(Main main) {
        super(main);
    }

    public void showSubjects() {
        boolean first = true;
        for (Subject pelajaran : getDatabase().fetchSubjects()) {
            if (first) {
                first = false;
            } else {
                out.println();
            }
            out.println(pelajaran.getName());
            out.println("Pengajar: " + pelajaran.getTeacher());
        }
        out.println();
    }

    /*private List<Teacher> getSubjectTeachers(Subject subject) {
        List<Teacher> result = new ArrayList<>();
        for (Teacher m : getDatabase().fetchTeachers()) {
            for (Subject e : m.getSubjects()) {
                if (e.getName().equals(subject.getName())) {
                    result.add(m);
                }
            }
        }
        return result;
    }*/
}
