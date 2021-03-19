package ru.progwards.java2.lessons.sort;

public class CombSort {
    public static <E extends Comparable<? super E>> void sort(E[] a) {
        int gap = a.length;
        boolean swapped = true;
        while (gap > 1 || swapped) {
            if (gap > 1)
                gap = (int) (gap / 1.247330950103979);

            int i = 0;
            swapped = false;
            while (i + gap < a.length) {
                if (a[i].compareTo(a[i + gap]) > 0) {
                    E t = a[i];
                    a[i] = a[i + gap];
                    a[i + gap] = t;
                    swapped = true;
                }
                i++;
            }
        }
    }
}
