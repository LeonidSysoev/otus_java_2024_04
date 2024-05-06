package org.example;


//import com.google.common.collect.Lists;

import com.google.common.collect.Lists;



import java.util.List;

public class HelloOtus {
    public void guavaExample() {
        List<Integer> list = Lists.newArrayList(10);
        for (int i = 0; i < list.size(); i++) {
            list.add(i);
        }



    }

}
