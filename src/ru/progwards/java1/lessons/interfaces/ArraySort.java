package ru.progwards.java1.lessons.interfaces;

//1 Реализовать метод, сортирующий произвольный массив целых чисел:
//public static void sort(int[] a), по следующему алгоритму:
//        1. Берем первый элемент и сравниваем его со вторым, если второй меньше,
//           меняем элементы в массиве местами.
//        2. Далее, сравниваем первый элемент с третьим, и если третий меньше, меняем их местами.
//        3. Так делаем для всех элементов с индексом больше первого
//        4. Берем второй элемент и сравниваем его с третьим, если нужно, меняем местам
//        5. Далее сравниваем второй элемент с четвертым, и если нужно, меняем местами.
//        6. Делаем так для всех элементов, с индексом больше 2-го
//        7. Переходим к элементу с индексом 3...
//        8. Обобщая, алгоритм звучит следующим образом - сделать 2 вложенных цикла,
//           внешний по i и внутренний по j. Внутренний цикл начинается от i+1, и если a[i] > a[j],
//           то нужно поменять элементы a[i] и a[j] местами.

import java.util.Arrays;

public class ArraySort
{
  public static void sort(CompareWeight[] a)
  {
    CompareWeight z;

    for (int i = 0; i < a.length; i++)
      for (int j = i + 1; j < a.length; j++)
      {
        Animal food = (Animal) a[i];
        if (food.compareWeight(a[j]) == CompareWeight.CompareResult.GREATER)
        {
          z = a[i];
          a[i] = a[j];
          a[j] = z;
        }
      }
  }

  public static void sort(int[] a)
  {
    int z;

    for (int i = 0; i < a.length; i++)
      for (int j = i + 1; j < a.length; j++)
        if (a[i] > a[j])
        {
          z = a[i];
          a[i] = a[j];
          a[j] = z;
        }
  }

  public static void main(String[] args)
  {
    int[] a1 = {12, 5, 0, 58, 36};

    System.out.println("\n int");

    System.out.println("Before :  " + Arrays.toString(a1));
    sort(a1);
    System.out.println("After  :  " + Arrays.toString(a1));

//=================


    Animal[] arr = {
            new Animal(59),
            new Animal(19),
            new Animal(60),
            new Animal(5),
            new Animal(8),
    };

    System.out.println("\n CompareWeight");

    System.out.println("Before : "+debugPrint(arr));
    sort(arr);
    System.out.println("After  : "+debugPrint(arr));

  }
private static   String debugPrint(Animal[] arr)
  {
    String res="";
    for (Animal a: arr)
    {
      res=res+a.getWeight()+", ";
    }
    return res;
  }
}
