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
  private int arr_size;

  MatrixIterator(T[][] array)
  {
    this.array = array;
    cur = 0;

    arr_size = 0;
    for (int i = 0; i < array.length; i++)
      arr_size = arr_size + array[i].length;
  }

  @Override
  public boolean hasNext()
  {
    return cur < arr_size;
  }

  @Override
  public T next()
  {
    //  int i = cur / array[0].length;
    //  int j = cur % array[0].length;
    int x = 0;
    int y = 0;
    int pos = cur;

    for (int i = 0; i < array.length; i++)
    {
//если не попадает в тек.массив -- взять следующий
      if (pos >= array[i].length)
        pos = pos - array[i].length;
      else
      {
//строка - ном.тек.массива, столбец - остаток
        x = i;
        y = pos % array[i].length;
        break;
      }
    }

    cur++;
    return array[x][y];
  }

  public static void main(String[] args)
  {
    Integer[][] arr = {new Integer[]{1, 2, 3}, new Integer[]{4, 5}, new Integer[]{6, 7, 8, 9}};
//    System.out.println(arr.length + " " + arr[0].length);

//    String[][] arr = {{"алекс", "васил", "григо"}, {"дмитр", "дмитр", "григо"}, {"дмитр", "васил"}};
//    String[][] arr = {{"boris", "dmitr"}, {"vasil", "vasil", "alexa"}, {"grigo", "dmitr"}};
    //System.out.println(arr[0][0] + " " + arr[0][1] + " " + arr[0][2]);

    int n = 1;
    MatrixIterator mi = new MatrixIterator(arr);
    while (mi.hasNext())
      System.out.println("" + (n++) + " " + mi.hasNext() + " " + mi.next());
    //System.out.println("");
  }
}

//  В конструктор передан двумерный массив содержащий 3 массива.
//  Массив 1, значения: Борис,Дмитрий.
//  Массив 2, значения: Василий,Василий,Александр.
//  Массив 3, значения: Григорий,Дмитрий.
//
//  Текущий порядковый номер вызова : 5.
//  Вызван метод hasNext(), возвращено: true. Ожидалось: true.
//  Вызван метод next(), возвращено: Александр. Ожидалось: Александр.
//  Вызван метод hasNext(), возвращено: false. Ожидалось: true.
//=================================================================


// Тест "Класс MatrixIterator" не пройден. Класс работает неверно.
//
// В конструктор передан двумерный массив содержащий 3 массива.
// Массив 1, значения: Александр,Василий,Григорий.
// Массив 2, значения: Дмитрий,Дмитрий,Григорий.
// Массив 3, значения: Дмитрий,Василий.
//
// Текущий порядковый номер вызова : 8.
// Вызван метод hasNext(), возвращено: true. Ожидалось: true.
// Вызван метод next(), возвращено: Василий. Ожидалось: Василий.
// Вызван метод hasNext(), возвращено: true. Ожидалось: false.
