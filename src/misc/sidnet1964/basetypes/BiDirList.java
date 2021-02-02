package misc.sidnet1964.basetypes;

//        import org.jetbrains.annotations.NotNull;
        import java.util.Arrays;
        import java.util.Iterator;
        import java.util.NoSuchElementException;

public class BiDirList<T> implements Iterable<T>{
  // @NotNull
   @Override
   public Iterator<T> iterator() {
      return new MyIterator<>(head);
   }

   class MyIterator<T> implements Iterator<T>{
      ListItem<T> curr;
      MyIterator(ListItem<T> curr){
         this.curr = curr;
      }
      @Override
      public boolean hasNext() {
         if (head == null)
            return false;
         if (curr == null)
            return false;
         return true;
      }

      @Override
      public T next() {
         if (head == null)
            throw new NoSuchElementException();
         T rezult = curr.item;
         curr = curr.next;
         return rezult;
      }
   }
   //  ----------------------------------------------
   class ListItem<T> {

      private T item;
      private ListItem<T> next;   //  ссылка вперед
      private ListItem<T> prev;   //  ссылка назад

      //  конструктор целевого объекта
      ListItem(T item) {
         this.item = item;
      }
      //  возращаемое значение
      T getItem() {
         return item;
      }
      //  установить ссылку на новый объект
      void setNext(ListItem<T> itemObj) {
         next = itemObj;
      }
      ListItem<T> getNext() {
         return next;
      }
      void setPrev(ListItem<T> itemObj) {
         prev = itemObj;
      }
      ListItem<T> getPrev() {
         return prev;
      }

      @Override
      public String toString() {
         return "{" +
                 "item=" + item +
                 '}';
      }
   }
   //  ----------------------------------------------
   //  структура списка не изменяется, но добавляется size
   private ListItem<T> head;
   private ListItem<T> tail;
   private int size;   //  размер списка
   private ListItem<T> curr;   //  ссылка на текущий элемент итератора

   //  для создания итератора
   ListItem<T> getHead() {
      return head;
   }
   ListItem<T> getTail() {
      return tail;
   }

   //  --------------------------------
   //  1.1 - добавить в конец списка
   public void addLast(T item) {
      ListItem<T> li = new ListItem<T>(item);
      if (head == null) {
         head = li;
         tail = li;
      } else {
         tail.setNext(li);   //  хвост отвалился
         li.prev = getTail();//  prev для нового элемента == старому значению tail
         tail = li;          //  новый хвост
      }
      size++;
   }
   //  --------------------------------
   //  1.2 добавить в начало списка
   public void addFirst(T item) {
      ListItem<T> li = new ListItem<T>(item);
      if (head == null) {
         head = li;
         tail = li;
      } else {
         head.setPrev(li);   //  голова ...
         li.next = getHead();//  next для нового элемента == старому значению head
         head = li;          //  новая голова
      }
      size++;
   }
   //  --------------------------------
   //  1.3 - удалить, но сначала найти в списке
   public void remove(T item){
      if (getSize() == 0)
         throw new IndexOutOfBoundsException("<Index: 0, Size: 0>");
      ListItem<T> current = getHead();    //  начинаем поиск с головы
      while(current != null) {
         if (current.getItem().equals(item)){
            //  значение найдено, можно удалять
            //  варианты: в середине списка, в начале, в конце
            if (current.prev == null)
               head = current.next;
            else
               current.prev.next = current.next;
            if (current.next == null)
               tail = current.prev;
            else
               current.next.prev = current.prev;
            size--;
            return;
         }
//            System.out.println(current.getItem() + "<=>" + item);
         current = current.getNext();    //  продолжение поиска
      }
//        throw new NoSuchElementException("<Index: 0, Size: 0>");
      return;
   }
   //  1.4 - получить элемент по индексу, можно оптимизировать начало поиска
   public ListItem<T> at(int i){
      if (i < 0 || i >= size)
         return null;
      ListItem<T> current = getHead();
//        while(current != null) {
      for (int j = 0; j<i; j++)
         current = current.getNext();
//
//
      return current;
   }
   //  1.5 - получить количество элементов
   public int getSize() {
      return size;
   }
   //  1.6 - конструктор из массива
   public static<T> BiDirList<T> from(T[] array){
      BiDirList<T> list = new BiDirList<>();
      for (T element : array)
         list.addLast(element);
      return list;
   }
   //  1.7  -  конструктор из массива
   public static<T> BiDirList<T> of(T...array){
      BiDirList<T> list = new BiDirList<>();
      for (int i = 0; i < array.length ; i++)
         list.addLast(array[i]);
      return list;
   }
   //  1.8 - скопировать в массив
   public void toArray(T[] array){
      array = (T[]) new Object[size];
      int index = 0;
      BiDirList<T>.ListItem<T> current = getHead();
      while(current != null) {
//            System.out.println(current.getItem());
         array[index++] = current.item;
         current = current.getNext();
      }
//        System.out.println(Arrays.toString(array));
   }
   //  --------------------------------
   //  1.9 public Iterator<BiDirList<T>> getIterator() - получить итератор
//    public Iterator<BiDirList<T>> getIterator(){
//    public Iterator<ListItem<T>> getIterator(){
   public Iterator<T> getIterator() {
      return new MyIterator(head);
   }
   //  --------------------------------
   @Override
   public String toString() {
      return "BiDirList{" +
              "head=" + head +
              ", size=" + size +
              ", tail=" + tail +
              '}';
   }
}
