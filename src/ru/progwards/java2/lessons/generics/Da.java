package ru.progwards.java2.lessons.generics;

/**
 * Автор: Фёдоров Александр
 * Дата:  19.09.2023  20:07
 */
import java.util.Arrays;

public class Da<T> {

    private T[] array;
    private int count;

    public Da() {
        array = (T[]) new Object[1];
        count = 0;
    }

    public void add(T t){
        array[count++] = t;
        T[] copyArray = (T[])new Object[count * 2];
        array = Arrays.copyOf(array, copyArray.length);
    }

    public void insert(int pos, T t){
        int arrLength;
        if(pos < size())
            arrLength = size() + 1;
        else
            arrLength = pos + 1;
        T[] copyArray = (T[])new Object[arrLength];
        array  = Arrays.copyOf(array, copyArray.length + 1);
        System.arraycopy(array, pos, copyArray, pos + 1, arrLength - pos - 1);
        System.arraycopy(copyArray, pos, array, pos, copyArray.length - pos);
        array[pos] = t;
    }

    public void remove(int pos){
        if(pos == 0){
            T[] copyArray = (T[])new Object[size()];
            System.arraycopy(array, pos + 1, copyArray, pos, size() - 1);
            System.arraycopy(copyArray, pos, array, pos, copyArray.length);
            array  = Arrays. copyOf(array, copyArray.length - 2);
        } else {
            T[] copyArray = (T[])new Object[size() - 1];
            System.arraycopy(array, pos, copyArray, pos - 1, size() - pos);
            System.arraycopy(copyArray, pos, array, pos, copyArray.length - pos);
            array  = Arrays. copyOf(array, copyArray.length - 1);
        }
    }

    public T get(int pos){
        if(pos < count)
            return array[pos];
        return null;
    }

    public int size() {
        if(!array.equals(null))
            return array.length;
        return -1;
    }

    public static void main(String[] args) {
        Da<Integer> ints = new Da<>();
        System.out.println(ints.size());
        ints.add(3);
        System.out.println(ints.size());
        ints.add(5);
        System.out.println(ints.size());
        ints.add(4);
        System.out.println(ints.size());
//        System.out.println(Arrays.toString(ints.array));
//        ints.insert(0, 10);
//        System.out.println(Arrays.toString(ints.array));
//        ints.remove(0);
//        System.out.println(Arrays.toString(ints.array));
//        System.out.println(ints.size());
//        System.out.println(ints.get(2));
//        Da<String> strings = new Da<>();
//        strings.add("B");
//        strings.add("C");
//        strings.add("D");
//        System.out.println(Arrays.toString(strings.array));
//        strings.insert(0, "A");
//        System.out.println(Arrays.toString(strings.array));
//        strings.remove(2);
//        System.out.println(Arrays.toString(strings.array));
//        System.out.println(strings.size());
//        System.out.println(strings.get(2));
    }
}