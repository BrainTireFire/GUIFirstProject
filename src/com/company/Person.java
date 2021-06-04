package com.company;

public class Person implements Comparable<Person>{

    private String name, surname, position;
    private int age, seniority, salary;

    public Person(String name, String surname, String position,int age, int seniority, int salary) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.age = age;
        this.seniority = seniority;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Person p) {
        if(this.name.compareTo(p.getName()) != 0){
            return this.name.compareTo(p.getName());
        }else if (this.surname.compareTo(p.getSurname()) != 0){
            return this.surname.compareTo(p.getSurname());
        }else if (this.position.compareTo(p.getPosition()) != 0){
            return this.position.compareTo(p.getPosition());
        }else if(this.age > p.getAge()){
            return this.age - p.getAge();
        }else if(this.seniority > p.getSeniority()){
            return this.seniority - p.getSeniority();
        }else if(this.salary > p.getSalary()){
            return this.salary - p.getSalary();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "name " + name +
                " surname " + surname +
                " position " + position +
                " age " + age +
                " seniority " + seniority +
                " salary " + salary;
    }
}

