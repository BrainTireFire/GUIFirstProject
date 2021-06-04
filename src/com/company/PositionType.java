package com.company;

public enum PositionType {

    DEVELOPER("Developer"){
        @Override
        boolean salaryBrackets(int salary) {
            if (salary >= 4000 && salary <= 20000){
                return true;
            }else
                return false;
        }
    },
    ACCOUNTANT("Accountant") {
        @Override
        boolean salaryBrackets(int salary) {
            if (salary >= 2500 && salary <= 8000){
                return true;
            }else
                return false;
        }
    },
    LAWYER("Lawyer") {
        @Override
        boolean salaryBrackets(int salary) {
            if (salary >= 3500 && salary <= 30000){
                return true;
            }else
                return false;
        }
    },
    JANITOR("Janitor") {
        @Override
        boolean salaryBrackets(int salary) {
            if (salary >= 1500 && salary <= 5000){
                return true;
            }else
                return false;
        }
    },
    TRADER("Trader") {
        @Override
        boolean salaryBrackets(int salary) {
            if (salary >= 1000 && salary <= 80000){
                return true;
            }else
                return false;
        }
    };

    private String message;

    private  PositionType(String message) {
        this.message = message;
    }

    abstract boolean salaryBrackets(int salary);

    @Override
    public String toString() {
        return message;
    }
}