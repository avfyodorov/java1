package ru.progwards.java2.lessons.sort;

public class InsertionSort {
    public static<T extends Comparable<T>> void sort(T[] a) {
        for (int j = 1; j <a.length; j++) {
            T cur = a[j];
            int i = j - 1;
            while (i >= 0 && a[i].compareTo(cur) > 0) {
                a[i + 1] = a[i];
                i--;
            }
            a[i+1] = cur;
        }
    }

    public static<T extends Comparable<T>> void sort2(T[] a) {
        final int l = a.length;
        for (int j = l - 1; j > 0; j--) {
            T cur = a[j];
            int i = j + 1;
            while (i < l && a[i].compareTo(cur) < 0)
                i++;
            if (i + 3 < l && i - j - 2 > 0) {
                System.arraycopy(a, j + 1, a, j, i - j - 2);
            }
            a[i - 1] = cur;
        }    }
}
