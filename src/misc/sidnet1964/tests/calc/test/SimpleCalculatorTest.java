package misc.sidnet1964.tests.calc.test;
/*
import org.junit.jupiter.api.AfterAll;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.params.ParameterizedTest;
        import org.junit.jupiter.params.provider.CsvFileSource;
        import org.junit.jupiter.params.provider.CsvSource;
        import static org.junit.jupiter.api.Assertions.*;
*/
public class SimpleCalculatorTest {
 /*
   static SimpleCalculator calc;
   @BeforeAll
   public static void init(){
      calc = new SimpleCalculator();
      System.out.println("public static void init()");
   }
   //  1.2 Создать параметризованные тесты для этих методов,
   @ParameterizedTest
   @CsvSource({
           "1, 1, 2",
           "2, 2, 4",
           "0, 0, 0"
   })
   void testWithCsvSource(int a, int b, int c) {
      assertEquals(c, calc.sum(a, b));
   }
   @ParameterizedTest
   @CsvFileSource(resources = "/sum-column.csv", numLinesToSkip = 0)
   void testWithCsvFileSource(int a, int b, int c) {
      assertEquals(c, calc.sum(a, b));
   }

   //  1.3 Написать для всех методов тесты для проверки соответствующих исключений
   @Test
   void exceptionTesting() {
      Exception exception1 = assertThrows(ArithmeticException.class, () ->
              calc.sum(Integer.MAX_VALUE, 1));
      assertEquals("Переполнение размера Integer", exception1.getMessage());
      Exception exception2 = assertThrows(ArithmeticException.class, () ->
              calc.diff(Integer.MIN_VALUE, 1));
      assertEquals("Переполнение размера Integer", exception2.getMessage());
      Exception exception3 = assertThrows(ArithmeticException.class, () ->
              calc.mult(Integer.MAX_VALUE, 2));
      assertEquals("Переполнение размера Integer", exception3.getMessage());
      Exception exception4 = assertThrows(ArithmeticException.class, () ->
              calc.div(0, 0));
      assertEquals("Деление на ноль", exception4.getMessage());
   }
   @AfterAll
   public static void destroy(){
      calc = null;
   }
*/
}

