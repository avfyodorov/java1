package ru.progwards.java1.lessons.collections;


//public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) -
// найти 2 соседних числа в коллекции сумма которых минимальна, вернуть коллекцию,
// содержащую индексы этих чисел
//
//        2.2 Реализовать метод
//public static Collection<Integer> findLocalMax(Collection<Integer> numbers) -
// найти локальные максимумы - числа, которые больше соседа справа и слева.
// Первый и последний элемент коллекции не может являться локальным  максимумом,
// вернуть коллекцию, содержащую значения этих максимумов
//
//        2.3 Реализовать метод
//public static boolean findSequence(Collection<Integer> numbers) -
// проверить, содержит ли коллекция все числа от 1 до size(), порядок может быть произвольный
//
//        2.4 Реализовать метод
//
//public static String findSimilar(Collection<String> names) -
// найдите максимальное количество повторяющихся подряд элементов.
// Результат вернуть в виде строки <элемент>:<количество>, например Василий:5.
// При равенстве максимального количества у разных повторяющихся элементов, вернуть результат для элемента,
// повторяющаяся последовательность которого началась с наименьшего индекса.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Finder
{
  // найти 2 соседних числа в коллекции сумма которых минимальна, вернуть коллекцию,
// содержащую индексы этих чисел
  public static Collection<Integer> findMinSumPair(Collection<Integer> numbers)
  {
    List<Integer> source = (List<Integer>) numbers;

    Collection<Integer> res = new ArrayList<>();
    int id = 0;

    for (int i = 0; i < source.size(); i++)
    {
      switch (i)
      {
        case 0:
          break;
        case 1:
          id = 1;
          break;
        default:
          if ((source.get(i - 1) + source.get(i)) < (source.get(id - 1) + source.get(id)))
            id = i;
      }
    }

    res.add(id - 1);
    res.add(id);

    return res;
  }
//------

  // найти локальные максимумы - числа, которые больше соседа справа и слева.
// Первый и последний элемент коллекции не может являться локальным  максимумом,
// вернуть коллекцию, содержащую значения этих максимумов
  public static Collection<Integer> findLocalMax(Collection<Integer> numbers)
  {
    List<Integer> source = (List<Integer>) numbers;

    Collection<Integer> res = new ArrayList<>();

    for (int i = 1; i < source.size() - 1; i++)
      if ((source.get(i - 1) < source.get(i)) && (source.get(i) > source.get(i + 1)))
        res.add(source.get(i));

    return res;

  }

  public static boolean findSequence(Collection<Integer> numbers)
// проверить, содержит ли коллекция все числа от 1 до size(), порядок может быть произвольный
  {
    List<Integer> source = (List<Integer>) numbers;

    int[] flags=new int[numbers.size()];
    Arrays.fill(flags,0);

//сумма от 1 до size
    int sum = 0;
    for (int i = 0; i < source.size(); i++)
    {
//за пределами массива или уже было
      if (source.get(i)>source.size() || source.get(i)<1 || flags[source.get(i)-1]>0)
        return false;

      sum++;
      flags[source.get(i)-1]=1;
    }

    return sum == source.size();
  }

  public static String findSimilar(Collection<String> names)
// найдите максимальное количество повторяющихся подряд элементов.
// Результат вернуть в виде строки <элемент>:<количество>, например Василий:5.
// При равенстве максимального количества у разных повторяющихся элементов, вернуть результат для элемента,
// повторяющаяся последовательность которого началась с наименьшего индекса.
  {
    List<String> source = (List<String>) names;

    String max_item = "";
    int max_count = 0;

    String item = "";
    int count = 0;

    for (int i = 0; i < source.size(); i++)
    {
      if (item.compareTo(source.get(i)) == 0)
//совпадает - увеличиваем кол-во
        count++;
      else
      {
//не совпадает. если найдено больше - запомнить
        if (max_count < count)
        {
          max_count = count;
          max_item = item;
        }
//считаем заново
        item = source.get(i);
        count = 1;
      }
    }

//проверить в конце
    if (max_count < count)
      return count + ":" + item;

    return max_item + ":" + max_count;
  }

  public static void main(String[] args)
  {
    Collection<Integer> list = new ArrayList<>();
    list.add(5);
    for (int i = 0; i < 5; i++)
      list.add(i);
    System.out.println("source: " + list);
//=============================

    System.out.println("find min sum: " + findMinSumPair(list));
//==============================
    list.clear();
    list.add(1);
    list.add(5);
    list.add(4);
    System.out.println("source: " + list);
    System.out.println("find local max: " + findLocalMax(list));

//==============================
    list.clear();
    list.add(1);
    list.add(3);
    list.add(2);
    list.add(4);
    System.out.println("source: " + list);
    System.out.println("findsequence: " + findSequence(list));

//==============================
    Collection<String> source = new ArrayList<>();
    source.add("1");
    source.add("3");
    source.add("2");
    source.add("2");

    System.out.println("source: " + source);
    System.out.println("similar: " + findSimilar(source));

  }
}
