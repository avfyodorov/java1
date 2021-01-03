package ru.progwards.java2.lessons.basetypes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BiDirList<T> implements
        Iterable<BiDirList.ListItem>,
        Iterator<BiDirList.ListItem> {

   @Override
   public Iterator<BiDirList.ListItem> iterator() {
      return this;
   }

   @Override
   public boolean hasNext() {
      if (index < count_elem) return true;
      index = 0;
      currentIterator = head;
      return false;
   }

   @Override
   public ListItem<T> next() {
      if (!hasNext()) {
         throw new NoSuchElementException();
      }
      if (index == 0) currentIterator = head;
      ++index;
      currentIterator = currentIterator.getNext();
      return index >= count_elem ? tail : currentIterator.getPrevious();
   }

   class ListItem<T> {

      private T item;
      private ListItem<T> next;
      private ListItem<T> previous;

      ListItem(T item) {
         this.item = item;
      }

      T getItem() {
         return item;
      }

      void setNext(ListItem<T> item) {
         next = item;
      }

      ListItem<T> getNext() {
         return next;
      }

      void setPrevious(ListItem<T> item) {
         previous = item;
      }

      ListItem<T> getPrevious() {
         return previous;
      }

      @Override
      public String toString() {
         return "Item - " + item;
      }
   }

   private ListItem<T> head;
   private ListItem<T> tail;
   private ListItem<T> currentIterator;
   private int count_elem = 0;
   private int index = 0;

   ListItem<T> getHead() {
      return head;
   }

   ListItem<T> getTail() {
      return tail;
   }

   public void addLast(T item) {
      ListItem<T> li = new ListItem<>(item);
      if (tail == null) {
         tail = li;
         head = li;
      } else {
         tail.setNext(li);
         li.setPrevious(tail);
         tail = li;
      }
      ++count_elem;
   }

   public void addFirst(T item) {
      ListItem<T> li = new ListItem<>(item);
      if (head == null) {
         head = li;
         tail = li;
      } else {
         li.setNext(head);
         head.setPrevious(li);
         head = li;
      }
      ++count_elem;
   }

   public void remove(T item) {
      if (count_elem == 1 && head.getItem().equals(item)) {
         head = null;
         tail = null;
         count_elem = 0;
      } else {
         ListItem<T> current = head;
         while (current != null) {
            if (current.getItem().equals(item)) {
               if (current.getPrevious() == null) {
                  head = current.getNext();
                  current.getNext().setPrevious(null);
               } else if (current.getNext() == null) {
                  tail = current.getPrevious();
                  current.getPrevious().setNext(null);
               } else {
                  current.getPrevious().setNext(current.getNext());
                  current.getNext().setPrevious(current.getPrevious());
               }
               --count_elem;
               break;
            }
            current = current.getNext();
         }
      }
   }

   public T at(int i) {
      if (i < 0 || i >= count_elem) {
         throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + count_elem);
      }
      ListItem<T> current = head;
      if (i < count_elem / 2) {
         for (int j = 0; j < i; ++j)
            current = current.getNext();
      } else {
         current = tail;
         for (int j = count_elem - 1; j > i; --j)
            current = current.getPrevious();
      }
      return current.getItem();
   }

   public int size() {
      return count_elem;
   }

   public static <T> BiDirList<T> from(T[] array) {
      BiDirList<T> list = new BiDirList<>();
      for (T a : array)
         list.addLast(a);
      return list;
   }

   public static <T> BiDirList<T> of(T... array) {
      BiDirList<T> list = new BiDirList<>();
      for (T a : array)
         list.addLast(a);
      return list;
   }

   public void toArray(T[] array) {
      int len = count_elem;
      if (len > array.length) len = array.length;
      ListItem<T> current = head;
      for (int i = 0; i < len; ++i) {
         array[i] = current.getItem();
         current = current.getNext();
      }
   }

   public static void main(String[] args) {
      Integer[] arr = {1, 2, 3, 4, 5};
      BiDirList<Integer> list = from(arr);
      list.forEach(System.out::println);
      System.out.println("-----------");

      list = of(5, 6, 7, 8, 9);
      list.addLast(1);
      list.addFirst(3);
      list.forEach(System.out::println);

      list.toArray(arr);

      BiDirList<Integer>.ListItem<Integer> current = list.getHead();
      list.remove(3);
      list.remove(6);
      System.out.println(list.at(0));
//        current = list.getHead();
      System.out.println("Size - " + list.size());
      while (current != null) {
         System.out.println(current.getItem());
         current = current.getNext();
      }
      System.out.println("-----------");

      current = list.getTail();
      while (current != null) {
         System.out.println(current.getItem());
         current = current.getPrevious();
      }
      System.out.println("-----------");

      while (list.hasNext()) {
         System.out.println(list.next());
      }
   }
}
