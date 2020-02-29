package ru.progwards.java1.lessons.collections;

//1 Реализовать метод
//public static Collection<Integer> fillEven(int n) -
// создать коллекцию и заполнить последовательностью четных возрастающих чисел начиная с 2,
// количество элементов в коллекции n
//
//        1.2 Реализовать метод
//public static Collection<Integer> fillOdd(int n) -
// создать коллекцию и заполнить последовательностью нечетных убывающих чисел,
// минимальное число в коллекции 1, количество элементов в коллекции n*3
//
//        1.3 Реализовать метод
//public static Collection<Integer> fill3(int n) -
// создать коллекцию и заполнить ее тройками чисел. Каждая тройка создается по алгоритму:
// первое число тройки - индекс числа в коллекции, второе - индекс в квадрате,
// третье - индекс в кубе, количество элементов в коллекции n*3

import java.util.ArrayList;
import java.util.Collection;

public class Creator
{
  // создать коллекцию и заполнить последовательностью четных возрастающих чисел начиная с 2,
// количество элементов в коллекции n
  public static Collection<Integer> fillEven(int n)
  {
    Collection<Integer> list = new ArrayList<>();

    for (int i = 0; i < n; i++)
      list.add(i * 2 + 2);

    return list;
  }

// создать коллекцию и заполнить последовательностью нечетных убывающих чисел,
// минимальное число в коллекции 1, количество элементов в коллекции n*3
  public static Collection<Integer> fillOdd(int n)
  {
    Collection<Integer> list = new ArrayList<>();

    for (int i = 0; i < n * 3; i++)
      list.add(n * 6 - i * 2 - 1);

    if (list.size() < 1)
      list.add(1);

    return list;
  }

// создать коллекцию и заполнить ее тройками чисел. Каждая тройка создается по алгоритму:
// первое число тройки - индекс числа в коллекции, второе - индекс в квадрате,
// третье - индекс в кубе, количество элементов в коллекции n*3
  public static Collection<Integer> fill3(int n)
  {
    Collection<Integer> list = new ArrayList<>();

    for (int i = 0; i < n; i++)
    {
      int x=list.size();
      list.add(x);
      list.add(x*x);
      list.add(x*x*x);
    }
    return list;

  }

  public static void main(String[] args)
  {
    System.out.println("чётные числа начиная с 2-х -- " + fillEven(10));
    System.out.println("нечётные убывающие n*3 -- " + fillOdd(4));
    System.out.println("индекс квадрат куб n*3 -- " + fill3(3));

  }
}
