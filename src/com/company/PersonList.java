package com.company;

import javax.swing.*;
import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonList implements WriteReadList {

    private List<Person> list;

    public PersonList() {
        list = new ArrayList<>();
    }

    public PersonList(List<Person> list) {
        this.list = list;
    }

    public void addPerson(Person person) {
        list.add(person);
    }

    public void removePerson(int index) {
        list.remove(index);
    }

    public void editPerson(int index, Person person) {
        list.set(index, person);
    }

    public PersonList sortList() {
        List<Person> nameList = list;
        Collections.sort(nameList);
        return new PersonList(nameList);
    }

    public PersonList sortByName(){
        List<Person> nameList = list;
        nameList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                return person.getSurname().compareTo(t1.getSurname());
            }
        });
        return new PersonList(nameList);
    }

    public PersonList searchByName(String name) {
        List<Person> nameList = new ArrayList<>();
        for (Person p : list) {
            if (p.getName().equals(name)) {
                nameList.add(p);
            }
        }
        return new PersonList(nameList);
    }

    public PersonList searchBy(String searchObject, String searchBy) {
        List<Person> nameList = new ArrayList<>();
        for (Person p : list) {
            if (searchBy.equals("name")) {
                if (p.getName().equals(searchObject)) {
                    nameList.add(p);
                }
            } else if (searchBy.equals("surname")) {
                if (p.getSurname().equals(searchObject)) {
                    nameList.add(p);
                }
            } else if (searchBy.equals("position")) {
                if (p.getPosition().equals(searchObject)) {
                    nameList.add(p);
                }
            } else if (searchBy.equals("age")) {
                if (p.getAge() == Integer.parseInt(searchObject)) {
                    nameList.add(p);
                }
            } else if (searchBy.equals("seniority")) {
                if (p.getSeniority() == Integer.parseInt(searchObject)) {
                    nameList.add(p);
                }
            } else if (searchBy.equals("salary")) {
                if (p.getSalary() == Integer.parseInt(searchObject)) {
                    nameList.add(p);
                }
            } else if (searchBy.equals("salaryUp")) {
                if (p.getSalary() >= Integer.parseInt(searchObject)) {
                    nameList.add(p);
                }
            } else if (searchBy.equals("salaryDown")) {
                if (p.getSalary() < Integer.parseInt(searchObject)) {
                    nameList.add(p);
                }
            }

        }
        return new PersonList(nameList);
    }

    public int getSizePersonList() {
        return list.size() - 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            stringBuilder.append(i + ": " + list.get(i) + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void read() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            String line;
            try {
                BufferedReader input = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
                if (!input.ready()) {
                    throw new IOException();
                }

                Pattern p = Pattern.compile("^(.+)(\\\\+)(.+)(.txt)");
                System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
                Matcher m = p.matcher(fileChooser.getSelectedFile().getAbsolutePath());
                boolean b = m.matches();
                if (!b){
                    throw new Exception();
                }

                while ((line = input.readLine()) != null) {
                    String[] vals = line.split(" ");
                    addPerson(new Person(vals[1], vals[3], vals[5], Integer.parseInt(vals[7]), Integer.parseInt(vals[9]), Integer.parseInt(vals[11])));
                }
                input.close();
                JOptionPane.showMessageDialog(null, "File read", null, JOptionPane.INFORMATION_MESSAGE);
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: Can not find file or bad format!", null, JOptionPane.ERROR_MESSAGE);
            }catch (Exception ex1){
                JOptionPane.showMessageDialog(null, "ERROR: Bad format file", null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void write() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                FileWriter fw = new FileWriter(file);
                Writer output = new BufferedWriter(fw);
                for (int i = 0; i < list.size(); ++i) {
                    output.write(list.get(i).toString() + "\n");
                }
                output.close();
                JOptionPane.showMessageDialog(null, "File created", null, JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR: Can not create that file!", null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

/*

    @Override
    public void read() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION){
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
        }
    }
 */
