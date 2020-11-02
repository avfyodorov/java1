package ru.progwards.java2.lessons.generics;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//public class GTest {
//  public  static <T extends Throwable> T getItem(List<T> list, int index) {
//    try {
//      return list.get(index);
////      java: unexpected type
////      required: class
////      found:    type parameter T
//    } catch (T e) {
//      return null;
//    }
//  }
//}

//public class GTest {
//  public  static <T extends Comparable> T getItem(List<? extends Comparable> list, int index) {
//    return list.get(index);
//  }
//}
//
////++++++++++++++++++++++++++++
//public class GTest<T>
//{
//  public void addAll(List<T> list, T... items)
//  {
//    for (T item : items)
//    {
//      list.add(item);
//    }
//  }
//
////  Создайте статический метод с именем compare, который содержит 2 параметра обобщающего типа,
////  и сравнивает их через метод compareTo. Метод compare должен возвращать CompareResult, причем
////
////  CompareResult.LESS если первый параметр меньше второго
////  CompareResult.GREATER если первый параметр больше второго
////  CompareResult.EQUAL если первый параметр равен второму
//
//
//  static <X extends Comparable> CompareResult compare(X x, X y)
//  {
//    if (x.compareTo(y) < 0) return CompareResult.LESS;
//    if (x.compareTo(y) > 0) return CompareResult.GREATER;
//    return CompareResult.EQUAL;
//  }
//}
//
//enum CompareResult
//{LESS, EQUAL, GREATER};

// class GTest<T> {
//  public static T get(List<T> list, int index) {
//    return list.get(index);
//  }
//}
// class GTest<T>
//{
//  public static <T extends Comparable<T>> GTest<T> from(List<T> list)
//  {
//    GTest<T> result = new GTest<>();
//    //.. do something
//    return result;
//  }
//
//}

class GTest
{
  //  здайте статический метод с именем from, который принимает параметром массив,
//  обобщающего типа, создает новый ArrayList, копирует в него содержимое массива и
//  возвращает ArrayList в качестве результата метода.
  public static <T> ArrayList<T> from(T[] arr)
  {
    ArrayList<T> res = new ArrayList<>();
    for (int i = 0; i < arr.length; i++)
    {
      res.add(arr[i]);
    }
//    Collections.addAll(res, arr);

    return res;
  }

}