package misc.lama5267.threads;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintScan {
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    static void print(String name, int pages) {
        lock1.lock();
        try {
            if (pages <= 0) {
                System.out.println("Документ пустой");
                return;
            }
            for (int i = 1; i <= pages; i++) {
                System.out.println("print " + name + " page " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }

    static void scan(String name, int pages) {
        lock2.lock();
        try {
            if (pages <= 0) {
                System.out.println("Документ пустой");
                return;
            }
            for (int i = 1; i <= pages; i++) {
                System.out.println("scan " + name + " page " + i);
                Thread.sleep(70);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
        Thread [] threads = new Thread[20];
        for (int i = 0; i<20; i++) {
            int finalI = i+1;
            if (i<10)
                threads[i] = new Thread(() -> print("файл" + finalI, 3 + finalI));
            else
                threads[i] = new Thread(() -> scan("файл" + finalI, -7 + finalI));
        }
        for (int i = 0; i<20; i++) {
            threads[i].start();
        }
    }
}