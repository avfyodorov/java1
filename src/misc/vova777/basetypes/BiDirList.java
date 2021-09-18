package misc.vova777.basetypes;

import java.util.Arrays;
import java.util.Iterator;


//1.1 public void addLast(T item) - добавить в конец списка
//1.2 public void addFirst(T item)- добавить в начало списка
//1.3 public void remove(T item) - удалить
//1.4 public T at(int i) - получить элемент по индексу
//1.5 public int size() - получить количество элементов
// 1.6 public static<T> BiDirList<T> from(T[] array) - конструктор из массива
//1.7 public static<T> BiDirList<T> of(T...array) -  конструктор из массива
// 1.8 public void toArray(T[] array) - скопировать в массив
// 1.9 реализовать интерфейс Iterable<T>

public class BiDirList<T> implements Iterable<T> {
    private int size = 0;

    class ListIteam<T> implements Iterator<T> {

        private T iteam;
         ListIteam<T> next;
         ListIteam<T> prev;
         ListIteam(T item){
            this.iteam = item;
        }

        @Override
        public boolean hasNext() {
            if (iterator == tail){

                return false;}
            else {
                return true;
            }
        }

        @Override
        public T next() {
            iterator = iterator.next;
            return (T) iterator.iteam;

        }

    }
    public ListIteam<T> tail;
    public ListIteam<T> head;

    ListIteam<T> iterator = new ListIteam<T>(null);

    @Override
    public Iterator iterator() {
        iterator.next = head;
        return iterator;
    }



















    public void addLast(T item){
        if (head == null){
            head = new ListIteam<>(item);
            tail = head;

        }else{
            ListIteam<T> listIteam = new ListIteam<>(item);
            listIteam.prev = tail;
            tail.next = listIteam;
            tail = listIteam;
        }
        size++;
    }

    public void addFirst(T item){
        if (head == null){
            head = new ListIteam<>(item);
            tail = head;

        }else{
            ListIteam<T> listIteam = new ListIteam<>(item);
            listIteam.next = head;
            head.prev = listIteam;
            head = listIteam;
        }
        size++;
    }

    public void remove(T item){
        if (head == null) throw new NullPointerException("List is empty");
        if (item.equals(head.iteam)) {
            if (head.next == null) {head = null; return;}
            this.removeFirst();
            if (head.iteam.equals(item)) remove(item);
            if (head == null) return;
        }
        if (head.next == null) return;
        ListIteam<T> currentList = head.next;
        while(currentList != tail) {
            if (currentList.iteam.equals(item)){
                currentList.prev.next = currentList.next;
                currentList.next.prev = currentList.prev;
                size--;
            }
            currentList = currentList.next;
        }
        if (item.equals(tail.iteam)){
            this.removeLast();
            while (tail.iteam.equals(item)) {
                this.removeLast();}
        }
    }

    public void removeFirst(){
        if (head == null) throw new NullPointerException("List is empty");
        if (head.next == null) {head = null; return;}
        head = head.next;
        head.prev.next = null;
        head.prev = null;
        size--;
    }

    public void removeLast(){
        if (head == null) throw new NullPointerException("List is empty");
        if (tail.prev == null) {tail = null; return;}
        tail = tail.prev;
        tail.next.prev = null;
        tail.next = null;
        size--;
    }

    public T at(int i) {
        if (head == null) throw new NullPointerException("List is empty");
        if (i >= this.size || i < 0) throw new IndexOutOfBoundsException();
        ListIteam<T> current;
        if (i < (size/2)){
            current = head;
            for (int j = 0; j < i; j++){
                current = current.next;
            }
        }
        else {current = tail;
            for (int j = (size - 1); j > i; j--){
                current = current.prev;
            }
        }
        return current.iteam;
    }

    public static<T> BiDirList<T> from(T[] array){
        if (array.length == 0) throw new UnsupportedOperationException("Array is empty");
        BiDirList<T> list = new BiDirList<>();
        list.head = list.creatorListIt(array[0]);
        list.tail = list.head;
        if (array.length == 1) {list.tail = list.head; return list;}
        if (array.length > 1){
            for (int i = 1; i < array.length; i++){
                list.tail.next = list.creatorListIt(array[i]);
                list.tail.next.prev = list.tail;
                list.tail = list.tail.next;
            }
        }
        return list;
    }

