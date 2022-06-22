package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;
import com.amrsatrio.school.model.Person;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;

import static com.amrsatrio.school.util.Utils.DF;
import static com.amrsatrio.school.util.Utils.EMAIL_PATTERN;
import static java.lang.System.out;

public abstract class PersonInterface extends Interface {
    public PersonInterface(Main main) {
        super(main);
    }

    protected void promptPerson(Person person) {
        String nama;
        while (true) {
            out.print("Masukkan Nama: ");
            nama = scanner.nextLine();
            if (nama.length() == 0) {
                out.println("Nama tidak boleh kosong");
                continue;
            }
            break;
        }
        person.setName(nama);

        String gender;
        while (true) {
            out.print("Masukkan Gender (L / P): ");
            gender = scanner.nextLine().toUpperCase(Locale.ROOT);
            if (!gender.equals("L") && !gender.equals("P")) {
                out.println("Gender harus L atau P");
                continue;
            }
            break;
        }
        person.setGender(gender);

        Date tanggalLahir;
        while (true) {
            out.print("Masukkan Tanggal Lahir (YYYY-MM-DD): ");
            try {
                tanggalLahir = DF.parse(scanner.nextLine());
            } catch (ParseException e) {
                out.println("Format tanggal salah");
                continue;
            }
            break;
        }
        person.setDateOfBirth(tanggalLahir);

        String email;
        while (true) {
            out.print("Masukkan Email (harus berdomain gmail.com): ");
            email = scanner.nextLine();
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            if (!matcher.matches() || !email.endsWith("@gmail.com")) {
                out.println("Email tidak valid");
                continue;
            }
            break;
        }
        person.setEmail(email);

        String noTelp;
        while (true) {
            out.print("Masukkan Nomor Telepon (10-13 angka, dimulai dengan 0): ");
            noTelp = scanner.nextLine().replaceAll("[^0-9]", "");
            if (noTelp.isEmpty() || noTelp.charAt(0) != '0' || noTelp.length() < 10 || noTelp.length() > 13) {
                out.println("Nomor telepon tidak valid");
                continue;
            }
            break;
        }
        person.setPhone(noTelp);

        String alamat;
        while (true) {
            out.print("Masukkan Alamat: ");
            alamat = scanner.nextLine();
            if (alamat.isEmpty()) {
                out.println("Alamat tidak boleh kosong");
                continue;
            }
            break;
        }
        person.setAddress(alamat);
    }

    protected boolean updatePerson(Person person, int choice) {
        if (choice == 1) {
            String nama;
            while (true) {
                out.print("Masukkan Nama: ");
                nama = scanner.nextLine();
                if (nama.length() == 0) {
                    out.println("Nama tidak boleh kosong");
                    continue;
                }
                break;
            }
            person.setName(nama);
            return true;
        } else if (choice == 2) {
            String gender;
            while (true) {
                out.print("Masukkan Gender (L / P): ");
                gender = scanner.nextLine().toUpperCase(Locale.ROOT);
                if (!gender.equals("L") && !gender.equals("P")) {
                    out.println("Gender harus L atau P");
                    continue;
                }
                break;
            }
            return true;
        } else if (choice == 3) {
            Date tanggalLahir;
            while (true) {
                out.print("Masukkan Tanggal Lahir (YYYY-MM-DD): ");
                try {
                    tanggalLahir = DF.parse(scanner.nextLine());
                } catch (ParseException e) {
                    out.println("Format tanggal salah");
                    continue;
                }
                break;
            }
            person.setDateOfBirth(tanggalLahir);
            return true;
        } else if (choice == 4) {
            String email;
            while (true) {
                out.print("Masukkan Email (harus berdomain gmail.com): ");
                email = scanner.nextLine();
                Matcher matcher = EMAIL_PATTERN.matcher(email);
                if (!matcher.matches() || !email.endsWith("@gmail.com")) {
                    out.println("Email tidak valid");
                    continue;
                }
                break;
            }
            person.setEmail(email);
            return true;
        } else if (choice == 5) {
            String noTelp;
            while (true) {
                out.print("Masukkan Nomor Telepon (10-13 angka, dimulai dengan 0): ");
                noTelp = scanner.nextLine().replaceAll("[^0-9]", "");
                if (noTelp.isEmpty() || noTelp.charAt(0) != '0' || noTelp.length() < 10 || noTelp.length() > 13) {
                    out.println("Nomor telepon tidak valid");
                    continue;
                }
                break;
            }
            person.setPhone(noTelp);
            return true;
        } else if (choice == 6) {
            String alamat;
            while (true) {
                out.print("Masukkan Alamat: ");
                alamat = scanner.nextLine();
                if (alamat.isEmpty()) {
                    out.println("Alamat tidak boleh kosong");
                    continue;
                }
                break;
            }
            person.setAddress(alamat);
            return true;
        } else {
            return false;
        }
    }
}
