import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Main {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
    private Scanner scan = new Scanner(System.in);
    private List<Murid> listMurid = new ArrayList<>();
    private List<Guru> listGuru = new ArrayList<>();
    private List<MataPelajaran> listMataPelajaran = new ArrayList<>();
    private List<Ekskul> listEkskul = new ArrayList<>();
    private int lastMurid = 0;
    private int lastGuru = 0;

    public Main() {
        /*Guru dummy = new Guru();
        dummy.setId("M00001");
        dummy.setNama("Dummy");
        dummy.setAlamat("Dummy");
        dummy.setEmail("asd@a.com");
        dummy.setKelas(1);
        dummy.setTanggalLahir(new Date());
        dummy.setGender("L");
        dummy.setNoTelp("08123456789");
        dummy.setSubjects(new ArrayList<>());
        listGuru.add(dummy);
        showDataGuru();
        System.exit(0);*/

        // Setup pelajaran
        listMataPelajaran.add(new MataPelajaran("Matematika"));
        listMataPelajaran.add(new MataPelajaran("PKn"));
        listMataPelajaran.add(new MataPelajaran("IPA (Biologi/Fisika/Kimia)"));
        listMataPelajaran.add(new MataPelajaran("IPS (Sosiologi/Geografi/Sejarah)"));
        listMataPelajaran.add(new MataPelajaran("Bahasa Inggris"));
        listMataPelajaran.add(new MataPelajaran("Bahasa Indonesia"));
        listMataPelajaran.add(new MataPelajaran("Penjasorkes"));

        // Setup ekskul
        listEkskul.add(new Ekskul("Futsal"));
        listEkskul.add(new Ekskul("Basket"));
        listEkskul.add(new Ekskul("Badminton"));
        listEkskul.add(new Ekskul("Volley"));
        listEkskul.add(new Ekskul("TIK"));
        listEkskul.add(new Ekskul("Panahan"));
        listEkskul.add(new Ekskul("Tari Tradisional"));

        while (true) {
            out.println("Menu");
            out.println("1. Insert Data");
            out.println("2. Update Data");
            out.println("3. Delete Data");
            out.println("4. Menampilkan Data");
            out.println("0. Keluar");

            int input = promptInt(">> ", 0, 4);
            switch (input) {
                case 1:
                    insertData();
                    break;
                case 2:
                    updateData();
                    break;
                case 3:
                    deleteData();
                    break;
                case 4:
                    showData();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

            System.out.println("Tekan Enter untuk kembali ke menu utama...");
            scan.nextLine();
        }
    }

    private List<Murid> getMuridYangIkutEkskul(Ekskul ekskul) {
        List<Murid> result = new ArrayList<>();
        for (Murid m : listMurid) {
            for (Ekskul e : m.getEkskulDiikuti()) {
                if (e.getNama().equals(ekskul.getNama())) {
                    result.add(m);
                }
            }
        }
        return result;
    }

    // region Insert Data
    private void insertData() {
        out.println("1. Insert Data Murid");
        out.println("2. Insert Data Guru");
        out.println("3. Insert Data Ekskul");
        out.println("0. Kembali");
        int pilih = promptInt(">> ", 0, 3);
        if (pilih == 1) {
            insertDataMurid();
        } else if (pilih == 2) {
            insertDataGuru();
        } else if (pilih == 3) {
            insertDataEkskul();
        }
    }

    /**
     * @author Lawysen
     */
    private void insertDataMurid() {
        Murid murid = new Murid();

        String id = String.format("M%05d", ++lastMurid);
        murid.setId(id);

        promptSdm(murid);

        int kelas = promptInt("Masukkan Kelas (1-12): ", 1, 12);
        murid.setKelas(kelas);

        List<Ekskul> ekskulDiikuti = promptEkskul();
        murid.setEkskulDiikuti(ekskulDiikuti);
        out.printf("%d ekskul berhasil ditentukan\n", ekskulDiikuti.size());

        listMurid.add(murid);
        out.println("Murid berhasil ditambahkan");
    }

    private List<Ekskul> promptEkskul() {
        out.println("Memilih Ekskul");
        int max = 2;

        // Print daftar ekskul
        for (int i = 0; i < listEkskul.size(); i++) {
            out.println((i + 1) + ". " + listEkskul.get(i).getNama());
        }

        List<Ekskul> ekskulDiikuti = new ArrayList<>();
        while (true) {
            out.printf("Pilih nomor untuk ekskul ke-%d dari %d, atau 'next' untuk lanjut: ", ekskulDiikuti.size() + 1, max);
            String response = scan.nextLine();
            if (response.equalsIgnoreCase("next")) {
                break; // Cukup
            }
            int choice;
            try {
                choice = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                continue;
            }
            if (choice < 1 || choice > listEkskul.size()) {
                continue;
            }
            ekskulDiikuti.add(listEkskul.get(choice - 1));
            if (ekskulDiikuti.size() == max) {
                break;
            }
        }
        return ekskulDiikuti;
    }

    /**
     * @author Nelsen Anggara
     */
    private void insertDataGuru() {
        Guru guru = new Guru();

        String id = String.format("K%05d", ++lastGuru);
        guru.setId(id);

        promptSdm(guru);

        List<MataPelajaran> subjects = promptSubjects();
        guru.setSubjects(subjects);
        out.printf("%d pelajaran berhasil ditentukan\n", subjects.size());

        listGuru.add(guru);
        out.println("Guru berhasil ditambahkan");
    }

    private List<MataPelajaran> promptSubjects() {
        // Print daftar pelajaran
        for (int i = 0; i < listMataPelajaran.size(); i++) {
            out.println((i + 1) + ". " + listMataPelajaran.get(i).getNama());
        }

        out.println("Memilih pelajaran yang diajar");
        int max = 2;

        List<MataPelajaran> subjects = new ArrayList<>();
        while (true) {
            out.printf("Pilih nomor untuk pelajaran ke-%d dari %d, atau 'next' untuk lanjut: ", subjects.size() + 1, max);
            String response = scan.nextLine();
            if (response.equalsIgnoreCase("next")) {
                break; // Cukup
            }
            int choice;
            try {
                choice = Integer.parseInt(response);
            } catch (NumberFormatException e) {
                continue;
            }
            if (choice < 1 || choice > listMataPelajaran.size()) {
                continue;
            }
            subjects.add(listMataPelajaran.get(choice - 1));
            if (subjects.size() == max) {
                break;
            }
        }
        return subjects;
    }

    private void insertDataEkskul() {
        String nama;
        while (true) {
            out.print("Nama Ekskul: ");
            nama = scan.nextLine();
            if (nama.isEmpty()) {
                out.println("Nama tidak boleh kosong");
                continue;
            }
            break;
        }

        listEkskul.add(new Ekskul(nama));
        out.println("Ekskul berhasil ditambahkan");
    }

    private void promptSdm(Sdm sdm) {
        String nama;
        while (true) {
            out.print("Masukkan Nama: ");
            nama = scan.nextLine();
            if (nama.length() == 0) {
                out.println("Nama tidak boleh kosong");
                continue;
            }
            break;
        }
        sdm.setNama(nama);

        String gender;
        while (true) {
            out.print("Masukkan Gender (L / P): ");
            gender = scan.nextLine().toUpperCase(Locale.ROOT);
            if (!gender.equals("L") && !gender.equals("P")) {
                out.println("Gender harus L atau P");
                continue;
            }
            break;
        }
        sdm.setGender(gender);

        Date tanggalLahir;
        while (true) {
            out.print("Masukkan Tanggal Lahir (YYYY-MM-DD): ");
            try {
                tanggalLahir = DF.parse(scan.nextLine());
            } catch (ParseException e) {
                out.println("Format tanggal salah");
                continue;
            }
            break;
        }
        sdm.setTanggalLahir(tanggalLahir);

        String email;
        while (true) {
            out.print("Masukkan Email: ");
            email = scan.nextLine();
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            if (!matcher.matches()) {
                out.println("Email tidak valid");
                continue;
            }
            break;
        }
        sdm.setEmail(email);

        String noTelp;
        while (true) {
            out.print("Masukkan Nomor Telepon (10-13 angka, dimulai dengan 0): ");
            noTelp = scan.nextLine().replaceAll("[^0-9]", "");
            if (noTelp.isEmpty() || noTelp.charAt(0) != '0' || noTelp.length() < 10 || noTelp.length() > 13) {
                out.println("Nomor telepon tidak valid");
                continue;
            }
            break;
        }
        sdm.setNoTelp(noTelp);

        String alamat;
        while (true) {
            out.print("Masukkan Alamat: ");
            alamat = scan.nextLine();
            if (alamat.isEmpty()) {
                out.println("Alamat tidak boleh kosong");
                continue;
            }
            break;
        }
        sdm.setAlamat(alamat);
    }
    // endregion

    // region Update Data
    /**
     * @author Mario
     */
    private void updateData() {
        out.println("1. Update Data Murid");
        out.println("2. Update Data Guru");
        out.println("0. Kembali");
        int pilih = promptInt(">> ", 0, 2);
        scan.nextLine();
        if (pilih == 1) {
            updateDataMurid();
        } else if (pilih == 2) {
            updateDataGuru();
        }
    }

    private void updateDataMurid() {
        Murid murid = findMurid();
        if (murid == null) {
            return;
        }
        out.printf("Update Data %s - %s\n", murid.getId(), murid.getNama());
        out.println("1. Nama");
        out.println("2. Gender");
        out.println("3. Tanggal Lahir");
        out.println("4. Email");
        out.println("5. Nomor Telepon");
        out.println("6. Alamat");
        out.println("7. Kelas");
        out.println("8. Ekskul");
        out.println("0. Kembali");

        int choice;
        while (true) {
            out.print("Pilih: ");
            choice = scan.nextInt();
            if (choice < 0 || choice > 8) {
                out.println("Pilihan tidak valid");
                continue;
            }
            scan.nextLine();
            break;
        }

        if (choice == 0) {
            return;
        }
        if (updateDataSdm(murid, choice)) {
            // Already handled
        } else if (choice == 7) {
            int kelas;
            while (true) {
                out.print("Masukkan Kelas: ");
                kelas = scan.nextInt();
                if (kelas < 1 || kelas > 12) {
                    out.println("Kelas tidak valid");
                    continue;
                }
                break;
            }
            murid.setKelas(kelas);
        } else if (choice == 8) {
            List<Ekskul> ekskulDiikuti = promptEkskul();
            murid.setEkskulDiikuti(ekskulDiikuti);
        } else {
            return;
        }
        out.println("Data berhasil diperbarui");
    }

    private void updateDataGuru() {
        Guru guru = findGuru();
        if (guru == null) {
            return;
        }
        out.printf("Update Data %s - %s\n", guru.getId(), guru.getNama());
        out.println("1. Nama");
        out.println("2. ");
        out.println("3. Tanggal Lahir");
        out.println("4. Email");
        out.println("5. Nomor Telepon");
        out.println("6. Alamat");
        out.println("7. Pelajaran-pelajaran yang diajar");
        out.println("0. Kembali");

        int choice;
        while (true) {
            out.print("Pilih: ");
            choice = scan.nextInt();
            if (choice < 0 || choice > 8) {
                out.println("Pilihan tidak valid");
                continue;
            }
            scan.nextLine();
            break;
        }

        if (updateDataSdm(guru, choice)) {
            // Already handled
        } else if (choice == 7) {
            List<MataPelajaran> subjects = promptSubjects();
            guru.setSubjects(subjects);
        } else {
            return;
        }
        out.println("Data berhasil diperbarui");
    }

    private boolean updateDataSdm(Sdm sdm, int choice) {
        if (choice == 1) {
            String nama;
            while (true) {
                out.print("Masukkan Nama: ");
                nama = scan.nextLine();
                if (nama.length() == 0) {
                    out.println("Nama tidak boleh kosong");
                    continue;
                }
                break;
            }
            sdm.setNama(nama);
            return true;
        } else if (choice == 2) {
            String gender;
            while (true) {
                out.print("Masukkan Gender (L / P): ");
                gender = scan.nextLine().toUpperCase(Locale.ROOT);
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
                    tanggalLahir = DF.parse(scan.nextLine());
                } catch (ParseException e) {
                    out.println("Format tanggal salah");
                    continue;
                }
                break;
            }
            sdm.setTanggalLahir(tanggalLahir);
            return true;
        } else if (choice == 4) {
            String email;
            while (true) {
                out.print("Masukkan Email: ");
                email = scan.nextLine();
                Matcher matcher = EMAIL_PATTERN.matcher(email);
                if (!matcher.matches()) {
                    out.println("Email tidak valid");
                    continue;
                }
                break;
            }
            sdm.setEmail(email);
            return true;
        } else if (choice == 5) {
            String noTelp;
            while (true) {
                out.print("Masukkan Nomor Telepon (10-13 angka, dimulai dengan 0): ");
                noTelp = scan.nextLine().replaceAll("[^0-9]", "");
                if (noTelp.isEmpty() || noTelp.charAt(0) != '0' || noTelp.length() < 10 || noTelp.length() > 13) {
                    out.println("Nomor telepon tidak valid");
                    continue;
                }
                break;
            }
            sdm.setNoTelp(noTelp);
            return true;
        } else if (choice == 6) {
            String alamat;
            while (true) {
                out.print("Masukkan Alamat: ");
                alamat = scan.nextLine();
                if (alamat.isEmpty()) {
                    out.println("Alamat tidak boleh kosong");
                    continue;
                }
                break;
            }
            sdm.setAlamat(alamat);
            return true;
        } else {
            return false;
        }
    }
    // endregion

    // region Delete Data
    /**
     * @author Satrio Nareswara W
     */
    private void deleteData() {
        out.println("1. Delete Data Murid");
        out.println("2. Delete Data Guru");
        out.println("3. Delete Data Ekskul");
        out.println("0. Kembali");
        int pilih = promptInt(">> ", 0, 3);
        if (pilih == 1) {
            deleteDataMurid();
        } else if (pilih == 2) {
            deleteDataGuru();
        } else if (pilih == 3) {
            deleteDataEkskul();
        }
    }

    private void deleteDataMurid() {
        Murid murid = findMurid();
        if (murid != null) {
            listMurid.remove(murid);
            out.println("Murid " + murid.getNama() + " dengan ID " + murid.getId() + " berhasil dihapus");
        }
    }

    private void deleteDataGuru() {
        Guru guru = findGuru();
        if (guru != null) {
            listGuru.remove(guru);
            out.println("Guru " + guru.getNama() + " dengan ID " + guru.getId() + " berhasil dihapus");
        }
    }

    private void deleteDataEkskul() {
        Ekskul ekskul = findEkskul();
        if (ekskul != null) {
            // Propagate to all Murid
            for (Murid m : listMurid) {
                List<Ekskul> ekskulDiikuti = m.getEkskulDiikuti();
                ekskulDiikuti.removeIf(e -> e.getNama().equals(ekskul.getNama()));
                m.setEkskulDiikuti(ekskulDiikuti);
            }
            listEkskul.remove(ekskul);
            out.println("Ekskul " + ekskul.getNama() + " berhasil dihapus");
        }
    }
    // endregion

    // region Menampilkan Data
    /**
     * @author Satrio Nareswara W
     */
    private void showData() {
        out.println("1. Menampilkan Data Murid");
        out.println("2. Menampilkan Data Guru");
        out.println("3. Menampilkan Data Ekskul");
        out.println("4. Menampilkan Data Pelajaran");
        out.println("0. Kembali");
        int pilih = promptInt(">> ", 0, 4);
        if (pilih == 1) {
            showDataMurid();
        } else if (pilih == 2) {
            showDataGuru();
        } else if (pilih == 3) {
            showDataEkskul();
        } else if (pilih == 4) {
            showDataPelajaran();
        }
    }

    private void showDataMurid() {
        out.println(strRepeat("=", 146));
        String fmt = "| %-6s | %-15s | %-2s | %-10s | %-20s | %-12s | %-20s | %-3s | %-30s |\n";
        out.printf(fmt, "ID", "Nama", "JK", "Tgl. Lahir", "Email", "No. Telepon", "Alamat", "Kls", "Ekskul");
        for (Murid murid : listMurid) {
            out.printf(fmt,
                    murid.getId(),
                    murid.getNama(),
                    murid.getGender(),
                    DF.format(murid.getTanggalLahir()),
                    murid.getEmail(),
                    murid.getNoTelp(),
                    murid.getAlamat(),
                    String.valueOf(murid.getKelas()),
                    murid.getEkskulDiikuti());
        }
        out.println(strRepeat("=", 146));
    }

    private static String strRepeat(String s, int n) {
        return String.join("", Collections.nCopies(n, s));
    }

    private void showDataGuru() {
        out.println(strRepeat("=", 140));
        String fmt = "| %-6s | %-15s | %-2s | %-10s | %-20s | %-12s | %-20s | %-30s |\n";
        out.printf(fmt, "ID", "Nama", "JK", "Tgl. Lahir", "Email", "No. Telepon", "Alamat", "Mengajar");
        for (Guru guru : listGuru) {
            out.printf(fmt,
                    guru.getId(),
                    guru.getNama(),
                    guru.getGender(),
                    DF.format(guru.getTanggalLahir()),
                    guru.getEmail(),
                    guru.getNoTelp(),
                    guru.getAlamat(),
                    guru.getSubjects());
        }
        out.println(strRepeat("=", 140));
    }

    private void showDataEkskul() {
        out.println("Name");
        for (Ekskul ekskul : listEkskul) {
            out.println(ekskul.getNama());
        }
    }

    private void showDataPelajaran() {
        out.println("Name");
        for (MataPelajaran pelajaran : listMataPelajaran) {
            out.println(pelajaran.getNama());
        }
    }
    // endregion

    private Murid findMurid() {
        showDataMurid();
        String id;
        while (true) {
            out.print("Masukkan ID Murid (M(5 angka) atau '0' untuk batal): ");
            id = scan.nextLine();
            if (id.equals("0")) {
                return null;
            }
            if (id.length() != 6 || id.charAt(0) != 'M') {
                continue;
            }
            for (Murid murid : listMurid) {
                if (murid.getId().equals(id)) {
                    return murid;
                }
            }
            out.println("Murid dengan ID " + id + " tidak ditemukan");
        }
    }

    private Guru findGuru() {
        showDataGuru();
        String id;
        while (true) {
            out.print("Masukkan ID Guru (K(5 angka) atau '0' untuk batal): ");
            id = scan.nextLine();
            if (id.equals("0")) {
                return null;
            }
            if (id.length() != 5 || id.charAt(0) != 'K') {
                continue;
            }
            for (Guru guru : listGuru) {
                if (guru.getId().equals(id)) {
                    return guru;
                }
            }
            out.println("Guru dengan ID " + id + " tidak ditemukan");
        }
    }

    private Ekskul findEkskul() {
        String name;
        while (true) {
            out.print("Masukkan Nama Ekskul (atau '0' untuk batal): ");
            name = scan.nextLine();
            if (name.equals("0")) {
                return null;
            }
            if (name.isEmpty()) {
                continue;
            }
            for (Ekskul ekskul : listEkskul) {
                if (ekskul.getNama().equals(name)) {
                    return ekskul;
                }
            }
            out.println("Ekskul " + name + " tidak ditemukan");
        }
    }

    private int promptInt(String text, int lower, int upper) {
        int pilih;
        while (true) {
            out.print(text);
            try {
                pilih = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                out.println("Input bukan angka");
                continue;
            }
            if (pilih < lower || pilih > upper) {
                out.println("Input harus di antara " + lower + " dan " + upper);
                continue;
            }
            break;
        }
        return pilih;
    }

    public static void main(String[] args) {
        new Main();
    }
}
