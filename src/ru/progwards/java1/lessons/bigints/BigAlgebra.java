package ru.progwards.java1.lessons.bigints;


import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra
{
//Реализовать алгоритм быстрого возведения в степень pow числа num в BigDecimal,
// описание алгоритма можно прочитать например в Википедии
public static BigDecimal fastPow(BigDecimal num, int pow)
{
  if (pow == 0)
    return BigDecimal.ONE;

  if (pow % 2 == 1)
    return fastPow(num, pow-1).multiply(num);
  else
  {
    BigDecimal b = fastPow(num, pow/2);
    return b.multiply(b);
  }
}

// Реализовать алгоритм вычисления n-го числа фибоначчи в BigInteger.
// Последовательность чисел Фибоначчи, это когда каждое последующее число равно
// сумме двух предыдущих чисел. Первые 2 числа последовательности 1, 1.
// Итого получаем 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 и т.д.
  public static BigInteger fibonacci(int n)
  {
    if (n < 3)
      return BigInteger.ONE;

    return fibonacci(n - 2).add(fibonacci(n-1));
  }

  public static void main(String[] args)
  {
    String s="big=";
    for (int i = 0; i <15 ; i++)
      s = s + fibonacci(i).toString() + ", ";
    System.out.println(s);

    System.out.println("big= "+fastPow(new BigDecimal(21),13));

  }
}
