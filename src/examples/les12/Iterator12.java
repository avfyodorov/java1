package examples.les12;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Iterator12
{
  public static void main(String[] args)
  {
    List<Integer> linkedList = new LinkedList();
    for (int i = 0; i < 5; i++)
      linkedList.add(i);
//---------------------------------------
//       0 0 2 4 4 8
    for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); )
    {
      Integer n = listIterator.next();
      if (n % 2 != 0)
        listIterator.remove();
      else
        listIterator.add(n * 2);
    }
    System.out.println(linkedList);
//---------------------------------------\
//         0 2 2 6 4
//      for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); )
//      {
//        Integer n = listIterator.next();
//        if (n % 2 != 0)
//          listIterator.set(n * 2);
//      }

    for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); )
    {
      Integer n = listIterator.next();
      System.out.println(n);
    }
  }
}
