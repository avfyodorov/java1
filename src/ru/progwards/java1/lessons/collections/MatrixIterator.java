package ru.progwards.java1.lessons.collections;

//Сделать итератор MatrixIterator по двумерному массиву (матрице),
// который разворачивает матрицу в линейную последовательность построчно:
// a[0][0], a[0][1], ...a[0][N],a[1][0], a[1][1]...a[1][N]... a[M][N]
//
//        Шаблон для итератора взять от ArrayIterator

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T>
{
  private T[][] array;
  private int cur;

  MatrixIterator(T[][] array)
  {
    this.array = array;
    cur = 0;
  }

  @Override
  public boolean hasNext()
  {
    int size=0;
    for (int i = 0; i < array.length; i++)
    size=size+array[0].length;
    return cur < size;
  }

  @Override
  public T next()
  {
  //  int i = cur / array[0].length;
  //  int j = cur % array[0].length;
    int x=0;
    int y=0;
    int pos=cur;

    for (int i = 0; i < array.length; i++)
    {
      if (pos>=array[i].length)
        pos=pos-array[i].length;
      else
      {
    x=i;y=pos%array[i].length;break;
      }
    }

    cur++;
    return array[x][y];
  }

  public static void main(String[] args)
  {
    Integer[][] arr = {new Integer[]{1, 2, 3}, new Integer[]{4, 5}, new Integer[]{6, 7,8,9}};
//    System.out.println(arr.length + " " + arr[0].length);

    System.out.println(arr[0][0] + " " + arr[0][1] + " " + arr[0][2]);

    MatrixIterator mi = new MatrixIterator(arr);
    while (mi.hasNext())
      System.out.print(mi.next() + " ");
    System.out.println("");
  }
}
