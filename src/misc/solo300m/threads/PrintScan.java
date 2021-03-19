package misc.solo300m.threads;


public class PrintScan {

    //static Lock loc2 = new ReentrantLock();
    //static Lock loc = new ReentrantLock();
    public static Object sync1 = new Object();
    public static Object sync2 = new Object();
    static void print(String name, int pages){
        synchronized (sync1) {
            //loc2.lock();
            for (int i = 0; i < pages; i++) {

                System.out.println("print " + name + " page " + (i + 1));

            }
            //loc2.unlock();
        }

    }
    static void scan(String name, int pages){
        synchronized (sync1) {
        //    loc.lock();
            for (int i = 0; i < pages; i++) {

                System.out.println("scan " + name + " page " + (i + 1));

            }
        //    loc.unlock();
        }
    }
}
class Main{
    public static void main(String[] args) {

        for(int i = 0; i < 10; i++) {

            int finalI = i;
            Thread name = new Thread(new Runnable() {
                @Override
                public void run() {
                    PrintScan.print("doc "+ finalI, 3);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            name.start();
        }
        for(int i = 0; i < 10; i++) {

            int finalI = i;
            Thread name1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    PrintScan.scan("scanDoc2 "+finalI, 3);
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        return;
                    }
                }

            });
            name1.start();


        }
    }
}