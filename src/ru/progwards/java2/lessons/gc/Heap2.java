package ru.progwards.java2.lessons.gc;

import java.util.Arrays;
import java.util.HashMap;

public class Heap2
{
  public Heap2(int maxHeapSize)
  {
    bytes = new byte[maxHeapSize];
  }

  byte[] bytes; //- собственно, куча
//набор занятых блоков
  static final int N_BLOCKS = 100_000;
//list used blocks in bytes/ [ptr,size], [ptr,size],.....
  int[] used = new int[N_BLOCKS * 2];
//idx - индекс 1-го свободного блока в массиве used.  idx = 0, 2 ,4....
  int idx = 0;

  void addIDX()
  {
//дошли до границы массива. увеличить размер, скопировать
    if (idx == used.length - 2)
    {
      int[] arr = new int[used.length + N_BLOCKS];
      System.arraycopy(used, 0, arr, 0, used.length);
      used = arr;
    }
    idx = idx + 2;
  }

  int findPtr(int ptr)
  {
    int i = 0;
    while (i < idx)
    {
      if (ptr == used[i])
        return i;
      i = i + 2;
    }

    return -1;
  }

  public void free(int ptr) throws InvalidPointerException
  //- "удаляет", т.е. помечает как свободный блок памяти по "указателю".
  // Проверять валидность указателя - т.е. то, что он соответствует началу ранее выделенного блока,
  // а не его середине, или вообще, уже свободному.
  {
    int id = findPtr(ptr);
    if (id == -1)
      throw new InvalidPointerException();

//удалить указатель и сжать массив used. Данные не трогать
    if (id < idx - 2)
      System.arraycopy(used, id + 2, used, id, idx - id - 2);

    idx = idx - 2;
  }

  //1-й свободный = последний занятый + его длина
  int getFirstUnused()
  {
    if (idx == 0) return 0;
    return used[idx - 2] + used[idx - 1];
  }

  public int malloc(int size) throws OutOfMemoryException
//- "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size.
// Соответственно это должен быть непрерывный блок (последовательность ячеек),
// которые на момент выделения свободны.
// Возвращает "указатель" - индекс первой ячейки в массиве, размещенного блока.
  {
//мало свободного места -
    if (bytes.length - getFirstUnused() < size)
    {
      compact();

//совсем нет места
      if (bytes.length - getFirstUnused() < size)
        throw new OutOfMemoryException();
    }

//заполнить новый блок, сдвинуть индекс
    used[idx] = getFirstUnused();
    used[idx + 1] = size;
    addIDX();
    return used[idx - 2];
  }

  public void compact()
  //- компактизация кучи - перенос всех занятых блоков в начало хипа,
  // с копированием самих данных - элементов массива.
  // Для более точной имитации производительности копировать просто в цикле по одному элементу,
  // не используя System.arraycopy.
  // Обязательно запускаем compact из malloc если не нашли блок подходящего размера
  {
    int firstUnused = 0;
    int i = 0;
    while (i < idx)
    {
      if (firstUnused < used[i])
        System.arraycopy(bytes, used[i], bytes, firstUnused, used[i + 1]);
      used[i] = firstUnused;
      firstUnused = firstUnused + used[i + 1];
      i = i + 2;
    }
  }
void debug()
{
  System.out.println("idx="+idx+" used="+Arrays.toString(used));
}
  public static void main(String[] args) throws InvalidPointerException
  {
    try
    {
      Heap2 heap = new Heap2(10);
      heap.debug();
      heap.malloc(3);
      heap.debug();
      heap.malloc(5);
      heap.debug();
      heap.free(0);
      heap.debug();
      heap.malloc(4);
      heap.debug();
    } catch (OutOfMemoryException e)
    {
      System.out.println(e.getMessage());
    } catch (InvalidPointerException e)
    {
      System.out.println(e.getMessage());
    }
  }

}
