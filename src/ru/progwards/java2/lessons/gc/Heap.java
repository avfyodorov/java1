package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap
{
  public Heap(int maxHeapSize)
  {
    bytes = new byte[maxHeapSize];
  }

  byte[] bytes; //- собственно, куча
  //набор занятых блоков
  HashMap<Integer, Integer> used = new HashMap<>();
  int firstUnused = 0;

  int findHole(int size)
  {
    int curpos = 0;
//есть ли пустой блок в середине
    for (Integer ptr : used.keySet())
    {
//между началом блока и тек.позицией хватает места
      if (ptr - curpos >= size)
        return curpos;

//передвинуть позицию на конец блока
      curpos = ptr + used.get(ptr);
    }

    return -1;
  }

  public int malloc(int size) throws OutOfMemoryException
//- "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size.
// Соответственно это должен быть непрерывный блок (последовательность ячеек),
// которые на момент выделения свободны.
// Возвращает "указатель" - индекс первой ячейки в массиве, размещенного блока.
  {
//если в хвосте достаточно пустого места - помещаем туда
    if (bytes.length - size > firstUnused)
    {
      used.put(firstUnused, size);
      firstUnused = firstUnused + size;
      return firstUnused - size;
    }

//в хвосте места нет - попытаться найти в середине
    int hole = findHole(size);
    if (hole > -1)
    {
//нашли - вставляем
      used.put(hole, size);
      return hole;
    }

//нет подходящего блока - сжать
    compact();

//совсем нет места
    if (bytes.length - firstUnused < size)
      throw new OutOfMemoryException();

    return firstUnused;
  }

  public void free(int ptr) throws InvalidPointerException
  //- "удаляет", т.е. помечает как свободный блок памяти по "указателю".
  // Проверять валидность указателя - т.е. то, что он соответствует началу ранее выделенного блока,
  // а не его середине, или вообще, уже свободному.
  {
    if (!used.containsKey(ptr))
      throw new InvalidPointerException();
    used.remove(ptr);
  }

  public void defrag()
  //- осуществляет дефрагментацию кучи - ищет смежные свободные блоки,
  // границы которых соприкасаются и которые можно слить в один.
  {
  }

  public void compact()
  //- компактизация кучи - перенос всех занятых блоков в начало хипа,
  // с копированием самих данных - элементов массива.
  // Для более точной имитации производительности копировать просто в цикле по одному элементу,
  // не используя System.arraycopy.
  // Обязательно запускаем compact из malloc если не нашли блок подходящего размера
  {
    HashMap<Integer, Integer> res = new HashMap<>();
    firstUnused = 0;

    for (Integer key : used.keySet())
    {
      int sz = used.get(key);
      if (firstUnused < key)
        for (int i = 0; i < sz; i++)
        {
          bytes[firstUnused + i] = bytes[key + i];
        }
      res.put(firstUnused, sz);
      firstUnused = firstUnused + sz;
    }

    used = res;
  }

  public static void main(String[] args) throws  InvalidPointerException
  {
    try
    {
      Heap heap = new Heap(10);
      heap.malloc(11);
      heap.malloc(22);
      heap.malloc(33);
      heap.used.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
      System.out.println("----");
      heap.free(12);
      heap.used.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
    }
    catch (OutOfMemoryException e)
    {
      System.out.println(e.getMessage());
    }
    catch (InvalidPointerException e)
    {
      System.out.println(e.getMessage());
    }
  }

//  public void getBytes(int ptr, byte[] bytes) {
//    System.arraycopy(this.bytes, ptr, bytes, 0, size);
//  }
//
//  public void setBytes(int ptr, byte[] bytes) {
//    System.arraycopy(bytes, 0, this.bytes, ptr, size);
//  }
}

