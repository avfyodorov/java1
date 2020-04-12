package examples.les12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test12
{
//  Напишите метод с сигнатурой public List<Integer> listAction(List<Integer> list),
//  который выполняет следующие действия:
//
//  удаляет минимальный элемент коллекции
//  по индексу 0 добавляет число равное количеству элементов
//  по индексу 2 добавляет максимальный элемент из list
//  возвращает list как результат метода
public List<Integer> listAction(List<Integer> list)
{
//  System.out.println(list.toString());
  list.remove(Collections.min(list));
//  System.out.println(list.toString());
  list.add(0,list.size());
  list.add(2,Collections.max(list));
//  System.out.println(list);

  return list;
}


  public static void main(String[] args)
  {
    List<Integer> items = new ArrayList();
    for(int i=0; i<5; i++)
      items.add(i);
    Test12 tt=new Test12();
    tt.listAction(items);


    Collection<Integer> numbers = new ArrayList();
    for(int i=0; i<5; i++)
      numbers.add(i);

    //((ArrayList)numbers).add(3, numbers.size());
//    numbers.add(Collections.min(numbers));
    numbers.remove(3);
    //numbers.add(3,5);
    System.out.println(numbers);
  }
}
