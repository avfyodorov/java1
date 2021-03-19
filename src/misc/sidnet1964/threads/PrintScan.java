package misc.sidnet1964.threads;

import java.math.BigInteger;

public class PrintScan {
    static BigInteger count1 = BigInteger.ZERO;
    static BigInteger count2 = BigInteger.ONE;
    static class DocPrin implements Runnable{
        String name;
        int pages;
        Object obj = new Object();
        DocPrin(String name, int pages) {
            this.name = name;
            this.pages = pages;
        }
        @Override
        public void run() {
//            count1++;      //  сначала все сканирование (первые страницы, вторые), затем печать - аналогично
            synchronized (count1) {
//                count1++;      //  сначала все сканирование (первые страницы, вторые), затем печать - подокументно
                print(this.name, this.pages);
//                count1++;   //  подокументная обработка от 1 до последней страницы каждого документа
            }
        }
    }

    static void print(String name, int pages){
        for (int i = 0; i < pages; i++){
            System.out.println("print "+ name + " page " + (i+1));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class DocScan implements Runnable {
        String name;
        int pages;
        Object obj = new Object();
        DocScan(String name, int pages) {
            this.name = name;
            this.pages = pages;
        }
        @Override
        public void run() {
//            count2++;      //  сначала все сканирование (первые страницы, вторые), затем печать - аналогично
            synchronized (count2) {
//                count2++;      //  сначала все сканирование (первые страницы, вторые), затем печать - подокументно
                scan(this.name, this.pages);
//                count2++;   //  подокументная обработка
            }
        }
    }

    static void scan(String name, int pages) {
        for (int i = 0; i < pages; i++) {
            System.out.println("scan " + name + " page " + (i + 1));
            try {
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int count = 4;
        for (int i = 0; i < count; i++) {
            new Thread(new DocScan("COD_" + i, 5)).start();
            new Thread(new DocPrin("DOC_" + i, 5)).start();
        }
        Thread.sleep(500);
    }
}
