package ru.progwards.java1.lessons.queues;

//Сравнение методов сортировки коллекций
//
//        1.1 Реализовать метод public static void mySort(Collection<Integer> data)
//        - переделать алгоритм из класса ArraySort из ДЗ про массивы, на коллекции.
//        Не использовать встроенные методы сортировок
//
//        1.2 Реализовать метод public static void minSort(Collection<Integer> data)
//        по следующему алгоритму
//        - создать новую коллекцию
//        - найти минимальный элемент с использованием функции min()
//        - переместить его в новую коллекцию
//        - делать так до тех пор, пока все элементы не окажутся в новой коллекции
//        - скопировать новую коллекцию в старую
//
//        1.3 Реализовать метод public static void collSort(Collection<Integer> data)
//        используя метод sort из Collections
//
//        1.4 Реализовать метод public static Collection<String> compareSort()
//        в котором сравнить производительность методов и вернуть их имена,
//        отсортированные в порядке производительности, первый - самый быстрый.
//        В случае равенства производительности каких-то методов,
//        возвращать их названия в алфавитном порядке.

import java.util.*;

public class CollectionsSort
{

  public static void mySort(Collection<Integer> data)
  {
    List<Integer> data1 = (List) data;
    for (int i = 0; i < data1.size(); i++)
      for (int j = i + 1; j < data1.size(); j++)
        if (data1.get(i) > data1.get(j))
          Collections.swap(data1, i, j);
  }

  public static void minSort(Collection<Integer> data)
//        по следующему алгоритму
//        - создать новую коллекцию
//        - найти минимальный элемент с использованием функции min()
//        - переместить его в новую коллекцию
//        - делать так до тех пор, пока все элементы не окажутся в новой коллекции
//        - скопировать новую коллекцию в старую
  {
    List<Integer> res = new ArrayList<>();
    while (data.size() > 0)
    {
      Integer i = Collections.min(data);
      res.add(i);
      data.remove(i);
    }
    data.addAll(res);
  }

  public static void collSort(Collection<Integer> data)
//        используя метод sort из Collections
  {
    Collections.sort((List) data);
  }

  static final int TEST_COUNT = 100_000;

  static ResSortTest testmain(String m_name)
  {
    List<Integer> data = new ArrayList<>();
    long start = new Date().getTime();

    for (int i = 0; i < TEST_COUNT; i++)
    {
      Collections.addAll(data, 12, 5, 0, 58, 36);

      if (m_name.equals("mySort"))
        mySort(data);
      else if (m_name.equals("minSort"))
        minSort(data);
      else
        collSort(data);

      data.clear();
    }
    System.out.println(m_name + " == " + (int) (new Date().getTime() - start));
    return new ResSortTest(m_name, (int) (new Date().getTime() - start));
  }

  public static Collection<String> compareSort()
//        в котором сравнить производительность методов и вернуть их имена,
//        отсортированные в порядке производительности, первый - самый быстрый.
//        В случае равенства производительности каких-то методов,
//        возвращать их названия в алфавитном порядке.
  {
//список для рез-в тестов
    Set<ResSortTest> tests = new TreeSet<>(new Comparator<ResSortTest>()
    {
      @Override
      public int compare(ResSortTest o1, ResSortTest o2)
      {
//сперва по скорости, потом по имени
        int x = Integer.compare(o1.getSpeed(), o2.getSpeed());
        return x == 0 ? (o1.getMethod_name().compareTo(o2.getMethod_name())) : x;
      }
    });

    tests.add(testmain("mySort"));
    tests.add(testmain("minSort"));
    tests.add(testmain("collSort"));
//--------------
//    Время вызова метода mySort составило: 3027 мсек.
//    Время вызова метода minSort составило: 1625 мсек.
//    Время вызова метода collSort составило: 401 мсек.
//    Возвращена коллекция, содержащая: minSort, collSort, mySort.
//    Ожидалось: collSort, minSort, mySort.
//    tests.add(new ResSortTest("mySort", 3027));
//    tests.add(new ResSortTest("minSort", 1625));
//    tests.add(new ResSortTest("collSort", 401));


//сформировать результирующий список строк
    List<String> res = new ArrayList<>();
    for (ResSortTest t : tests) res.add(t.toString());

    return res;
  }


  public static void main(String[] args)
  {
    List<Integer> data = new ArrayList<>();
    Collections.addAll(data, 12, 5, 0, 58, 36);
    System.out.println("Before :  " + data);
    mySort(data);
    System.out.println("MySort  :  " + data);
//-------------------------------
    data.clear();
    Collections.addAll(data, 12, 5, 0, 58, 36);
    minSort(data);
    System.out.println("minSort  :  " + data);
//-------------------------------
    data.clear();
    Collections.addAll(data, 12, 5, 0, 58, 36);
    collSort(data);
    System.out.println("collSort  :  " + data);
//-------------------------------

    System.out.println("test result: " + compareSort());
  }
}

class ResSortTest
{
  public String getMethod_name()
  {
    return method_name;
  }

  private String method_name;


  public int getSpeed()
  {
    return speed;
  }

  private int speed;

  @Override
  public String toString()
  {
    return method_name;
  }

  public ResSortTest(String method_name, int speed)
  {
    this.method_name = method_name;
    this.speed = speed;
  }

}
