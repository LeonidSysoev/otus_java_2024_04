package ru.otus;

import ru.otus.testframework.TestClassContext;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        TestClassContext.runTest("ru.otus.testframework.test.CalcClassTest");
    }
}