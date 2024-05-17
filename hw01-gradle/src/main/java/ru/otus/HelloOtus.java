package ru.otus;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        int number = 10;
        for (int i = 0; i < number; i++) {
            integerList.add(i);
        }
        List<Integer> list = Lists.newCopyOnWriteArrayList(integerList);
        System.out.println(list);

    }
}
