package misc.alexzvon.tests.calc;

public class SimpleCalculator {
   public int sum(int val1, int val2) throws ArithmeticException {
      long r = (long)val1 + val2;
      if ( r > Integer.MAX_VALUE || r < Integer.MIN_VALUE )
         throw new ArithmeticException("Переполнение на операции sum");
      return (int)r;
   };

   public int diff(int val1, int val2) throws ArithmeticException {
      long r = (long)val1 - val2;
      if ( r > Integer.MAX_VALUE || r < Integer.MIN_VALUE )
         throw new ArithmeticException("Переполнение на операции diff");
      return (int)r;
   };

   public int mult(int val1, int val2) throws ArithmeticException {
      long r = (long)val1 * val2;
      if ( r > Integer.MAX_VALUE || r < Integer.MIN_VALUE )
         throw new ArithmeticException("Переполнение на операции mult");
      return (int)r;
   };

   public int div(int val1, int val2) throws NullPointerException {
      if (0 == val2) {
         throw new NullPointerException("Деление на ноль");
      }
      return val1 / val2;
   };
}
