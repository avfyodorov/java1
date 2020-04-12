package examples.les12;

//Напишите метод с сигнатурой public void iterator3(ListIterator<Integer> iterator)
// который заменяет значение каждого элемента, которое кратно 3 на значение его индекса.

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Iterator12_1
{
  public void iterator3(ListIterator<Integer> iterator)
  {
    while (iterator.hasNext())
    {
      Integer n = iterator.next();
      if (n % 3 == 0)
        iterator.set(iterator.nextIndex() - 1);
    }

  }

  public static void main(String[] args)
  {
    List<Integer> linkedList = new LinkedList();
    for (int i = 0; i < 15; i++)
      linkedList.add(15 - i);
//---------------------------------------
    System.out.println("до того - " + linkedList.toString());
    //-------------------------

    Iterator12_1 o = new Iterator12_1();
    o.iterator3(linkedList.listIterator());
    System.out.println("после - " + linkedList.toString());

  }
}
