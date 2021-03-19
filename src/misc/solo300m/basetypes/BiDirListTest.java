package misc.solo300m.basetypes;

//.1 public void addLast(T item) - добавить в конец списка
//        1.2 public void addFirst(T item)- добавить в начало списка
//        1.3 public void remove(T item) - удалить
//        1.4 public T at(int i) - получить элемент по индексу
//        1.5 public int size() - получить количество элементов
//        1.6 public static<T> BiDirList<T> from(T[] array) - конструктор из массива
//        1.7 public static<T> BiDirList<T> of(T...array) -  конструктор из массива
//        1.8 public static void toArray(T[] array) - скопировать в массив
//        1.9 реализовать интерфейс Iterable<T>

public class BiDirListTest
{
   public static void main(String[] args) {
      BiDirList<Integer> list = new BiDirList<>();
      list.addLast(3);
      list.addLast(4);
      list.addLast(5);
      list.addFirst(2);
      list.addFirst(1);
      list.addLast(6);

      System.out.println("size 0 == "+list.size());

   }
}
