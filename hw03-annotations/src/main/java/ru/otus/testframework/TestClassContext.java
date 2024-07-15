package ru.otus.testframework;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestClassContext {

    final List<Method> beforeMethods;
    final List<Method> testMethods;
    final List<Method> afterMethods;
    Class<?> clazz;
    int failsCounter = 0;

    public TestClassContext(Class<?> clazz) {
        this.beforeMethods = new ArrayList<>();
        this.testMethods = new ArrayList<>();
        this.afterMethods = new ArrayList<>();
        this.clazz = clazz;
    }

    static void fillTextContext(TestClassContext testClassContext) {
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


}
