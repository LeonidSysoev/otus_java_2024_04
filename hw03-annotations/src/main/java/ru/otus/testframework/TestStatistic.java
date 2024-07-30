package ru.otus.testframework;

public class TestStatistic {
    static void printStatistic(TestClassContext testClassContext, int failsCounter) {
        System.out.println("Test  results");
        System.out.println("Total test completed: " + testClassContext.getTestMethods().size());
        System.out.println("Successful: " + (testClassContext.getTestMethods().size() - failsCounter));
        System.out.println("Failed: " + failsCounter);

    }
}
