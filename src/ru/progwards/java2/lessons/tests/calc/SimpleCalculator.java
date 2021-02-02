package ru.progwards.java2.lessons.tests.calc;

/*
1.1 Реализовать класс SimpleCalculator с методами
        int sum(int val1, int val2);
        int diff(int val1, int val2);
        int mult(int val1, int val2);
        int div(int val1, int val2);
        класс поместить в package ru.progwards.java2.lessons.tests.calc

        1.2 Создать параметризованные тесты для этих методов,
        тесты поместить в package ru.progwards.java2.lessons.tests.test.calc.

        1.3 Написать для всех методов тесты для проверки соответствующих исключений,
        тесты поместить в package ru.progwards.java2.lessons.tests.test.calc.
*/
public class SimpleCalculator {
   private int verify(long val) {
      if (val > Integer.MAX_VALUE || val < Integer.MIN_VALUE)
         throw new ArithmeticException("Переполнение размера Integer");
      return (int) val;
   }

   public int sum(int a, int b) {
      return verify((long) a + b);
   }

   public int diff(int a, int b) {
      return verify((long) a - b);
   }

   public int mult(int a, int b) {
      return verify((long) a * b);
   }

   public int div(int a, int b) {
      if (b == 0)
         throw new ArithmeticException("Деление на ноль");
      return verify((long) a / b);
   }
}
