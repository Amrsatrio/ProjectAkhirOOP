package com.amrsatrio.school;

import com.amrsatrio.school.database.DatabaseManager;
import com.amrsatrio.school.ui.MainInterface;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager database;

    public Main() {
        try {
            database = new DatabaseManager();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        new MainInterface(this).invoke();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public DatabaseManager getDatabase() {
        return database;
    }

    public static void main(String[] args) {
        new Main();
    }
}
