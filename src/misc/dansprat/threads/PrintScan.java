package misc.dansprat.threads;

import java.util.concurrent.locks.ReentrantLock;

public class PrintScan {


    private static ReentrantLock scanLock = new ReentrantLock();
    private static ReentrantLock printLock = new ReentrantLock();

    public static class Document{
        private int pages;
        private String name;

        public Document(String name, int pages) {
            this.name=name;
            this.pages=pages;
        }

        public int getPages() {
            return pages;
        }

        public String getName() {
            return name;
        }
    }

    public static class PrintThread extends Thread{
        Document [] documents;
        public PrintThread(Document ... documents){
            this.documents = new Document[documents.length];
            int i =0;
            for (Document var: documents){
               this.documents[i] = var;
               i++;
            }
        }
        @Override
        public void run() {
            for (var document:documents){
                print(document.name,document.pages);
            }
        }
    }

    public static class ScanThread extends Thread{
        Document [] documents;
        public ScanThread(Document ... documents){
            this.documents = new Document[documents.length];
            int i =0;
            for (Document var: documents){
                this.documents[i] = var;
                i++;
            }
        }
        @Override
        public void run() {
            for (var document:documents){
                scan(document.name,document.pages);
            }
        }
    }
    static void  print(String name, int pages){
            printLock.lock();
            for (int i = 1; i <= pages; i++) {
                System.out.println("print " + name + " page " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printLock.unlock();

    }

    static  void scan(String name, int pages){
            scanLock.lock();
            for (int i = 1; i <= pages; i++) {
                System.out.println("scan " + name + " page " + i);
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            scanLock.unlock();
    }

    public static void main(String[] args) {
        Thread thread1 = new PrintThread(new Document("Doc1",3),new Document("Doc2",3),new Document("Doc4",4));
        Thread thread2 = new PrintThread(new Document("Doc5",3),new Document("Doc6",3),new Document("Doc7",4),new Document("Doc8",5));
        Thread thread3 = new PrintThread(new Document("Doc3",5),new Document("Doc9",3),new Document("Doc10",2));

        Thread thread4 = new ScanThread(new Document("Doc11",3),new Document("Doc12",3),new Document("Doc13",4));
        Thread thread5 = new ScanThread(new Document("Doc14",3),new Document("Doc15",3),new Document("Doc16",4));
        Thread thread6 = new ScanThread(new Document("Doc17",3),new Document("Doc18",3),new Document("Doc19",4),new Document("Doc20",6));

        thread6.start();
        thread5.start();
        thread4.start();
        thread1.start();
        thread2.start();
        thread3.start();

    }

}
