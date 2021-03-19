package ru.progwards.java2.lessons.sort;

public class ShakerSort {
    public static<T extends Comparable<T>> void sort(T[] a) {
        if (a.length == 0)
            return;

        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            for (int i = right; i > left; --i) {
                if (a[i-1].compareTo(a[i]) > 0) {
                    swap(a, i-1, i);
                }
            }
            left++;
            for (int i = left; i < right; ++i) {
                if (a[i] .compareTo(a[i+1]) > 0) {
                    swap(a, i, i+1);
                }
            }
            right--;
        }
    }

    static<T> void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static<T extends Comparable<T>> void sort2(T[] a) {
        if (a.length == 0)
            return;

        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            for (int i = right; i > left; --i) {
                //int j = i-1;
                if (a[i-1].compareTo(a[i]) > 0) {
                    T tmp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = tmp;
                }
            }
            left++;
            for (int i = left; i < right; ++i) {
                //int j = i + 1;
                if (a[i] .compareTo(a[i+1]) > 0) {
                    T tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;
                }
            }
            right--;
        }
    }
    public static<T extends Comparable<T>> void sort3(T[] a) {
        if (a.length == 0)
            return;

        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            for (int i = right; i > left; --i) {
                int j = i-1;
                if (a[i-1].compareTo(a[i]) > 0) {
                    T tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
            left++;
            for (int i = left; i < right; ++i) {
                int j = i + 1;
                if (a[i] .compareTo(a[j]) > 0) {
                    T tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
            right--;
        }
    }
}
