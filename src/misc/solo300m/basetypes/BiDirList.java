package misc.solo300m.basetypes;

import java.util.Iterator;

public class BiDirList<T> implements Iterable<T> {
   public Iterator<BiDirList<T>> getIterator() {
      Iterator<BiDirList<T>> row = new Iterator<BiDirList<T>>() {
         int curretAllIndex = 1;

         @Override
         public boolean hasNext() {
            if (curretAllIndex < size() + 1)
               return true;
            else
               return false;
         }

         @Override
         public BiDirList<T> next() {
            if (hasNext()) {
               return (BiDirList<T>) at(curretAllIndex++);
            }
            return null;
         }
      };
      return row;
   }

   @Override
   public Iterator<T> iterator() {

      Iterator<T> iterator = new Iterator<T>() {
         private int currentIndex = 1;

         @Override
         public boolean hasNext() {
            if (currentIndex < size() + 1)
               return true;
            else
               return false;
         }

         @Override
         public T next() {
            return at(currentIndex++);
         }
      };
      return iterator;
   }

   /**
    * Встроенный класс обобщенный ListItem
    *
    * @param <T> может принимать любые типы, в том числе созданные программистом
    */
   public static class ListItem<T> {
      private T item;
      private ListItem<T> next;
      private ListItem<T> prev;

      /**
       * Конструктор по умолчанию
       */
      public ListItem() {
         this.item = null;
      }

      /**
       * Конструктор с параметром полем item
       *
       * @param item поле произвольного типа T
       */
      public ListItem(T item) {
         this.item = item;
      }

      public T getItem() {
         return item;
      }

      public void setItem(T item) {
         this.item = item;
      }

      void setNext(ListItem<T> item) {
         this.next = item;
      }

      ListItem<T> getNext() {
         return this.next;
      }

      void setPrev(ListItem<T> item) {
         this.prev = item;
      }

      ListItem<T> getPrev() {
         return this.prev;
      }
   }

   private ListItem<T> head;
   private ListItem<T> tail;
   private static ListItem<?> previous;

   ListItem<T> getHead() {
      return head;
   }

   ListItem<T> getTail() {
      return tail;
   }

   public void addLast(T item) {
      ListItem<T> li = new ListItem<>(item);
      if (head == null) {
         head = li;
         tail = li;
         previous = li;
      } else {
         tail.setNext(li);
         li.setPrev((ListItem<T>) previous);
         previous = li;
         tail = li;
      }
   }

   public void addFirst(T item) {
      ListItem<T> li = new ListItem<>(item);
      if (head == null) {
         head = li;
         tail = li;
         previous = li;
      } else {
         li.setPrev(head.getPrev());
         li.setNext(head);
         head.setPrev(li);
         head = li;
      }
   }

   public void remove(T item) {
      //BiDirList<T>list = new BiDirList<>();
      //BiDirList.ListItem<T> current = list.getHead();
      ListItem<T> iter = new ListItem<>();
      iter = head;
      while (!iter.equals(tail)) {
         if (!iter.getItem().equals(item)) {
            iter = iter.getNext();
         } else {
            //System.out.println(iter.getItem());
            ListItem<T> prev = iter.getPrev();
            ListItem<T> next = iter.getNext();
            prev.setNext(next);
            next.setPrev(prev);
            iter = iter.getNext();
         }
      }
   }

   public int size() {
      ListItem<T> iter = new ListItem<>();
      iter = head;
      int n = 1;
      while (!iter.equals(tail)) {
         iter = iter.getNext();
         n++;
      }
      return n;
   }

   public T at(int i) {
      ListItem<T> iter = new ListItem<>();
      iter = head;
      int n = 1;
      while (n <= size()) {
         if (n == i) {
            return iter.getItem();
         }
         iter = iter.getNext();
         n++;
      }
      return null;
   }

   public static <T> BiDirList<T> from(T[] array) {
      BiDirList<T> list = new BiDirList<>();
      for (T iter : array) {
         list.addLast(iter);
      }
      return list;
   }

   public static <T> BiDirList<T> of(T... array) {
      BiDirList<T> list = new BiDirList<>();
      for (T iter : array) {
         list.addLast(iter);
      }
      return list;
   }

   public void toArray(T[] array) {
      ListItem<T> iter = new ListItem<T>();
      iter = head;

      if (array.length == size()) {
         for (int i = 0; i < array.length; i++) {
            array[i] = iter.getItem();
            iter = iter.getNext();
         }
      } else {
         System.out.println("Длина массива - " + array.length + " Длина списка - " + size());
      }

   }

   private BiDirList<T> getList() {
      BiDirList<T> list = new BiDirList<>();
      ListItem<T> iter = new ListItem<>();
      iter = head;
      while (!iter.equals(tail)) {
         list.addLast(iter.getItem());
         iter = iter.getNext();
      }
      return list;
   }

}

class Test {
   public static void main(String[] args) {
      BiDirList<Integer> list = new BiDirList<>();
      list.addLast(2);
      list.addLast(1);
      list.addLast(5);
      list.addFirst(6);
      list.addFirst(7);
      list.addLast(8);
      BiDirList<Integer> list2 = new BiDirList<>();
      list2.addLast(12);
      list2.addLast(11);
      list2.addLast(15);
      list2.addFirst(16);
      list2.addFirst(17);
      list2.addLast(18);
      BiDirList<BiDirList<Integer>> matrixList = new BiDirList<>();
      matrixList.addLast(list);
      matrixList.addLast(list2);
      for (BiDirList<Integer> s : matrixList) {
         for (Integer f : s) {
            System.out.print(f);
         }
         System.out.println();
      }
        /*System.out.println(list.at(6));
        for(Integer iter:list){
            System.out.print(iter);
        }
        System.out.println();
        BiDirList.ListItem<Integer> current2 = list.getHead();
        while(current2!=null){
            System.out.print(current2.getItem());
            current2 = current2.getNext();
        }
        System.out.println();
        list.remove(2);
        System.out.println();
        current2 = list.getHead();
        while(current2!=null){
            System.out.print(current2.getItem());
            current2 = current2.getNext();
        }*/
   }
}