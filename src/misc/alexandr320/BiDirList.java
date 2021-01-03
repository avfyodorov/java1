package misc.alexandr320;


        import java.util.Iterator;
        import java.util.NoSuchElementException;

public class BiDirList<T> implements Iterable<T> {   // Реализовать класс BiDirList - двунаправленный связный список
   public static void main(String[] args) {
      BiDirList<Integer> list = new BiDirList<>();
      list.addFirst(2);
      list.addFirst(5);
      list.addFirst(7);
      for (Integer val : list) {
         System.out.println(val);
      }

      BiDirList<String> list1 = new BiDirList<>();
      list1.addLast("dfdsfs");
      list1.addLast("ljhljhl");
      list1.addLast("wqrwqr");
      for (String val : list1) {
         System.out.println(val);
      }
   }

   private static class ListItem<T> {

      private T item;
      private ListItem<T> next;
      private ListItem<T> previous;


      ListItem(T item) {
         this.item = item;
      }

      T getItem() {
         return item;
      }

      void setNext(ListItem<T> next) {
         this.next = next;
      }

      void setPrevious(ListItem<T> previous) { this.previous = previous; }

      ListItem<T> getNext() {
         return next;
      }

      public ListItem<T> getPrevious() { return previous; }

      //@Override
      //public String toString() { return item.toString(); }

   }

   private ListItem<T> head;
   private ListItem<T> tail;

   ListItem<T> getHead() {
      return head;
   }

   ListItem<T> getTail() { return tail; }

   void addLast(T item) {  // добавить в конец списка
      ListItem<T> li = new ListItem<T>(item);
      if (head == null) {
         head = li;
         tail = li;
      } else {
         tail.setNext(li);
         li.setPrevious(tail);
         tail = li;
      }
   }

   void addFirst(T item) { // добавить в начало списка
      ListItem<T> li = new ListItem<T>(item);
      if (head == null) {
         head = li;
         tail = li;
      } else  {
         head.setPrevious(li);
         li.setNext(head);
         head = li;
      }
   }

   public void remove(T item) {  // удалить
      if (head == null) {
         throw new NoSuchElementException("Can't remove because list is empty");
      } else if (head.getItem().equals(item)) {
         head = head.getNext();
         head.setPrevious(null);
         if (head == null) {
            tail = null;
         }
      } else if (tail.getItem().equals(item)) {
         tail = tail.getPrevious();
         tail.setNext(null);
      } else {
         ListItem<T> listItem = findListItemOrNull(item);
         if (listItem == null) {
            throw new NoSuchElementException("Element not found");
         }
         ListItem<T> next = listItem.getNext();
         ListItem<T> previous = listItem.getPrevious();
         next.setPrevious(previous);
         previous.setNext(next);
      }
   }

   private ListItem<T> findListItemOrNull(T item) {  // поиск по элементу ListItem<T>
      ListItem<T> current = head;
      while (current != null) {
         if (current.getItem().equals(item)) {
            return current;
         } else {
            current = current.getNext();
         }
      }
      return null;
   }

   public T at(int i) {  // получить элемент по индексу
      int currentIndex = 0;
      ListItem<T> current = head;
      while (current != null) {
         if (i == currentIndex) {
            return current.getItem();
         }
         currentIndex++;
         current = current.getNext();
      }
      throw new IndexOutOfBoundsException();
   }

   public int size() {  // получить количество элементов
      int size = 0;
      ListItem<T> current = head;
      while (current != null) {
         size++;
         current = current.getNext();
      }
      return size;
   }

   public static<T> BiDirList<T> from(T[] array) {  // конструктор из массива
      BiDirList<T> biDirList = new BiDirList<>();
      biDirList.head = new ListItem<T>(array[0]);
      ListItem<T> last = biDirList.head;
      for (int i = 1; i < array.length; i++) {
         T elem = array[i];
         ListItem<T> next = new ListItem<>(elem);
         last.setNext(next);
         next.setPrevious(last);
         last = next;
      }
      biDirList.tail = last;
      return biDirList;
   }

   public static<T> BiDirList<T> of(T...array) {  // конструктор из массива
      return from(array);
   }

   public void toArray(T[] array) {  // скопировать в массив
      int index = 0;
      ListItem<T> current = head;
      while (current != null) {
         array[index] = current.getItem();
         index++;
         current = current.getNext();
      }
   }

   @Override
   public Iterator<T> iterator() {
      return new Iterator<T>() {
         private ListItem<T> curr = head;

         @Override
         public boolean hasNext() {
            return curr != null;
         }

         @Override
         public T next() {
            T result = curr.getItem();
            curr = curr.getNext();
            return result;
         }
      };
   }


}
