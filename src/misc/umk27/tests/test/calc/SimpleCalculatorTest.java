package misc.umk27.tests.test.calc;

import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import misc.umk27.tests.calc.SimpleCalculator;


import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {

    static SimpleCalculator scalc;

    @BeforeClass
    public static void init() {
        scalc = new SimpleCalculator();
    }

    @RunWith(Parameterized.class)
    public static class SumSimpleCalculatorTest {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int val3;

        @Parameterized.Parameters(name = "Тест {index}: {0} + {1} = {2}")
        public static Iterable<Object> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 0, 0,},
                    {5, 0, 5},
                    {-5, -5, -10},
                    {50, 50, 100}
            });
        }

        @Test
        public void sum() {
            assertEquals(val3, scalc.sum(val1, val2));

        }

    }

    @RunWith(Parameterized.class)
    public static class DiffSimpleCalculatorTest {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int val3;

        @Parameterized.Parameters(name = "Тест {index}: {0} - {1} = {2}")
        public static Iterable<Object> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 0, 0,},
                    {5, 5, 0},
                    {-5, -5, 0},
                    {30, 8, 22}
            });
        }

        @Test
        public void diff() {
            assertEquals(val3, scalc.diff(val1, val2));

        }

    }

    @RunWith(Parameterized.class)
    public static class MultSimpleCalculatorTest {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int val3;

        @Parameterized.Parameters(name = "Тест {index}: {0} - {1} = {2}")
        public static Iterable<Object> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 0, 0,},
                    {5, 5, 25},
                    {-5, -10, 50},
                    {20, 30, 600}
            });
        }

        @Test
        public void mult() {
            assertEquals(val3, scalc.mult(val1, val2));

        }

    }

    @RunWith(Parameterized.class)
    public static class DivSimpleCalculatorTest {

        @Parameterized.Parameter(0)
        public int val1;
        @Parameterized.Parameter(1)
        public int val2;
        @Parameterized.Parameter(2)
        public int val3;

        @Parameterized.Parameters(name = "Тест {index}: {0} - {1} = {2}")
        public static Iterable<Object> dataForTest() {
            return Arrays.asList(new Object[][]{
                    {0, 1, 0,},
                    {5, 5, 1},
                    {64, 8, 8},
                    {25, 5, 5}
            });
        }

        @Test
        public void div() {
            assertEquals(val3, scalc.div(val1, val2));

        }

    }


    public static class CheckExceptionTest {
        @Test(expected = ArithmeticException.class)
        public void SumWithExeption() {
            assertEquals(Integer.MAX_VALUE + 1, scalc.sum(Integer.MAX_VALUE, 1));


        }

        @Test(expected = ArithmeticException.class)
        public void DiffWithExeption() {
            assertEquals(Integer.MIN_VALUE - 1, scalc.diff(Integer.MIN_VALUE, 1));

        }

        @Test(expected = ArithmeticException.class)
        public void MultWithExeption() {
            assertEquals(Integer.MAX_VALUE * 2, scalc.mult(Integer.MAX_VALUE, 2));

        }

        @Test(expected = NullPointerException.class)
        public void DivWithExeption() {
            assertEquals("??", scalc.div(5, 0));

        }

    }

    @AfterClass
    public static void destroy() {
        scalc = null;
    }
}
