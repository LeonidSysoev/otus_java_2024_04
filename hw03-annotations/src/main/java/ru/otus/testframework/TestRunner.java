package ru.otus.testframework;

import java.lang.reflect.InvocationTargetException;


public class TestRunner {
    public static void runTest(String className) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        TestClassContext testClassContext = new TestClassContext(Class.forName(className));
        TestClassContext.fillTextContext(testClassContext);
        TestExecutor.executeTest(testClassContext);
    }
}
