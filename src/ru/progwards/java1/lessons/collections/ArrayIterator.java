package ru.progwards.java1.lessons.collections;


//Сделать итератор по одномерному массиву, реализовать методы hasNext() и next(). Шаблон пустого итератора:

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T>
{

  private T[] array;
  private int cur;

  ArrayIterator(T[] array)
  {
    this.array = array;
    cur = 0;
  }

  @Override
  public boolean hasNext()
  {
    return cur < array.length;
  }

  @Override
  public T next()
  {
    return array[cur++];
  }

  public static void main(String[] args)
  {
    Integer[] arri = {1, 2, 3};

    ArrayIterator<Integer> o = new ArrayIterator<Integer>(arri);
    for (Integer i : o.array)
      System.out.print(i + ", ");

    System.out.println("");
//    System.out.println(5.234 % 2);
  }
}
