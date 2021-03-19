package ru.progwards.java2.lessons.sort;
/*
import ru.progwards.java2.lessons.basetypes.BinaryHeap;
import ru.progwards.java2.lessons.gc.Heap;
import ru.progwards.java2.lessons.tree.BinaryTree;
import ru.progwards.java2.lessons.tree.TreeException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class SortTestStr {
    static final int COUNT = 1000;

    static String[] fill() {
        String line;
        List<String> strings = new ArrayList<>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader("wiki.train.tokens" ));
            while ((line = bufferreader.readLine()) != null) {
                String s[] = line.split("[ :\".,!<>{}=]");
                for (String str: s) {
                    str = str.trim();
                    if (str.length() > 0) {
                        strings.add(str);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String a[] = new String[strings.size()];
        return strings.toArray(a);
    }

    static void fill2(String[] a) {
        Arrays.fill(a, 1);
    }

    static String[] copy(String[] src) {
        String[] res = new String[src.length];
        for(int i=0; i < src.length; i++)
            res[i] = src[i];
        return res;
    }

    static void selection(String[] org) {
        String[] a = copy(org);
        long start = System.currentTimeMillis();
        SelectionSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("selection sort: "+sort);
    }

    static void heap(String[] org) {
        String[] a = copy(org);

        long start = System.currentTimeMillis();
        BinaryHeap<String> heap = BinaryHeap.from(BinaryHeap.Type.MIN_HEAP, a);
        heap.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("heap sort: "+sort);
    }

    static void tree(String[] org) throws TreeException {
        ArrayList<BinaryTree.TreeLeaf> sorted = new ArrayList<>(COUNT);

        long start = System.currentTimeMillis();
        BinaryTree<String, String> tree = new BinaryTree<>();
        tree.process(sorted::add);
        for(String n: org)
            tree.add(n, n);
        tree.process(sorted::add);
        long sort = System.currentTimeMillis()-start;
        System.out.println("tree sort: "+sort);
    }

    static void bubble(String[] org) {
        String[] a = copy(org);
        long start = System.currentTimeMillis();
        BubbleSort.sort(a);
        long sort = System.currentTimeMillis()-start;

        System.out.println("bubble sort: "+sort);
    }

    static void shaker(String[] org) {
        String[] a = copy(org);
        long start = System.currentTimeMillis();
        ShakerSort.sort(a);
        long sort = System.currentTimeMillis()-start;

        System.out.println("shaker sort: "+sort);
    }

    static void comb(String[] org) {
        String[] a = copy(org);
        long start = System.currentTimeMillis();
        CombSort.sort(a);
        long sort = System.currentTimeMillis()-start;

        System.out.println("comb sort: "+sort);
    }

    static void insertion(String[] org) {
        String[] a = copy(org);
        long start = System.currentTimeMillis();
        InsertionSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("insertion sort: "+sort);

//        long start = System.currentTimeMillis();
//        InsertionSort.sort2(a);
//        long sort2 = System.currentTimeMillis()-start;
//        System.out.println("insertion sort2: "+sort2);
    }

    static void quick(String[] org) {
        String[] a = copy(org);
//        long start = System.currentTimeMillis();
//        QuickSort.sort(a);
//        long sort = System.currentTimeMillis()-start;
//
//        a = copy(org);
        long start = System.currentTimeMillis();
        QuickSort.sort2(a);
        long sort2 = System.currentTimeMillis()-start;
        System.out.println("quick sort2: "+sort2);
        //System.out.println(Arrays.toString(a));
    }

    static void shell(String[] org) {
        String[] a = copy(org);
        long start = System.currentTimeMillis();
        ShellSort.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("shell sort: "+sort);
        //System.out.println(Arrays.toString(a));
    }

    static void arrays(String[] org) {
        String[] a = copy(org);
        long start = System.currentTimeMillis();

        Arrays.sort(a);
        long sort = System.currentTimeMillis()-start;
        System.out.println("arrays sort: "+sort);
    }

    public static void main(String[] args) throws TreeException {
        String[] org = fill();

        selection(org);
    }
}
*/