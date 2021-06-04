package com.company;

import javax.swing.*;
import java.awt.*;

public class AddPersonView extends JFrame {

    private PersonList personList;
    private View view;
    private int index = -1;
    private JComboBox comboBox;
    private JTextField[] addFields;
    private JButton sumbit;

    public AddPersonView(PersonList personList, View view){
        this.personList = personList;
        JPanel menu = new JPanel();
        this.view = view;

        this.setTitle("Adding Peroson");
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(menu);
        this.setVisible(true);

        initialize();


        JTextField textFieldEnum = new JTextField(10);


        comboBox.addActionListener(e -> {
            PositionType item = (PositionType)comboBox.getSelectedItem();
            textFieldEnum.setText(item + "");
        });

        sumbit.addActionListener(e->{

            try{
                Person person = new Person(addFields[0].getText(), addFields[1].getText(), textFieldEnum.getText(), Integer.parseInt(addFields[2].getText()), Integer.parseInt(addFields[3].getText()), Integer.parseInt(addFields[4].getText()));

                if (addFields[0].getText().isEmpty() || addFields[1].getText().isEmpty() || addFields[3].getText().isEmpty() || addFields[4].getText().isEmpty() || addFields[0].getText().isEmpty()){
                    throw new NullPointerException();
                }

                switch (textFieldEnum.getText()){
                    case "Developer":
                        if (!PositionType.DEVELOPER.salaryBrackets(Integer.parseInt(addFields[4].getText()))){
                            throw new IndexOutOfBoundsException();
                        }
                        break;
                    case "Accountant":
                        if (!PositionType.ACCOUNTANT.salaryBrackets(Integer.parseInt(addFields[4].getText()))){
                            throw new IndexOutOfBoundsException();
                        }
                        break;
                    case "Janitor":
                        if (!PositionType.JANITOR.salaryBrackets(Integer.parseInt(addFields[4].getText()))){
                            throw new IndexOutOfBoundsException();
                        }
                        break;
                    case "Lawyer":
                        if (!PositionType.LAWYER.salaryBrackets(Integer.parseInt(addFields[4].getText()))){
                            throw new IndexOutOfBoundsException();
                        }
                        break;
                    case "Trader":
                        if (!PositionType.TRADER.salaryBrackets(Integer.parseInt(addFields[4].getText()))){
                            throw new IndexOutOfBoundsException();
                        }
                        break;
                    default:
                        System.out.println("default");
                        break;
                }

                if (this.index != -1) {
                    personList.editPerson(index, person);
                    this.dispose();
                } else {
                    personList.addPerson(person);
                    this.dispose();
                }
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "ERROR: In Age, seniority and salary must be number!", null, JOptionPane.ERROR_MESSAGE);
            }catch (NullPointerException ex1){
                JOptionPane.showMessageDialog(null, "ERROR: Textarea can not be empty", null, JOptionPane.ERROR_MESSAGE);
            }catch (IndexOutOfBoundsException ex2){
                JOptionPane.showMessageDialog(null, "ERROR: Salary is not in salary brackets", null, JOptionPane.ERROR_MESSAGE);
            }


            view.refreshTextArea();
        });

    }

    public void initialize(){
        //create label to textfileds
        Label nameLabel = new Label("Name");
        Label surNameLabel = new Label("Surname");
        Label positionLabel = new Label("Position");
        Label ageLabel = new Label("Age");
        Label senitaryLabel = new Label("Senitary");
        Label salaryLabel = new Label("Salary");

        comboBox = new JComboBox(PositionType.values());
        addFields = new JTextField[5];
        for (int i = 0; i < addFields.length; ++i) {
            addFields[i] = new JTextField(10);
        }

        sumbit = new JButton("Submit");

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(surNameLabel)
                                        .addComponent(positionLabel)
                                        .addComponent(ageLabel)
                                        .addComponent(senitaryLabel)
                                        .addComponent(salaryLabel)
                                        .addComponent(sumbit))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(addFields[0])
                                        .addComponent(addFields[1])
                                        .addComponent(comboBox)
                                        .addComponent(addFields[2])
                                        .addComponent(addFields[3])
                                        .addComponent(addFields[4]))))
        );
        layout.linkSize(SwingConstants.HORIZONTAL, nameLabel, surNameLabel, positionLabel, ageLabel, senitaryLabel, salaryLabel);
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(addFields[0]))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(surNameLabel)
                        .addComponent(addFields[1]))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(positionLabel)
                        .addComponent(comboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(ageLabel)
                        .addComponent(addFields[2]))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(senitaryLabel)
                        .addComponent(addFields[3]))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(salaryLabel)
                        .addComponent(addFields[4]))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(sumbit))
        );
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public PersonList getPersonList() {
        return personList;
    }
}



/*
  enum PositionType {

        DEVELOPER("Developer"),
        ACCOUNTANT("Accountant"),
        LAWYER("Lawyer"),
        JANITOR("Janitor"),
        TRADER("Trader");


        private String message;
        private  PositionType(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

 public AddPersonView(PersonList personList, int index){
        this.personList = personList;
        JPanel menu = new JPanel();

        JComboBox comboBox = new JComboBox(PositionType.values());

        JTextField[] addFields = new JTextField[5];
        for (int i = 0; i < addFields.length; ++i) {
            addFields[i] = new JTextField(10);

        }
        //add text and combobox
        menu.add(addFields[0]);
        menu.add(addFields[1]);
        menu.add(comboBox);
        menu.add(addFields[2]);
        menu.add(addFields[3]);
        menu.add(addFields[4]);

        JButton sumbit = new JButton("Submit");
        menu.add(sumbit);

        JTextField textFieldEnum = new JTextField(10);


        comboBox.addActionListener(e -> {
            PositionType item = (PositionType)comboBox.getSelectedItem();
            textFieldEnum.setText(item + "");
        });

        sumbit.addActionListener(e->{
            Person person = new Person(addFields[0].getText(), addFields[1].getText(), textFieldEnum.getText(),Integer.parseInt(addFields[2].getText()), Integer.parseInt(addFields[3].getText()) ,Integer.parseInt(addFields[4].getText()));
            personList.editPerson(index,person);
            this.dispose();
        });

        this.setTitle("Adding Perosn");
        this.setSize(600, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(menu);
        this.setVisible(true);
    }
 */