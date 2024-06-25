package ru.otus;

public class CalcClass {
    private int num1;
    private int num2;

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int diff(int num1, int num2) {
        return num1 - num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public int divide(int num1, int num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("the number must not be zero");
        }
        return num1 / num2;
    }
    public void clean() {
        this.num1 = 0;
        this.num2 = 0;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
}
