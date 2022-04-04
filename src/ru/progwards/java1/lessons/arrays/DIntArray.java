package ru.progwards.java1.lessons.arrays;

//Реализовать динамический, саморастущий массив целых чисел, по следующей спецификации:
//
//        3.1 в классе разместить private переменную - массив целых чисел.
//        3.2 конструктор - по умолчанию.
//        3.2 метод
//public void add(int num) - добавляет элемент num в конец массива,
// при этом размер массива должен увеличиться на 1.
// Для этого нужно будет разместить новый массив нужного размера,
// скопировать в него старый, и добавить в хвост элемент num.
//        3.3 метод
//public void atInsert(int pos, int num) - добавляет элемент num в позицию pos массива,
// при этом размер массива должен увеличиться на 1.
// Для этого нужно будет разместить новый массив нужного размера,
// скопировать в него старый, c учетом того, что новый элемент окажется где-то в середине,
// и потом положить в нужный индекс элемент num.
//        3.4 метод
//public void atDelete(int pos) - удаляет элемент в позиции pos массива,
// при этом размер массива должен уменьшиться на 1.
// Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый,
// уже без элемента, который был в позиции pos.
//        3.5 метод
//public int at(int pos) - возвращает элемент по индексу pos.

import java.util.Arrays;

public class DIntArray
{
  private int[] a;

  public void add(int num)
  {
    if (a == null)
    {
      a = new int[1];
      a[0] = num;
      return;
    }
    int[] b = new int[a.length + 1];
    System.arraycopy(a, 0, b, 0, a.length);
    b[b.length - 1] = num;
    a = b;
  }

  private boolean isCorrectPos(int pos)
  {
    return pos < a.length && pos >= 0;
  }

  public void atInsert(int pos, int num)
  {
    if (isCorrectPos(pos) == false) return;

    int[] b = new int[a.length + 1];

    if (pos==0)
      System.arraycopy(a, 0, b, 1, a.length);
    else
    {
      System.arraycopy(a, 0, b, 0, pos);
      System.arraycopy(a, pos, b, pos+1, a.length-pos);
    }

    b[pos]=num;

    a = b;
  }

  public void atDelete(int pos)
  {
    if (isCorrectPos(pos) == false) return;

    int[] b = new int[a.length - 1];

    if (pos==0)
      System.arraycopy(a, 1, b, 0, a.length-1);
    else if (pos==a.length-1)
      System.arraycopy(a, 0, b, 0, a.length-1);
    else
    {
      System.arraycopy(a, 0, b, 0, pos);
      System.arraycopy(a, pos+1, b, pos, a.length-pos-1);

    }

    a = b;
  }

  public int at(int pos)
  {
    return isCorrectPos(pos) ? a[pos] : Integer.MIN_VALUE;
  }

  public static void main(String[] args)
  {
    DIntArray d = new DIntArray();
    System.out.println("Before :  " + Arrays.toString(d.a));
    d.add(5);
    d.add(60);
    d.add(8);
    System.out.println("added  :  " + Arrays.toString(d.a));

    d.atInsert(2,19);
    System.out.println("inserted:  " + Arrays.toString(d.a));

//    d.atDelete(2);
//    System.out.println("deleted:  " + Arrays.toString(d.a));
//
  }
}
