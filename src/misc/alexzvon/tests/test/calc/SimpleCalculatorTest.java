package misc.alexzvon.tests.test.calc;

import misc.alexzvon.tests.calc.SimpleCalculator;

import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {

   @RunWith(Parameterized.class)
   public static class SumSimpleCalculatorTest {
      public static SimpleCalculator simpleCalculator;

      public int val1;
      public int val2;
      public int expected;

      @BeforeClass
      public static void init() {
         simpleCalculator = new SimpleCalculator();
      }

      @AfterClass
      public static void destroy() {
         simpleCalculator = null;
      }

      public SumSimpleCalculatorTest(int val1, int val2, int expected) {
         this.val1 = val1;
         this.val2 = val2;
         this.expected = expected;
      }

      @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
      public static Iterable<Object[]> dataForTest() {
         return Arrays.asList(new Object[][]{
                 {0, 0, 0},
                 {5, 0, 5},
                 {-5, -5, -10},
                 {34, 55, 89},
                 {-34, -55, -89},
         });
      }

      @Test
      public void testWithParams() {
         assertEquals(expected, simpleCalculator.sum(val1, val2));
      }
   }

   @RunWith(Parameterized.class)
   public static class DiffSimpleCalculatorTest {
      public static SimpleCalculator simpleCalculator;

      public int val1;
      public int val2;
      public int expected;

      @BeforeClass
      public static void init() {
         simpleCalculator = new SimpleCalculator();
      }

      @AfterClass
      public static void destroy() {
         simpleCalculator = null;
      }

      public DiffSimpleCalculatorTest(int val1, int val2, int expected) {
         this.val1 = val1;
         this.val2 = val2;
         this.expected = expected;
      }

      @Parameterized.Parameters(name = "Тест {index}: ({0}) - ({1}) = {2}")
      public static Iterable<Object[]> dataForTest() {
         return Arrays.asList(new Object[][]{
                 {121, 11, 110},
                 {-15, 0, -15},
                 {-5, -5, 0},
                 {34, 55, -21},
                 {-34, -55, 21},
         });
      }

      @Test
      public void testWithParams() {
         assertEquals(expected, simpleCalculator.diff(val1, val2));
      }
   }

   @RunWith(Parameterized.class)
   public static class DivSimpleCalculatorTest {
      public static SimpleCalculator simpleCalculator;

      public int val1;
      public int val2;
      public int expected;

      @BeforeClass
      public static void init() {
         simpleCalculator = new SimpleCalculator();
      }

      @AfterClass
      public static void destroy() {
         simpleCalculator = null;
      }

      public DivSimpleCalculatorTest(int val1, int val2, int expected) {
         this.val1 = val1;
         this.val2 = val2;
         this.expected = expected;
      }

      @Parameterized.Parameters(name = "Тест {index}: ({0}) / ({1}) = {2}")
      public static Iterable<Object[]> dataForTest() {
         return Arrays.asList(new Object[][]{
                 {121, 11, 11},
                 {150, 3, 50},
                 {-5, -5, 1},
                 {321, 107, 3},
                 {-100, 5, -20},
         });
      }

      @Test
      public void testWithParams() {
         assertEquals(expected, simpleCalculator.div(val1, val2));
      }
   }

   @RunWith(Parameterized.class)
   public static class MultSimpleCalculatorTest {
      public static SimpleCalculator simpleCalculator;

      public int val1;
      public int val2;
      public int expected;

      @BeforeClass
      public static void init() {
         simpleCalculator = new SimpleCalculator();
      }

      @AfterClass
      public static void destroy() {
         simpleCalculator = null;
      }

      public MultSimpleCalculatorTest(int val1, int val2, int expected) {
         this.val1 = val1;
         this.val2 = val2;
         this.expected = expected;
      }

      @Parameterized.Parameters(name = "Тест {index}: ({0}) * ({1}) = {2}")
      public static Iterable<Object[]> dataForTest() {
         return Arrays.asList(new Object[][]{
                 {0, 0, 0},
                 {5, 0, 0},
                 {-5, -5, 25},
                 {11, 11, 121},
                 {-11, 11, -121},
         });
      }

      @Test
      public void testWithParams() {
         assertEquals(expected, simpleCalculator.mult(val1, val2));
      }
   }
}