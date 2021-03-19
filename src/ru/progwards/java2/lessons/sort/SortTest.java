package ru.progwards.java2.lessons.sort;
/*
import ru.progwards.java2.lessons.basetypes.BinaryHeap;
import ru.progwards.java2.lessons.tree.BinaryTree;
import ru.progwards.java2.lessons.tree.TreeException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SortTest {
    public static final int COUNT = 10000;

    public static void fill(Integer[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < COUNT; i++) {
            int n;
            do {
                n = ThreadLocalRandom.current().nextInt();
            } while ( map.containsKey(n));
            a[i] = n;
            map.put(n,n);
        }
    }

    static void fill2(Integer[] a) {
        Arrays.fill(a, 1);
    }

    public static Integer[] copy(Integer[] src) {
        Integer[] res = new Integer[src.length];
        for(int i=0; i < src.length; i++)
            res[i] = src[i];
        return res;
    }

    static void selection(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        SelectionSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("selection sort: "+sort);
//
//        a = copy(org);
//        start = System.currentTimeMillis();
//        SelectionSort.sort2(a);
//        sort = System.currentTimeMillis()-start;
//        System.out.println("selection sort2: "+sort);
//
        a = copy(org);
        start = System.currentTimeMillis();
        SelectionSort.sort3(a);
        sort = System.currentTimeMillis()-start;
        System.out.println("selection sort3: "+sort);
    }

    static void heap(Integer[] org) {
        Integer[] a = copy(org);

        long start = System.currentTimeMillis();
        BinaryHeap<Integer> heap = BinaryHeap.from(BinaryHeap.Type.MIN_HEAP, a);
        heap.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("heap sort: "+sort);
    }

    static void tree(Integer[] org) throws TreeException {
        ArrayList<BinaryTree.TreeLeaf> sorted = new ArrayList<>(COUNT);

        long start = System.currentTimeMillis();
        BinaryTree<Integer, Integer> tree = new BinaryTree<>();
        for(Integer n: org)
            tree.add(n, n);
        tree.process(sorted::add);
        long sort = System.currentTimeMillis()-start;
        System.out.println("tree sort: "+sort);
    }

    static void bubble(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        BubbleSort.sort(a);
        long sort = System.currentTimeMillis()-start;

        System.out.println("bubble sort: "+sort);
    }

    static void shaker(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        ShakerSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("shaker sort: "+sort);

        a = copy(org);
        start = System.currentTimeMillis();
        ShakerSort.sort2(a);
        sort = System.currentTimeMillis()-start;
        System.out.println("shaker sort2: "+sort);

        a = copy(org);
        start = System.currentTimeMillis();
        ShakerSort.sort3(a);
        sort = System.currentTimeMillis()-start;
        System.out.println("shaker sort3: "+sort);
    }

    static void comb(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        CombSort.sort(a);
        long sort = System.currentTimeMillis()-start;

        System.out.println("comb sort: "+sort);
    }

    static void insertion(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        InsertionSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("insertion sort: "+sort);

        a = copy(org);
        start = System.currentTimeMillis();
        InsertionSort.sort2(a);
        sort = System.currentTimeMillis()-start;
        System.out.println("insertion sort2: "+sort);
    }

    static void quick(Integer[] org) {
//        Integer[] a = copy(org);
//        long start = System.currentTimeMillis();
//        QuickSort.sort(a);
//        long sort = System.currentTimeMillis()-start;
//        System.out.println("quick sort: "+sort);

        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        QuickSort.sort2(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("quick sort2: "+sort);
    }

    static void shell(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        ShellSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("shell sort: "+sort);
    }

    static void arrays(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();

        Arrays.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("arrays sort: "+sort);
    }

    static void megre(Integer[] org) {
        Integer[] a = copy(org);
        long start = System.currentTimeMillis();
        MergeSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("megre sort: "+sort);
    }

    public static void main(String[] args) throws TreeException {
        Integer[] org = new Integer[COUNT];
        fill(org);

        selection(org);
        heap(org);
        tree(org);
        bubble(org);
        shaker(org);
        comb(org);
        insertion(org);
        quick(org);
//        shell(org);
//        arrays(org);
//        megre(org);
    }
}
*/