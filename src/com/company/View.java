package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View extends JFrame {

    private PersonList personList;
    private JTextArea textArea;
    private String searchComboBox;

    public View(String title, PersonList personList){
        this.personList = personList;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //MENU BAR
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu menu2 = new JMenu("Help");
        menuBar.add(menu);
        menuBar.add(menu2);
        JMenuItem aboutMeItem = new JMenuItem("About me", new ImageIcon("Icons\\aboutMe.png"));
        menu2.add(aboutMeItem);
        JMenuItem saveFileItem = new JMenuItem("Save", new ImageIcon("Icons\\save.png"));
        JMenuItem loadFileItem = new JMenuItem("Load", new ImageIcon("Icons\\load.png"));
        JMenuItem exitFileItem = new JMenuItem("Exit", new ImageIcon("Icons\\exit.png"));
        menu.add(loadFileItem);
        menu.add(saveFileItem);
        menu.add(exitFileItem);


        //Menu down
        JPanel menuDown = new JPanel();

        JButton addButton = new JButton("Add person");
        menuDown.add(addButton);
        JButton removeButton = new JButton("Remove person");
        menuDown.add(removeButton);
        JButton editButton = new JButton("Edit person");
        menuDown.add(editButton);
        JButton refresh = new JButton("Refresh");
        menuDown.add(refresh);


        //Menu Up
        JPanel menuUp = new JPanel();
        JButton sortButton = new JButton("Sort");
        menuUp.add(sortButton);

        JButton sortSurname = new JButton("Sort Surname");
        menuUp.add(sortSurname);

        String[] searchBy = {"name", "surname","position", "age", "seniority", "salary", "salaryUp", "salaryDown"};
        JComboBox comboBox = new JComboBox(searchBy);
        menuUp.add(comboBox);

        JTextField searchByNameField = new JTextField(20);
        menuUp.add(searchByNameField);
        JButton searchButton = new JButton("Search by name");
        menuUp.add(searchButton);


        panel.add(menuUp, BorderLayout.PAGE_START);
        panel.add(menuDown, BorderLayout.PAGE_END);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);


        //Menubar ACTION Listener
        saveFileItem.addActionListener(e->{
            personList.write();
            refreshTextArea();
        });

        loadFileItem.addActionListener(e->{
            personList.read();
            refreshTextArea();
        });

        exitFileItem.addActionListener(e->{
            for(int i=2147483647;i>-2147483648;i--) {
                JOptionPane.showMessageDialog(null, "Good luck :)\nPress left: " + i, null, JOptionPane.INFORMATION_MESSAGE);
            }
            System.exit(0);
        });

        aboutMeItem.addActionListener(e->{
            JOptionPane.showMessageDialog(null, "My name is Maciej \nThis is project for school", null, JOptionPane.INFORMATION_MESSAGE);
        });

        //BUTTONS ACTION Listener
        sortSurname.addActionListener(e -> {
            PersonList sortSurname2 = personList.sortByName();
            textArea.setText(sortSurname2.toString());
        });

        comboBox.addActionListener(e -> {
            searchComboBox = comboBox.getSelectedItem().toString();
        });

        sortButton.addActionListener(e -> {
            PersonList nameList = personList.sortList();
            textArea.setText(nameList.toString());
        });

        searchButton.addActionListener(e -> {
            String name = searchByNameField.getText();
            PersonList nameList = personList.searchBy(name, searchComboBox);
            textArea.setText(nameList.toString());
        });

        addButton.addActionListener(e -> {
            new AddPersonView(personList, this);
        });

        removeButton.addActionListener(e -> {
            try {
                int index = selectItemButton();
                if (index > personList.getSizePersonList()){
                    throw new IndexOutOfBoundsException();
                }
                personList.removePerson(index);
                refreshTextArea();
            }catch(IndexOutOfBoundsException ex1){
                JOptionPane.showMessageDialog(null, "ERROR: This index not exist. Provide correct index", null, JOptionPane.ERROR_MESSAGE);
            }catch(NumberFormatException ex2){
                JOptionPane.showMessageDialog(null, "ERROR: You must provide number!", null, JOptionPane.ERROR_MESSAGE);
            }
        });

        editButton.addActionListener(e->{
            try {
                int index = selectItemButton();
                if (index > personList.getSizePersonList()){
                    throw new IndexOutOfBoundsException();
                }
                AddPersonView addPersonView = new AddPersonView(personList, this);
                addPersonView.setIndex(index);
            }catch(IndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null, "ERROR: This index not exist. Provide correct index", null, JOptionPane.ERROR_MESSAGE);
            }catch(NumberFormatException ex2){
                JOptionPane.showMessageDialog(null, "ERROR: You must provide number!", null, JOptionPane.ERROR_MESSAGE);
            }
        });

        refresh.addActionListener(e -> {
            refreshTextArea();
        });



        this.setTitle(title);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    public int selectItemButton(){
        String input = "";
        input = JOptionPane.showInputDialog("Please select person to remove: (type id)");
        return Integer.parseInt(input);
    }

    public void refreshTextArea(){
        textArea.setText(personList.toString());
    }

}
/*

try {
            input = JOptionPane.showInputDialog("Please select person to remove: (type id)");

            if (Integer.parseInt(input) > personList.getSizePersonList()){
                throw new IndexOutOfBoundsException();
            }
        }catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "ERROR: This index not exist. Provide correct index", null, JOptionPane.ERROR_MESSAGE);
        }

if (comboBox.getSelectedItem().toString().equals("name")){
                searchButton.addActionListener(new java.awt.event.ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        String name = searchByNameField.getText();
                        PersonList nameList = personList.searchByName(name);
                        textArea.setText(nameList.toString());
                    }
                });
            }else if(comboBox.getSelectedItem().toString().equals("age")){
                searchButton.addActionListener(new java.awt.event.ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int age = Integer.parseInt(searchByNameField.getText());
                        PersonList nameList = personList.searchByAge(age);
                        textArea.setText(nameList.toString());
                    }
                });
            }
 */
