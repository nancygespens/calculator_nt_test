package com.example.calculator.model;

public class CalculatorRequest {

    private String number_1;
    private String number_2;
    private String operation;

    public String getNumber_1() {
        return number_1;
    }

    public void setNumber1(String number1) {
        this.number_1 = number1;
    }

    public String getNumber_2() {
        return number_2;
    }

    public void setNumber_2(String number2) {
        this.number_2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