    private ListIteam<T> creatorListIt(T iteam){
        ListIteam<T> listIteam = new ListIteam<>(iteam);
        return listIteam;
    }

    public static<T> BiDirList<T> of(T...array){
        return from(array);
    }

    public void toArray(T[] array){
        if (this.size > array.length) throw new UnsupportedOperationException("size > length of array");
        ListIteam<T> currentListIteam = this.head;
        int i = 0;
        while (currentListIteam != null) {
            array[i] = currentListIteam.iteam;
            currentListIteam = currentListIteam.next;
            i++;
        }
    }

    @Override
    public String toString() {
        if (head == null) return "[ ]";
        String result ="[ " + head.iteam;
        ListIteam<T> r = head;
        if (head == tail){return result + " ]";}
        else {
            do{
                r = r.next;
                result += ", " + r.iteam;
            }while (r != tail);
        return result + " ]";
        }
    }

    public static void main(String[] args) throws Exception {
        BiDirList<Integer> list = new BiDirList<>();
//list.remove(7);
        list.addLast(1);
        list.addFirst(56);
        list.addLast(5);
        list.addFirst(45);
        list.addLast(0);
        list.remove(1);

        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
        // i = {0..size-1}
        System.out.println("3й - " + list.at(2));
        System.out.println("всего " + list.size());

        BiDirList<Integer> list3 = of(-1, 105, 58, 6, 16, -89, 23, 555);
        list3.remove(23);
        list3.addLast(71);
        for (Integer integer : list3) {
            System.out.print(integer + " ");
        }
        System.out.println();

        BiDirList<String> list2 = from(new String[]{"as", "at", "au"});
        for (String s : list2) {
            System.out.print(s + " ");
        }
        System.out.println();

        String[] strArray = new String[3];
        list2.toArray(strArray);
        for (String a: strArray) {
            System.out.println(a);
        }
    }

    private int size() {
        return size;
    }
/*
45 56 5 0
3й - 5
всего 4
-1 105 58 6 16 -89 555 71
as at au
as
at
au
 */

/*
    public static void main(String[] args) {
        // проверка addLast
        BiDirList<String> list = new BiDirList<>();
        list.addLast("Макар");
        System.out.println(list.toString());
        list.addLast("Егор");
        System.out.println(list.toString());
        list.addLast("Анастасия");
        System.out.println(list.toString());
        list.addLast("Владимир");
        System.out.println(list.toString());
        System.out.println(list.head.iteam);

// проверка addFirst
        BiDirList<Integer> listAddFirst = new BiDirList<>();
        listAddFirst.addFirst(3);
       // System.out.println(listAddFirst.toString());
        listAddFirst.addFirst(3);
        //System.out.println(listAddFirst.toString());
        listAddFirst.addFirst(5);
        //System.out.println(listAddFirst.toString());
        listAddFirst.addFirst(7);
        //System.out.println(listAddFirst.toString());
        listAddFirst.addLast(3);
//        System.out.println(listAddFirst.toString());// проверка с addLast
//        // проверка remove
//        listAddFirst.remove(7);
//        System.out.println(listAddFirst.toString());
////        listAddFirst.remove(5);
////        System.out.println(listAddFirst.toString());
//        listAddFirst.remove(100);
//        System.out.println(listAddFirst.toString());
//        listAddFirst.remove(3);
//        System.out.println(listAddFirst.toString());
////        listAddFirst.remove(1);
////        System.out.println(listAddFirst.toString());

        System.out.println(list.at(1));// check public T at(int i)

        //check public static<T> BiDirList<T> from(T[] array)

        Integer[] array = {1,2,3,4,5,6,7,8,9,10};
        BiDirList<Integer> listInt = from(array);
        System.out.println(listInt.toString());
        BiDirList<Integer> listInt2 = of(10,9,8,7,6,5,4,3,2,1);
        System.out.println(listInt2.toString());
        Integer[] ar1 = new Integer[10];
        listInt2.toArray(ar1);
        System.out.println("проверка public void toArray(T[] array): " + Arrays.toString(ar1));

        // check Iterable
        Iterator iterator = listInt.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        while(iterator.hasNext()){
            System.out.println(iterator.next());}






    }
*/


}
