package misc.shurupinh.trees;


import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class AvlTest {
    static final int ITERATIONS = 100_000;
    public static void main(String[] args) throws TreeException{

        int [] arr = new int[ITERATIONS];

        TreeMap<Integer, String> map = new TreeMap<>();
        AvlTree tree = new AvlTree();

        long start, finis;
        int key = 0;
        boolean wasFound;

        for (int i = 0; i < ITERATIONS; i ++) {
            wasFound = true;
            while (wasFound) {
                wasFound = false;
                key = ThreadLocalRandom.current().nextInt();
                for (int k = 0; i < i; i ++)
                    if (arr[k] == key)
                        wasFound = true;
            }
            arr[i] = key;
        }

        //Вставка случайных чисел
        System.out.println("Тест на операцию: вставка случайных чисел");
        start = System.currentTimeMillis();

        for(int i=0; i<ITERATIONS; i++)
            tree.put(arr[i], "key=" + arr[i]);

        finis = System.currentTimeMillis();
        System.out.println("AvlTree: " + (finis - start) );

        start = System.currentTimeMillis();

        for(int i=0; i<ITERATIONS; i++)
                map.put(arr[i], "key=" + arr[i]);

        finis = System.currentTimeMillis();
        System.out.println("TreeMap: " + (finis - start) );

        System.out.println("------------------------");
        System.out.println("Тест на операцию: поиск:");

        start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i ++)
            tree.find(arr[i]);
        finis = System.currentTimeMillis();
        System.out.println("AvlTree: " + (finis - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i ++)
            map.get(arr[i]);
        finis = System.currentTimeMillis();
        System.out.println("TreeMap: " + (finis - start));

        System.out.println("------------------------");
        System.out.println("Тест на операцию удаления");

        start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++)
            tree.delete(arr[i]);

        finis = System.currentTimeMillis();
        System.out.println("AvlTree: " + (finis - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i ++)
            map.remove(arr[i]);

        finis = System.currentTimeMillis();
        System.out.println("TreeMap: " + (finis - start));
    }
}
