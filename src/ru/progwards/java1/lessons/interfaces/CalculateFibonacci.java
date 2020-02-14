package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci
{

  //проверять параметр n на совпадение с последним рассчитанным значением,
// и если совпадает - возвращать уже готовый результат.
// Если не совпадает - рассчитывать и сохранять в статической переменной lastFibo.
  public static int fiboNumber(int n)
//  будет возвращать n-ое число Фибоначчи (нумерация начинается с 1,
//  то есть при n = 3 должно вернуться число Фибоначчи 2, а при n = 10 число 55).
  {
    if (getLastFibo() == null)
      lastFibo = new CacheInfo();

    if (n == lastFibo.n)
      return lastFibo.fibo;

    lastFibo.n = n;

    if (n < 3)
    {
      lastFibo.fibo = 1;
      return 1;
    }

    int f0 = 1, f1 = 1, f2 = 0;

    for (int i = 3; i <= n; i++)
    {
      f2 = f1 + f0;
      f0 = f1;
      f1 = f2;
    }

    lastFibo.fibo = f2;
    return f2;
  }

  public static class CacheInfo
  {
    public int n;    // -число,  для которого  рассчитываем Фибоначчи
    public int fibo; // -  результат расчета
  }

  private static CacheInfo lastFibo;

  public static CacheInfo getLastFibo()
  {
    return lastFibo;
  }

  public static void clearLastFibo()
  {
    lastFibo = null;
  }

  public static void main(String[] args)
  {
    System.out.println(fiboNumber(9));
    System.out.println(fiboNumber(9));
    System.out.println(fiboNumber(11));
  }
}
