package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        PersonList personList = new PersonList();
        SwingUtilities.invokeLater(() -> {
            try {
                new View("Project 1", personList);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
