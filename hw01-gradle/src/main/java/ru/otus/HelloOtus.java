package ru.otus;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloOtus {
    private static final Logger logger = LoggerFactory.getLogger(HelloOtus.class);

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        int number = 10;
        for (int i = 0; i < number; i++) {
            integerList.add(i);
        }
        List<Integer> list = Lists.newCopyOnWriteArrayList(integerList);
        logger.info("List:{}", list);
    }
}
