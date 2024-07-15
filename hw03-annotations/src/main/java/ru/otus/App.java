package ru.otus;

import ru.otus.testframework.TestRunner;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
       TestRunner.runTest("ru.otus.testframework.test.CalcClassTest");
    }
}