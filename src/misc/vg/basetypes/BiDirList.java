package misc.vg.basetypes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BiDirList<T> implements Iterable<T> {
    private BiDirListItem<T> head;
    private BiDirListItem<T> tail;
    private int size = 0;

    public void addLast(T item) {
        BiDirListItem<T> li = new BiDirListItem<>(item);
        if (head==null) {
            head = li;
            tail = li;
        } else {
            BiDirListItem<T> temp = tail;
            tail.setNext(li);
            tail = li;
            tail.setPrev(temp);
        }
        size++;
    }

    public void addFirst(T item) {
        BiDirListItem<T> li = new BiDirListItem<>(item);
        if (head==null) {
            head = li;
            tail = li;
        } else {
            BiDirListItem<T> temp = head;
            head.setPrev(li);
            head = li;
            head.setNext(temp);
        }
        size++;
    }

    public void remove(T item) {
        boolean isRemoved = false;
        BiDirListItem<T> cur = head;
        while (cur != null) {
            T tempItem = cur.getItem();
            if (tempItem == item) {
                BiDirListItem<T> tempPrev = cur.getPrev();
                BiDirListItem<T> tempNext = cur.getNext();
                tempPrev.setNext(tempNext);
                tempNext.setPrev(tempPrev);
                size--;
                isRemoved = true;
                break;
            } else {
                cur = cur.getNext();
            }
        }
        if (!isRemoved) {
            throw new NoSuchElementException("Объект для удаления не найден.");
        }
    }

    public T at(int i) {
        if (i>size-1) {
            throw new IndexOutOfBoundsException("Запрашивается индекс " + i + " при длине " + size + ".");
        }
        BiDirListItem<T> cur = head;
        for (int j=0; j<i; j++) {
            cur = cur.getNext();
        }
        return cur.getItem();
    }

    public int size() {
        return size;
    }

    public static<T> BiDirList<T> from(T[] array) {
        BiDirList<T> res = new BiDirList<>();
        for (T t : array) {
            res.addLast(t);
        }
        return res;
    }

    @SafeVarargs
    public static<T> BiDirList<T> of(T...array) {
        BiDirList<T> res = new BiDirList<>();
        for (T t : array) {
            res.addLast(t);
        }
        return res;
    }

    public void toArray(T[] array) throws Exception {
        if (array.length>=size) {
            BiDirListItem<T> cur = head;
            for (int i = 0; i < size; i++) {
                array[i] = cur.getItem();
                cur = cur.getNext();
            }
        } else {
            throw new Exception("Невозможно скопировать в массив меньшей длины!");
        }
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Iterator<T> iterator() {
        return new BiDirListIterator<>(head);
    }

    public static void main(String[] args) throws Exception {
        BiDirList<Integer> list = new BiDirList<>();
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
}
