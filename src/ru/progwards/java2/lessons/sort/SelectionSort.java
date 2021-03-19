package ru.progwards.java2.lessons.sort;

import java.util.Arrays;

public class SelectionSort {

    public static<T extends Comparable<T>> void sort(T[] a) {
        for (int i=0; i<a.length; i++)
            for(int j=i+1; j<a.length; j++)
                if (a[i].compareTo(a[j]) < 0) {
                    T tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
    }

    public static<T extends Comparable<T>> void sort2(T[] a) {
        for (int i=0; i<a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[min].compareTo(a[j]) < 0)
                    min = j;
            }
            if (min != i) {
                T tmp = a[i];
                a[i] = a[min];
                a[min] = tmp;
            }
        }
    }

    public static<T extends Comparable<T>> void sort3(T[] a) {
        for (int i=0; i<a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[min].compareTo(a[j]) < 0)
                    min = j;
            }
        T tmp = a[i];
        a[i] = a[min];
        a[min] = tmp;
        }
    }
}
