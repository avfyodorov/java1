package misc.massimilian.tests.test.calc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import misc.massimilian.tests.calc.SimpleCalculator;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SimpleCalculatorDiffTest {

    SimpleCalculator sc = new SimpleCalculator();
    public int one;
    public int two;
    public int res;

    public SimpleCalculatorDiffTest(int one, int two, int res) {
        this.one = one;
        this.two = two;
        this.res = res;
    }

    @Parameterized.Parameters(name = "Test №{index}: {0} - {1} = {2}")
    public static Iterable<Object[]> forTest() {
        return Arrays.asList(new Object[][]{
                {4, 2, 2},
                {-1, 1, -2},
                {200, -200, 400},
                {-11, 1, -12},
                {20, 1, 19}
        });
    }


    @Test
    public void whenTryToDiffDifferentNumbersThenDoIt() {
        Assert.assertEquals(sc.diff(one, two), res);
    }

    @Test (expected = ArithmeticException.class)
    public void whenTryToMakeAnExceptionThenDoIt() {
        sc.diff(Integer.MIN_VALUE, 1);
    }
}
