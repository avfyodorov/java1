package misc.massimilian.tests.test.calc;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import misc.massimilian.tests.calc.SimpleCalculator;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SimpleCalculatorSumTest {
    SimpleCalculator sc = new SimpleCalculator();
    public int val1;
    public int val2;
    public int expected;

    public SimpleCalculatorSumTest(int val1, int val2, int expected) {
        this.val1 = val1;
        this.val2 = val2;
        this.expected = expected;
    }


    @Parameterized.Parameters(name = "Test №{index}: {0} + {1} = {2}")
    public static Iterable<Object[]> forTest() {
        return Arrays.asList(new Object[][]{
                {1, 2, 3},
                {-1, 1, 0},
                {100, -200, -100},
                {-1, 11, 10},
                {1, 20, 21}
        });
    }

    @Test
    public void whenTryToAddDifferentNumbersThenDoIt() {
        Assert.assertEquals(sc.sum(val1, val2), expected);
    }

    @Test (expected = ArithmeticException.class)
    public void whenTryToMakeAnExceptionThenDoIt() {
        sc.sum(Integer.MAX_VALUE, 1);
    }
}
