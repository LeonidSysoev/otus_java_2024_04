package ru.otus.testframework.test;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;
import ru.otus.testframework.calculator.CalcClass;

import static org.assertj.core.api.Assertions.*;

public class CalcClassTest {
    private CalcClass calcClass;

    @Before
    public void setUp() {
        calcClass = new CalcClass();
    }

    @Test
    public void addTest() {
        int actual = calcClass.add(5, 10);
        assertThat(actual).isEqualTo(15);
    }

    @Test
    public void diffTest() {
        int actual = calcClass.diff(100, 50);
        assertThat(actual).isEqualTo(40);
    }

    @Test
    public void multiplyTest() {
        int actual = calcClass.multiply(5, 5);
        assertThat(actual).isEqualTo(25);
    }

    @Test
    public void divideTest() {
        assertThatThrownBy(() -> {
            int actual = calcClass.divide(10, 0);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @After
    public void clean() {
        calcClass.clean();
    }
}
