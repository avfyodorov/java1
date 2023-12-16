package ru.progwards.java2.lessons.generics;

import java.util.List;

//
//  1. Берем первый элемент и сравниваем его со вторым, если второй меньше, меняем элементы в массиве местами.
//  2. Далее, сравниваем первый элемент с третьим, и если третий меньше, меняем их местами.
//  3. Так делаем для всех элементов с индексом больше первого
//  4. Берем второй элемент и сравниваем его с третьим, если нужно, меняем местам
//  5. Далее сравниваем второй элемент с четвертым, и если нужно, меняем местами.
//  6. Делаем так для всех элементов, с индексом больше 2-го
//  7. Переходим к элементу с индексом 3...
//  8. Обобщая, алгоритм звучит следующим образом - сделать 2 вложенных цикла,
//  внешний по i и внутренний по j. Внутренний цикл начинается от i+1, и если a[i] > a[j],
//  то нужно поменять элементы a[i] и a[j] местами.
public class ArraySort
{
  //  Реализовать статический метод с именем sort, сортирующий произвольный массив обобщающих типов,
//  по алгоритму из первого урока:
  public static <T extends Comparable> void sort(T[] arr)
  {
    if (arr == null || arr.length == 0)
      return;

    for (int i = 0; i < arr.length - 1; i++)
    {
      for (int j = i + 1; j < arr.length; j++)
      {
        if (arr[i].compareTo(arr[j]) > 0)
        {
          T x = arr[i];
          arr[i] = arr[j];
          arr[j] = x;
        }
      }
    }
  }
  public static<T extends Comparable> void sort9(T[] arr) {

    for(int i = 0; i < arr.length; i++) {
      T tmp;
      for(int j = i + 1; j < arr.length; j++) {
        if(arr[i].compareTo(arr[j]) == 1) {
          tmp = arr[i];
          arr[i] = arr[j];
          arr[j] = tmp;
        }
      }
    }
  }
/*
  public static void main(String[] args) {
    Integer[] ints = {9, 2, 4, 1};
    Double[] doubles = {9.0, 2.0, 4.0, 1.0};
    sort(ints);
    sort(doubles);
    System.out.println(Arrays.toString(ints));
    System.out.println(Arrays.toString(doubles));
  }
*/
  public static void main(String[] args)
  {
    Integer[] i = {3, 2, 1};
    sort9(i);
    List.of(i)
        .forEach(System.out::println);
//------------
    String[] s = {"c", "b", "a"};
    sort9(s);
    List.of(s)
        .forEach(System.out::println);

  }
}
