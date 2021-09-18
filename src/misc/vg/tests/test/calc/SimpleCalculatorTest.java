package misc.vg.tests.test.calc;

import misc.vg.tests.calc.SimpleCalculator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.experimental.runners.Enclosed;
import static org.junit.Assert.*;

import java.util.Arrays;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {
    static SimpleCalculator calc;

    @BeforeClass
    public static void init() {
        calc = new SimpleCalculator();
    }

    @RunWith(Parameterized.class)
    public static class SumSimpleCalculatorTest {
        public int val1;
        public int val2;
        public int expected;

        public SumSimpleCalculatorTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 1, 1},
                    {-100, 100, 0},
                    {Integer.MAX_VALUE, 0, Integer.MAX_VALUE}
            });
        }

        @Test
        public void sumWithParams() {
            assertEquals(expected, calc.sum(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class DiffSimpleCalculatorTest {
        public int val1;
        public int val2;
        public int expected;

        public DiffSimpleCalculatorTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) - ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 1, -1},
                    {100, 100, 0},
                    {Integer.MIN_VALUE, 0, Integer.MIN_VALUE}
            });
        }

        @Test
        public void diffWithParams() {
            assertEquals(expected, calc.diff(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class MultSimpleCalculatorTest {
        public int val1;
        public int val2;
        public int expected;

        public MultSimpleCalculatorTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) * ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 1, 0},
                    {100, 100, 10_000},
                    {Integer.MIN_VALUE, 1, Integer.MIN_VALUE}
            });
        }

        @Test
        public void multWithParams() {
            assertEquals(expected, calc.mult(val1, val2));
        }
    }

    @RunWith(Parameterized.class)
    public static class DivSimpleCalculatorTest {
        public int val1;
        public int val2;
        public int expected;

        public DivSimpleCalculatorTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) / ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 1, 0},
                    {100, 100, 1},
                    {Integer.MIN_VALUE, 1, Integer.MIN_VALUE}
            });
        }

        @Test
        public void divWithParams() {
            assertEquals(expected, calc.div(val1, val2));
        }
    }

    // exceptions
    public static class SumExceptionsSimpleCalculatorTest {
        @Test(expected = ArithmeticException.class)
        public void sumWithExceptions() {
            calc.sum(Integer.MAX_VALUE, 1);
        }
    }

    public static class DiffExceptionsSimpleCalculatorTest {
        @Test(expected = ArithmeticException.class)
        public void diffWithExceptions() {
            calc.diff(Integer.MIN_VALUE, 1);
        }
    }

    public static class MultExceptionsSimpleCalculatorTest {
        @Test(expected = ArithmeticException.class)
        public void multWithExceptions() {
            calc.mult(Integer.MAX_VALUE, 1000);
        }
    }

    public static class DivExceptionsSimpleCalculatorTest {
        @Test(expected = ArithmeticException.class)
        public void divWithExceptions() {
            calc.div(1, 0);
            calc.div(Integer.MIN_VALUE, 1000);
        }
    }

    @AfterClass
    public static void destroy() {
        calc = null;
    }
}
