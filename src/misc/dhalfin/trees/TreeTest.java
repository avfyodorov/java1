package misc.dhalfin.trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TreeTest {
    static final int ITERATIONS = 50000;

    public static void main(String[] args) throws TreeException, FileNotFoundException {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        AvlTree<Integer, Integer> avlTree = new AvlTree<>();
        ArrayList<Integer> intList = new ArrayList<>();
        for (int i = 0; i < ITERATIONS; i++) {
            int key = ThreadLocalRandom.current().nextInt();
            if (!intList.contains(key))
                intList.add(key);
        }

        long start = System.currentTimeMillis();
        for (Integer key : intList) {
            map.put(key, key);
        }
        long stop = System.currentTimeMillis();
        System.out.println("TreeMap put: " + (stop - start));
        start = System.currentTimeMillis();
        for (Integer key : intList) {
            avlTree.put(key, key);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree put: " + (stop - start));
        System.out.println("----------------------");

        start = System.currentTimeMillis();
        for (Integer key : intList) {
            map.get(key);
        }
        stop = System.currentTimeMillis();
        System.out.println("TreeMap random find: " + (stop - start));
        start = System.currentTimeMillis();
        for (Integer key : intList) {
            avlTree.find(key);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree random find: " + (stop - start));
        System.out.println("----------------------");

        List<Integer> sortedIntList = new ArrayList<>(intList);
        sortedIntList.sort(Integer::compare);
        start = System.currentTimeMillis();
        for (Integer key : sortedIntList) {
            map.get(key);
        }
        stop = System.currentTimeMillis();
        System.out.println("TreeMap sorted find: " + (stop - start));
        start = System.currentTimeMillis();
        for (Integer key : sortedIntList) {
            avlTree.find(key);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree sorted find: " + (stop - start));
        System.out.println("----------------------");

        start = System.currentTimeMillis();
        for (Integer key : intList) {
            map.remove(key);
        }
        stop = System.currentTimeMillis();
        System.out.println("TreeMap random delete: " + (stop - start));
        start = System.currentTimeMillis();
        for (Integer key : intList) {
            avlTree.delete(key);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree random delete: " + (stop - start));
        System.out.println("----------------------");

        System.out.println("find & delete passed OK");
        avlTree.process(System.out::println);

        System.out.println("\nfrom file wiki.train.tokens\n");

        File file = new File("src\\misc\\dhalfin\\trees\\wiki.train.tokens");
        ArrayList<String> tokens = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            sc.useDelimiter("[^a-zA-Z]+");
            while (sc.hasNext()) {
                String str = sc.next();
                if (!tokens.contains(str))
                    tokens.add(str);
            }
        }

        TreeMap<String, String> strMap = new TreeMap<>();
        AvlTree<String, String> strAvlTree = new AvlTree<>();
        start = System.currentTimeMillis();
        for (String str : tokens) {
            strMap.put(str, str);
        }
        stop = System.currentTimeMillis();
        System.out.println("TreeMap put: " + (stop - start));
        start = System.currentTimeMillis();
        for (String str : tokens) {
            strAvlTree.put(str, str);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree put: " + (stop - start));
        System.out.println("----------------------");

        start = System.currentTimeMillis();
        for (String str : tokens) {
            strMap.get(str);
        }
        stop = System.currentTimeMillis();
        System.out.println("TreeMap random find: " + (stop - start));
        start = System.currentTimeMillis();
        for (String str : tokens) {
            strAvlTree.find(str);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree random find: " + (stop - start));
        System.out.println("----------------------");

        ArrayList<String> sortedTokens = new ArrayList<>(tokens);
        sortedTokens.sort(Comparator.naturalOrder());
        start = System.currentTimeMillis();
        for (String str : sortedTokens) {
            strMap.get(str);
        }
        stop = System.currentTimeMillis();
        System.out.println("TreeMap sorted find: " + (stop - start));
        start = System.currentTimeMillis();
        for (String str : sortedTokens) {
            strAvlTree.find(str);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree sorted find: " + (stop - start));
        System.out.println("----------------------");

        start = System.currentTimeMillis();
        for (String str : tokens) {
            strMap.remove(str);
        }
        stop = System.currentTimeMillis();
        System.out.println("TreeMap random delete: " + (stop - start));
        start = System.currentTimeMillis();
        for (String str : tokens) {
            strAvlTree.delete(str);
        }
        stop = System.currentTimeMillis();
        System.out.println("AvlTree random delete: " + (stop - start));
        System.out.println("----------------------");

        System.out.println("find & delete passed OK");
        avlTree.process(System.out::println);
    }
}
