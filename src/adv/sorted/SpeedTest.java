package adv.sorted;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Автор: Фёдоров Александр
 * Дата:  30.05.2022  23:08
 */
public class SpeedTest {
    Set<Task> sortedSet = new TreeSet<>(Comparator.comparing(Task::getI));
    HashMap<Integer, Task> taskMap = new HashMap<>();

    int id = 0;
    static int COUNT = 1_000;

    void fillSetMy() {
        for (int i = 0; i < COUNT; i++) {
            sortedSet.add(new Task(id++));
        }
    }

    void fillMap() {
        for (int i = 0; i < COUNT; i++) {
            taskMap.put(id, new Task(id++));
        }
    }

    Set<Task> getPrioritizedTasksMy() {
        return sortedSet;
    }

    Set<Task> getPrioritizedTasksYour() {
        sortedSet.clear();
        sortedSet.addAll(taskMap.values());
        return sortedSet;
    }

    public static void main(String[] args) {
        SpeedTest mane = new SpeedTest();
        //заполняем
        mane.fillSetMy();

        //замеряем
        long start = System.currentTimeMillis();
        for (int i = 0; i < SpeedTest.COUNT; i++) {
            Set<Task> taskSet = mane.getPrioritizedTasksMy();
        }
        long stop = System.currentTimeMillis();
        System.out.println("MY total time: " + (stop - start));
//==================================

        mane.fillMap();

        start = System.currentTimeMillis();
        for (int i = 0; i < SpeedTest.COUNT; i++) {
            Set<Task> taskSet = mane.getPrioritizedTasksYour();
        }
        stop = System.currentTimeMillis();
        System.out.println("YOUR total time: " + (stop - start));

    }
}

class Task {
    int i;

    public int getI() {
        return i;
    }

    Task(int i) {
        this.i = i;
    }
}