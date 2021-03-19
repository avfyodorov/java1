package misc.alexzvon.tests.test.calc;


import misc.alexzvon.tests.calc.SimpleCalculator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class CheckExceptionTest {
   public static misc.alexzvon.tests.calc.SimpleCalculator simpleCalculator;

   @Rule
   public ExpectedException exception = ExpectedException.none();

   @BeforeClass
   public static void init() {
      simpleCalculator = new SimpleCalculator();
   }

   @Test
   public void SumWithExcpection() {
      exception.expect(ArithmeticException.class);
      exception.expectMessage("Переполнение на операции sum");

      simpleCalculator.sum(Integer.MAX_VALUE, 1);
   }

   @Test
   public void diffWithExcpection() {
      exception.expect(ArithmeticException.class);
      exception.expectMessage("Переполнение на операции diff");

      simpleCalculator.diff(Integer.MAX_VALUE, -1);
   }

   @Test
   public void multWithExcpection() {
      exception.expect(ArithmeticException.class);
      exception.expectMessage("Переполнение на операции mult");

      simpleCalculator.mult(Integer.MAX_VALUE, 2);
   }

   @Test
   public void DivWithExcpection() {
      exception.expect(NullPointerException.class);
      exception.expectMessage("Деление на ноль");

      simpleCalculator.div(Integer.MAX_VALUE, 0);
   }

   @AfterClass
   public static void destroy() {
      simpleCalculator = null;
   }
}