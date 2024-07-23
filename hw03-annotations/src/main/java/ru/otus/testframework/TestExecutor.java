package ru.otus.testframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.testframework.helper.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestExecutor {
    private static final Logger logger = LoggerFactory.getLogger(TestExecutor.class);
    private static int failsCounter = 0;

    public TestExecutor() {
    }

    static void executeTest(TestClassContext testClassContext) throws InvocationTargetException, IllegalAccessException {
        for (Method method : testClassContext.getTestMethods()) {
            var testObject = ReflectionHelper.instantiate(testClassContext.getClazz());
            checkQuantity(testClassContext);
            if (!testClassContext.getBeforeMethods().isEmpty()) {
                testClassContext.getBeforeMethods().get(0).invoke(testObject);
                logger.info("Before {} is done", method.getName());
            }
            try {
                method.invoke(testObject);
                logger.info("Test {} is done", method.getName());
            } catch (Exception e) {
                logger.info("Test {} not passed", method.getName());
                failsCounter++;
            }
            finally {
                testClassContext.getAfterMethods().get(0).invoke(testObject);
                logger.info("After {} is done", method.getName());
            }
        }
        printStatistic(testClassContext);
    }

    private static void printStatistic(TestClassContext testClassContext) {
        System.out.println("Test  results");
        System.out.println("Total test completed: " + testClassContext.getTestMethods().size());
        System.out.println("Successful: " + (testClassContext.getTestMethods().size() - failsCounter));
        System.out.println("Failed: " + failsCounter);

    }

    private static void checkQuantity(TestClassContext testClassContext) {
        if (testClassContext.getBeforeMethods().size() > 1) {
            throw new IllegalArgumentException("There can be no more than one @Before method in the test");
        }
        if (testClassContext.getAfterMethods().size() > 1) {
            throw new IllegalArgumentException("There can be no more than one @After method in the test");
        }
    }
}
