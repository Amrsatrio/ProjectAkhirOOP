package com.amrsatrio.school.ui;

import com.amrsatrio.school.Main;
import com.amrsatrio.school.database.DatabaseManager;

import java.util.Scanner;

public abstract class Interface {
    protected final Main main;
    protected final Scanner scanner;
    private boolean inInnerMenu;

    public Interface(Main main) {
        this.main = main;
        this.scanner = main.getScanner();
    }

    public boolean menu() {
        return false;
    }

    public final void invoke() {
        while (menu()) {
            if (!inInnerMenu) {
                enterToContinue();
            }
            inInnerMenu = false;
        }
    }

    protected final void openInterface(Interface interfaceToOpen) {
        inInnerMenu = true;
        interfaceToOpen.invoke();
    }

    protected final DatabaseManager getDatabase() {
        return main.getDatabase();
    }

    protected final void enterToContinue() {
        System.out.println("Tekan Enter untuk kembali...");
        scanner.nextLine();
    }
}
