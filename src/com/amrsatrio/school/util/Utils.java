package com.amrsatrio.school.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Utils {
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    public static String strRepeat(String s, int n) {
        return String.join("", Collections.nCopies(n, s));
    }

    public static String joinNiceString(List<?> strings) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Object it : strings) {
            if (i > 0) {
                if (i == strings.size() - 1) {
                    sb.append(", dan ");
                } else {
                    sb.append(", ");
                }
            }

            sb.append(it);
            i++;
        }

        return sb.toString();
    }

    public static int promptInt(Scanner scanner, String text, int lower, int upper) {
        int pilih;
        while (true) {
            out.print(text);
            try {
                pilih = scanner.nextInt();
                scanner.nextLine();
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
}
