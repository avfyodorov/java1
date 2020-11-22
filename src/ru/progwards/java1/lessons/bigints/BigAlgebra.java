package ru.progwards.java1.lessons.bigints;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class BigAlgebra
{
  //Реализовать алгоритм быстрого возведения в степень pow числа num в BigDecimal,
// описание алгоритма можно прочитать например в Википедии
  public static BigDecimal fastPow0(BigDecimal num, int pow)
  {
    if (pow == 0)
      return BigDecimal.ONE;

    if (pow % 2 == 1)
      return fastPow0(num, pow - 1).multiply(num);
    else
    {
      BigDecimal b = fastPow0(num, pow / 2);
      return b.multiply(b);
    }
  }

  public static BigDecimal fastPow(BigDecimal num, int pow)
  {
    BigDecimal res=BigDecimal.ONE;
    BigDecimal a=num;

    while (pow>0)
    {
      if ((pow & 1)>0)
        res=res.multiply(a);
      a=a.multiply(a);
      pow=pow>>1;
    }
    return res;
  }
  static BigDecimal fastPow2(BigDecimal num, int pow) {
    String temp = Integer.toBinaryString(pow);
    int result = Integer.parseInt(temp);
    int low;
    BigDecimal res = BigDecimal.ONE;
    for (int i = temp.length() - 1; i >= 0; i--) {
      low = (result >> i) & 1;
      if (i !=0) {
        res = res.multiply(num.pow(low));
        res = res.pow(2);
      }
      if (i == 0) {
        res = res.multiply(num.pow(low));
      }
    }
    return new BigDecimal(String.valueOf(res));
  }
  // Реализовать алгоритм вычисления n-го числа фибоначчи в BigInteger.
// Последовательность чисел Фибоначчи, это когда каждое последующее число равно
// сумме двух предыдущих чисел. Первые 2 числа последовательности 1, 1.
// Итого получаем 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 и т.д.
  public static BigInteger fibonacci(int n)
  {
    if (n < 3)
      return BigInteger.ONE;

    return fibonacci(n - 2).add(fibonacci(n - 1));
  }

  public static void main(String[] args)
  {
    //fibo=12586269025, 167652
    long start = new Date().getTime();
    System.out.println("fibo=" + fibonacci(30).toString() + ", "+ (new Date().getTime() - start));

    String s = "fibo=";
    for (int i = 1; i <= 30; i++)
      s = s + fibonacci(i).toString() + ", ";
    System.out.println(s);

//    long start = new Date().getTime();
//    BigDecimal b1 = new BigDecimal(2);
//    b1 = b1.pow(1000000);
//    System.out.println("standart=  " + (new Date().getTime() - start) + " ms. res: " + b1);
//
    start = new Date().getTime();
    BigDecimal b2 = fastPow(new BigDecimal(2), 1000000);
    System.out.println("fastpow = " + (new Date().getTime() - start) + " ms. res: " + b2);

    start = new Date().getTime();
    BigDecimal b3 = fastPow2(new BigDecimal(2), 1000000);
    System.out.println("fastpow0= " + (new Date().getTime() - start) + " ms. res: " + b3);


  }
}
