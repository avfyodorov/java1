package misc.kadyrovas.basetypes;

import java.util.Iterator;

public class BiDirList <T extends Comparable> implements Iterable<T> {

    static class ListItem <T> {
        private ListItem itemPrevios;
        private ListItem itemNext;
        private T item;

        ListItem(T item) {
            this.item = item;
            this.itemNext = null;
            this.itemPrevios = null;
        }

        void setNext(ListItem<T> item) {this.itemNext = item;}
        void setPrevios(ListItem<T> item) {this.itemPrevios = item;}
        ListItem<T> getNext() {return this.itemNext;}
        ListItem<T> getPrevios() {return this.itemPrevios;}
    }

    private ListItem head;
    private ListItem tail;
    ListItem<T>getHead() {return this.head;}
    ListItem<T>getTail() {return this.tail;}

    @Override
    public Iterator<T> iterator() {
        Iterator<T> myIterator = new Iterator<T>() {
            ListItem<T>current = head;
            boolean firstItem = true;

            @Override
            public boolean hasNext() {
                if (current.getNext() == null) return false;
                else return true;
            }

            @Override
            public T next() {
                if (firstItem) {
                    firstItem = false;
                    return current.item;
                }
                else {
                    current = current.getNext();
                    return current.item;
                }
            }
        };
        return myIterator;
    }

    public void addLast(T item) { // добавить в конец списка
        ListItem element = new ListItem<T>(item);
        if (this.tail == null) firstElement(element);
        else {
            this.tail.itemNext = element;
            element.itemPrevios = this.tail;
            this.tail = element;
        }
    }
    public void addFirst(T item) { // добавить в начало списка
        ListItem<T> element = new ListItem<>(item);
        if (this.head == null) firstElement(element);
        else {
            this.head.itemPrevios = element;
            element.itemNext = this.head;
            this.head = element;
        }
    }

    private void firstElement(ListItem<T> elem){
        this.head = elem;
        this.tail = elem;
    }

    public<T extends Comparable> void remove(T item) { //удалить
        int count = 0;
        ListItem currentItem = getHead();
        while (item != currentItem.item && currentItem.itemNext != null)
            currentItem = currentItem.getNext();
        if (item.compareTo(currentItem.item) == 0) {
            if (currentItem.getPrevios() != null)
                currentItem.getPrevios().itemNext = currentItem.getNext();
            if (currentItem.getNext() != null)
                currentItem.getNext().itemPrevios = currentItem.getPrevios();
            if (currentItem.equals(head))
                head = currentItem.getNext();
            if (currentItem.equals(tail))
                tail = currentItem.getPrevios();
        }
    }

    public T at(int i) { // получить элемент по индексу
        int sch = 0;
        ListItem<T> element = this.head;
        while (true) {
            if (sch == i) return element.item;
            if (element.itemNext == null) return null;
            sch++;
            element = element.itemNext;
        }
    }

    public int size() { // получить количество элементов
        int count = 1;
        if (this.head == null) return 0;
        ListItem element = this.head;
        while (element.itemNext != null) {
            count++;
            element = element.itemNext;
        }
        return count;
    }
    public static<T> BiDirList from(T[] array) { // конструктор из массива
        BiDirList list = new BiDirList<>();
        ListItem element = null;
        ListItem previosElement = null;
        for (int i = 0; i < array.length; i ++) {
            element = new ListItem<T> (array[i]);
            if (i == 0) list.head = element;
            else if (i == array.length - 1) list.tail = element;
            if (previosElement != null) {
                previosElement.setNext(element);
                element.setPrevios(previosElement);
            }
            previosElement = element;
        }
    return list;
    }

    public static<T> BiDirList of(T...array) { //  конструктор из массива
        BiDirList list = from(array);
        return list;
    }

    public <T> void toArray(T[] array) { // скопировать в массив
    //убрал из сигнатуры static
        int count = 0;
        ListItem<T> current = this.head;
        while (current.getNext() != null) {
            array[count] = current.item;
            count++;
            current = current.getNext();
        }
        array[count] = current.item;
    }

    public static void main(String[] args) {
BiDirList<Integer>list=new BiDirList<>();
        System.out.println(list.size());



        //        BiDirList<Integer> myList = from(new Integer[]{10, 11, 12, 13, 14, 15});
        BiDirList<Integer> myList = of(10,11,12,13,14,15);
        myList.addLast(2);
        myList.addLast(3);
        myList.addLast(4);
        myList.addLast(5);
        myList.addFirst(1);
        myList.forEach(System.out::println);
        System.out.println("---------------");
        System.out.println("size = " + myList.size());
        int item = myList.at(2);
        System.out.println(item);
        System.out.println("---------------");
        System.out.println(myList.getHead().item + "    " + myList.getTail().item);
        myList.remove(1);
        myList.remove(5);
        System.out.println(myList.getHead().item + "    " + myList.getTail().item);
        System.out.println("---------------");
        myList.remove(13);

        Integer[] ar = new Integer[myList.size()];
        myList.toArray(ar);
        for (int el: ar)
            System.out.println(el);
    }
}
