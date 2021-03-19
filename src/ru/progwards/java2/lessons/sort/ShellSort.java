package ru.progwards.java2.lessons.sort;

public class ShellSort {
    static<T extends Comparable<T>> void insertion(T[] a, int start, int step)
    {
        for (int i = start; i < a.length - 1; i += step) {
            for (int j = Math.min(i + step, a.length - 1); j - step >= 0; j = j - step)
                if (a[j - step].compareTo(a[j]) > 0) {
                    T tmp = a[j];
                    a[j] = a[j - step];
                    a[j - step] = tmp;
                } else break;
        }
    }

    public static<T extends Comparable<T>> void sort(T[] a) {
        for (int step = a.length / 2; step >= 1; step = step / 2)
            for (int i = 0; i < step; i++)
                insertion(a, i, step);
    }

}
