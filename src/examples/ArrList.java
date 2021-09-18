package examples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrList {
   public static void main(String[] args) {
      // Создаём список массивов
      List<String> al = new ArrayList();

      // Добавляем элементы к списку массивов
      al.add("C");
      al.add("A");
      al.add("E");
      al.add("B");
      al.add("D");
      al.add("F");

      // Используем итератор, для отображения элементов al
      System.out.print("Начальное содержание al: ");
      Iterator itr = al.iterator();

      while(itr.hasNext()) {
         Object element = itr.next();
         System.out.print(element + " ");
      }
      System.out.println();

      // Изменяем повторяемые объекты
      ListIterator litr = al.listIterator();

      while(litr.hasNext()) {
         Object element = litr.next();
         litr.set(element + "+");
      }
      System.out.print("Изменённое содержание al: ");
      itr = al.iterator();

      while(itr.hasNext()) {
         Object element = itr.next();
         System.out.print(element + " ");
      }
      System.out.println();

      // Теперь, отобразим список задом наперёд
      System.out.print("Изменённый список задом наперёд: ");

      while(litr.hasPrevious()) {
         Object element = litr.previous();
         System.out.print(element + " ");
      }
      System.out.println();
   }
}
