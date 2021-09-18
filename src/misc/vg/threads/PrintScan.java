package misc.vg.threads;

public class PrintScan {
    public static final Integer t1 = 50;
    public static final Integer t2 = 70;
    public static Integer p = 1;

    public static void print(String name, int pages) {
        synchronized (t1) {
            for (int i = 1; i <= pages; i++) {
                System.out.println("print " + name + " page " + i);
                try {
                    Thread.sleep(t1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void scan(String name, int pages) {
        synchronized (t2) {
            for (int i = 1; i <= pages; i++) {
                System.out.println("scan " + name + " page " + i);
                try {
                    Thread.sleep(t2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<10; i++) {
            Thread thread1 = new Thread(() -> print("name " + p++, 4));
            thread1.start();
            //thread1.join();

            //Thread thread2 = new Thread(() -> scan("name " + p++, 2));
            //thread2.start();
            //thread2.join();
        }
        for (int i=0; i<10; i++) {
            Thread thread2 = new Thread(() -> scan("name " + p++, 3));
            thread2.start();
        }
    }
}
