package ru.otus;

import ru.otus.testframework.TestFramework;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        TestFramework.runTest("ru.otus.testframework.CalcClassTest");
    }
}