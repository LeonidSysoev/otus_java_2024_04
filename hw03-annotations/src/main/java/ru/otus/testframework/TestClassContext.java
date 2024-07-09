package ru.otus.testframework;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;
import ru.otus.testframework.helper.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestClassContext {
    private final List<Method> beforeMethods;
    private final List<Method> testMethods;
    private final List<Method> afterMethods;
    private Class<?> clazz;
    private int failsCounter = 0;

    public TestClassContext(Class<?> clazz) {
        this.beforeMethods = new ArrayList<>();
        this.testMethods = new ArrayList<>();
        this.afterMethods = new ArrayList<>();
        this.clazz = clazz;
    }

    public static void runTest(String className) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        TestClassContext testFramework = new TestClassContext(Class.forName(className));
        getClassWithAnnotations(testFramework);
        executeTest(testFramework);
    }


    private static void getClassWithAnnotations(TestClassContext testClassContext) {
        for (Method method : testClassContext.clazz.getMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                testClassContext.beforeMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testClassContext.testMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                testClassContext.afterMethods.add(method);
            }
        }
    }

    private static void executeTest(TestClassContext testClassContext) throws InvocationTargetException, IllegalAccessException {
        for (Method method: testClassContext.testMethods) {
            var testObject = ReflectionHelper.instantiate(testClassContext.clazz);
            if (!testClassContext.beforeMethods.isEmpty()) {
                testClassContext.beforeMethods.get(0).invoke(testObject);
            }
            try {
                method.invoke(testObject);
            } catch (Exception e) {
                System.out.println("Test " + method.getName() + " not passed");
                testClassContext.failsCounter++;
            }
            if (!testClassContext.afterMethods.isEmpty()) {
                testClassContext.afterMethods.get(0).invoke(testObject);
                System.out.println("After " + method.getName() + " is done");
            }
        }
        printStatistic(testClassContext);
    }

    private static void printStatistic(TestClassContext testClassContext) {
        System.out.println("Test  results");
        System.out.println("Total test completed: " + testClassContext.testMethods.size());
        System.out.println("Successful: " + (testClassContext.testMethods.size() - testClassContext.failsCounter));
        System.out.println("Failed: " + testClassContext.failsCounter);

    }


}
