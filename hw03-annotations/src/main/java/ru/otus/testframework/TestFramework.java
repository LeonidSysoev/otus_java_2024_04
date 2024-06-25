package ru.otus.testframework;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;
import ru.otus.testframework.helper.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestFramework {
    private final List<Method> beforeMethods;
    private final List<Method> testMethods;
    private final List<Method> afterMethods;
    private Class<?> clazz;
    private int failsCounter = 0;

    public TestFramework(Class clazz) {
        this.beforeMethods = new ArrayList<>();
        this.testMethods = new ArrayList<>();
        this.afterMethods = new ArrayList<>();
        this.clazz = clazz;
    }

    public static void runTest(String className) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        TestFramework testFramework = new TestFramework(Class.forName(className));
        getClassWithAnnotations(testFramework);
        executeTest(testFramework);
    }


    private static void getClassWithAnnotations(TestFramework testFramework) {
        for (Method method : testFramework.clazz.getMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                testFramework.beforeMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testFramework.testMethods.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                testFramework.afterMethods.add(method);
            }
        }
    }

    private static void executeTest(TestFramework testFramework) throws InvocationTargetException, IllegalAccessException {
        for (Method method: testFramework.testMethods) {
            var testObject = ReflectionHelper.instantiate(testFramework.clazz);
            if (!testFramework.beforeMethods.isEmpty()) {
                testFramework.beforeMethods.get(0).invoke(testObject);
            }
            try {
                method.invoke(testObject);
            } catch (Exception e) {
                System.out.println("Тест" + method.getName() + "не пройден");
                testFramework.failsCounter++;
            }
            if (!testFramework.afterMethods.isEmpty()) {
                testFramework.afterMethods.get(0).invoke(testObject);
            }
        }
        printStatistic(testFramework);
    }

    private static void printStatistic(TestFramework testFramework) {
        System.out.println("Результаты тесты");
        System.out.println("Выполнено тестов всего: " + testFramework.testMethods.size());
        System.out.println("Успешно: " + (testFramework.testMethods.size() - testFramework.failsCounter));
        System.out.println("Провалено: " + testFramework.failsCounter);

    }


}
