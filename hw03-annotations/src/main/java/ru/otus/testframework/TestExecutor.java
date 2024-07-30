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

    static int executeTest(TestClassContext testClassContext) throws InvocationTargetException, IllegalAccessException {
        int failsCounter = 0;
        for (Method method : testClassContext.getTestMethods()) {
            var testObject = ReflectionHelper.instantiate(testClassContext.getClazz());
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
        return failsCounter;
    }



}
