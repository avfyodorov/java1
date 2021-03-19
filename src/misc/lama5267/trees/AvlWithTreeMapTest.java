package misc.lama5267.trees;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import static java.nio.file.Files.readString;

public class AvlWithTreeMapTest {
    static final int ITERATIONS = 200000;
    long lstart, lstop, result1, result2;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    TreeMap<String, String> map1 = new TreeMap<>();
    AvlTree<Integer, Integer> avl = new AvlTree<>();
    AvlTree<String, String> avl1 = new AvlTree<>();
    ArrayList<Integer> list = new ArrayList<>();


    public void addSort () throws TreeException {
        System.out.println("Вставка " + ITERATIONS + " элементов");
        lstart = System.currentTimeMillis();
        for (int i=0; i <= ITERATIONS; i++) {
            avl.put(i,i);
        }
        lstop = System.currentTimeMillis();
        System.out.println("avl - " + (lstop-lstart));

        lstart = System.currentTimeMillis();
        for (int i=0; i <= ITERATIONS; i++) {
            map.put(i, i);
        }
        lstop = System.currentTimeMillis();
        System.out.println("map - " + (lstop-lstart));
    }

    public void findSort (int step) throws TreeException {
        System.out.println("Поиск каждого " + step + "-го элемента");

        lstart = System.currentTimeMillis();
        for (int i=0; i <= ITERATIONS; i+=step) {
            avl.find(i);
        }
        lstop = System.currentTimeMillis();
        System.out.println("avl - " + (lstop-lstart));

        lstart = System.currentTimeMillis();
        for (int i=0; i <= ITERATIONS; i+=step) {
            map.get(i);
        }
        lstop = System.currentTimeMillis();
        System.out.println("map - " + (lstop-lstart));
    }

    public void delSort (int step) throws TreeException {
        System.out.println("Удаление каждого " + step + "-го элемента");

        lstart = System.currentTimeMillis();
        for (int i=0; i <= ITERATIONS; i+=step) {
            avl.delete(i);
        }
        lstop = System.currentTimeMillis();
        System.out.println("avl - " + (lstop-lstart));

        lstart = System.currentTimeMillis();
        for (int i=0; i <= ITERATIONS; i+=step) {
            map.remove(i);
        }
        lstop = System.currentTimeMillis();
        System.out.println("map - " + (lstop-lstart));
    }

    public void addRandom (int less) throws TreeException {
        map.clear();
        avl = new AvlTree<>();
        System.out.println("Вставка " + ITERATIONS/less + " итераций (в " + less + " раз меньше сортированных)");
        for (int i=0; i <= ITERATIONS/less; i++) {
            int key = ThreadLocalRandom.current().nextInt();
            if (!map.containsKey(key)) {
                lstart = System.currentTimeMillis();
                avl.put(key,key);
                lstop = System.currentTimeMillis();
                result1 += lstop-lstart;

                lstart = System.currentTimeMillis();
                map.put(key, key);
                lstop = System.currentTimeMillis();
                result2 += lstop-lstart;

                list.add(key);
            }
        }
        System.out.println("avl - " + result1);
        System.out.println("map - " + result2);
    }

    public void findRandom () throws TreeException {
        result1 = 0;
        result2 = 0;
        System.out.println("Поиск " + list.size() + " значений");
        for (int key : list) {
            lstart = System.currentTimeMillis();
            avl.find(key);
            lstop = System.currentTimeMillis();
            result1 += lstop - lstart;

            lstart = System.currentTimeMillis();
            map.get(key);
            lstop = System.currentTimeMillis();
            result2 += lstop - lstart;
        }
        System.out.println("avl - " + result1);
        System.out.println("map - " + result2);
    }

    public void delRandom () throws TreeException {
        result1 = 0;
        result2 = 0;
        System.out.println("Удаление " + list.size() + " значений");
        for (int key : list) {
            lstart = System.currentTimeMillis();
            avl.delete(key);
            lstop = System.currentTimeMillis();
            result1 += lstop - lstart;

            lstart = System.currentTimeMillis();
            map.remove(key);
            lstop = System.currentTimeMillis();
            result2 += lstop - lstart;
        }
        System.out.println("avl - " + result1);
        System.out.println("map - " + result2);
    }

    public void addStr (String [] strArr) throws TreeException {
        map.clear();
        System.out.println("Вставка" );
        for (String s : strArr) {
            if (!map1.containsKey(s)) {
                lstart = System.currentTimeMillis();
                avl1.put(s, s);
                lstop = System.currentTimeMillis();
                result1 += lstop - lstart;

                lstart = System.currentTimeMillis();
                map1.put(s, s);
                lstop = System.currentTimeMillis();
                result2 += lstop - lstart;
            }
        }
        System.out.println("avl - " + result1);
        System.out.println("map - " + result2);
    }

    public void findStr (String [] strArr) throws TreeException {
        result1 = 0;
        result2 = 0;
        System.out.println("Поиск слов");
        for (String key : strArr) {
            if (map1.containsKey(key)) {
                lstart = System.currentTimeMillis();
                avl1.find(key);
                lstop = System.currentTimeMillis();
                result1 += lstop - lstart;

                lstart = System.currentTimeMillis();
                map1.get(key);
                lstop = System.currentTimeMillis();
                result2 += lstop - lstart;
            }
        }
        System.out.println("avl - " + result1);
        System.out.println("map - " + result2);
    }

    public void delStr (String [] strArr) throws TreeException {
        result1 = 0;
        result2 = 0;
        System.out.println("Удаление слов");
        for (String key : strArr) {
            if (map1.containsKey(key)) {
                lstart = System.currentTimeMillis();
                avl1.delete(key);
                lstop = System.currentTimeMillis();
                result1 += lstop - lstart;

                lstart = System.currentTimeMillis();
                map1.remove(key);
                lstop = System.currentTimeMillis();
                result2 += lstop - lstart;
            }
        }
        System.out.println("avl - " + result1);
        System.out.println("map - " + result2);
    }






    public static void main(String[] args) throws TreeException {
        AvlWithTreeMapTest avlTest = new AvlWithTreeMapTest();
        System.out.println("Отсортированные данные:");
        avlTest.addSort();
        avlTest.findSort(50);
        avlTest.delSort(50);
        System.out.println();
        System.out.println("Случайные данные:");
        try {
            avlTest.addRandom(5);
            avlTest.findRandom();
            avlTest.delRandom();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println();
        String [] strArr = {"A"};
        try {
            String str = readString(Paths.get("src/wiki.train.tokens"));
            strArr = str.split("[^A-Za-zА-Яа-я]+");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Данные из wiki.train.tokens");
        avlTest.addStr(strArr);
        avlTest.findStr(strArr);
        avlTest.delStr(strArr);
    }
}
