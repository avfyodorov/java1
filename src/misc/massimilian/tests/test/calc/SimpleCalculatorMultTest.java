package misc.massimilian.tests.test.calc;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import misc.massimilian.tests.calc.SimpleCalculator;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SimpleCalculatorMultTest {
    SimpleCalculator sc = new SimpleCalculator();
    public int val1;
    public int val2;
    public int expected;

    public SimpleCalculatorMultTest(int val1, int val2, int expected) {
        this.val1 = val1;
        this.val2 = val2;
        this.expected = expected;
    }


    @Parameterized.Parameters(name = "Test №{index}: {0} * {1} = {2}")
    public static Iterable<Object[]> forTest() {
        return Arrays.asList(new Object[][]{
                {4, 2, 8},
                {-1, 1, -1},
                {200, -200, -40000},
                {-11, 1, -11},
                {20, 12, 240}
        });
    }

    @Test
    public void whenTryToMultDifferentNumbersThenDoIt() {
        Assert.assertEquals(sc.mult(val1, val2), expected);
    }

    @Test (expected = ArithmeticException.class)
    public void whenTryToMakeAnExceptionThenDoIt() {
        sc.mult(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
