package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//Кеш для чисел Фибоначчи на Map. Кеш имитирует таковой на сервере,
// когда идут запросы со случайными параметрами от разных пользователей.
// Сам алгоритм чисел Фибоначчи - это просто пример некоего алгоритма,
// который долго работает, в сравнении с вытаскиванием значения из кэша.
// Считается, что кеш ничего не знает об алгоритме, и умеет только сохранять и
// доставать значения по ключу.
//
//
//        1.1 Определить приватную локальную переменную fiboCache как Map<Integer, BigDecimal>
//
//1.2 Определить конструктор public FiboMapCache(boolean cacheOn) - включен ли кэш.
// При cacheOn = true кэш работает, при cacheOn = false - выключен
//
//        1.3 Реализовать public BigDecimal fiboNumber(int n). Алгоритм работы следующий:
//
//        в функции проверить, находится ли вычисленное значение для n в кэше, и если да -
//        вернуть его из кэша, если нет - рассчитать и добавить в кэш. Учитывать значение
//        переменной cacheOn
//
//        1.4 Реализовать метод public void clearCahe() который устанавливает переменную
//        fiboCache в null
//
//        1.5 Для проверки работы реализовать public static void test()
//        - тест для расчета чисел Фибоначчи от n = 1 до 1000 включительно и замерить разницу
//        во времени с on = true и on = false, результат вывести на экран в
//        формате "fiboNumber cacheOn=??? время выполнения ???" для cacheOn=true и cacheOn=false,
//        вместо ??? вывести реальные значения в мсек.
public class FiboMapCache
{
  private Map<Integer, BigDecimal> fiboCache = new TreeMap<>();
  private boolean cacheOn;

  public FiboMapCache(boolean cacheOn)
  {
    this.cacheOn = cacheOn;
    if (cacheOn)
    {
      fiboCache.put(1, BigDecimal.ONE);
      fiboCache.put(2, BigDecimal.ONE);
    }
  }

  // сумме двух предыдущих чисел. Первые 2 числа последовательности 1, 1.
// Итого получаем 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 и т.д.
  public BigDecimal fiboNumber(int n)
  {
    if (n < 3) return BigDecimal.ONE;

    BigDecimal a, b, c;
    int start;

//если включено - взять ближайшее рассчитанное число
    if (cacheOn)
    {
      start = (int) ((TreeMap) fiboCache).floorKey(n) + 1;
      a = fiboCache.get(start - 2);
      b = fiboCache.get(start - 1);
    } else
    {
      start = 3;
      a = BigDecimal.ONE;
      b = BigDecimal.ONE;
    }

// Итого получаем 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 и т.д.
    for (int i = start; i <= n; i++)
    {
//calc next fibo number
      c = b.add(a);
      a = b;
      b = c;

      if (cacheOn)
        fiboCache.put(i, b);

    }

    return b;

  }

  static final int N_SIZE = 1000;

  //        во времени с on = true и on = false, результат вывести на экран в
//        формате "fiboNumber cacheOn=??? время выполнения ???" для cacheOn=true и cacheOn=false,
//        вместо ??? вывести реальные значения в мсек.
  public static void test()
  {
    FiboMapCache fiboMapCache = new FiboMapCache(true);
    long start = new Date().getTime();

    for (int i = 1; i <= N_SIZE; i++)
    {
      fiboMapCache.fiboNumber(i);
    }
    System.out.println("fiboNumber cacheOn=" + fiboMapCache.cacheOn +
            " время выполнения " + (int) (new Date().getTime() - start));

    fiboMapCache = new FiboMapCache(false);
    start = new Date().getTime();

    for (int i = 1; i <= N_SIZE; i++)
    {
      fiboMapCache.fiboNumber(i);
    }
    System.out.println("fiboNumber cacheOn=" + fiboMapCache.cacheOn +
            " время выполнения " + (int) (new Date().getTime() - start));

  }

  public void clearCahe()
  {
    fiboCache = null;
    cacheOn=false;
  }

  public static void main(String[] args)
  {
    test();
  }

}
