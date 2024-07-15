package ru.otus.testframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.testframework.helper.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestExecutor {
    private static final Logger logger = LoggerFactory.getLogger(TestExecutor.class);

    public TestExecutor() {
    }

    static void executeTest(TestClassContext testClassContext) throws InvocationTargetException, IllegalAccessException {
        for (Method method : testClassContext.testMethods) {
            var testObject = ReflectionHelper.instantiate(testClassContext.clazz);
            checkQuantity(testClassContext);
            if (!testClassContext.beforeMethods.isEmpty()) {
                testClassContext.beforeMethods.get(0).invoke(testObject);
                logger.info("Before {} is done", method.getName());
            }
            try {
                method.invoke(testObject);
                logger.info("Test {} is done", method.getName());
            } catch (Exception e) {
                logger.info("Test {} not passed", method.getName());
                testClassContext.failsCounter++;
            }
            if (!testClassContext.afterMethods.isEmpty()) {
                testClassContext.afterMethods.get(0).invoke(testObject);
                logger.info("After {} is done", method.getName());
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

    private static void checkQuantity(TestClassContext testClassContext) {
        if (testClassContext.beforeMethods.size() > 1) {
            throw new IllegalArgumentException("There can be no more than one @Before method in the test");
        }
        if (testClassContext.afterMethods.size() > 1) {
            throw new IllegalArgumentException("There can be no more than one @After method in the test");
        }
    }
}
