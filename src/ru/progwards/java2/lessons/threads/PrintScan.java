package ru.progwards.java2.lessons.threads;

import java.util.concurrent.locks.ReentrantLock;

public class PrintScan {
   static ReentrantLock lock = new ReentrantLock();
   final static int N_THREADS = 10;
   final static int N_PAGES = 5;

   static class MyScan implements Runnable {
      int num;

      public MyScan(int num) {
         this.num = num;
      }

      @Override
      public void run() {
         scan("scan <SCAN " + num + "> ", N_PAGES);
      }
   }

   static class MyPrint implements Runnable {
      int num;

      public MyPrint(int num) {
         this.num = num;
      }

      @Override
      public void run() {
         print("print <PRINT " + num + "> ", N_PAGES);
      }
   }

   static void scan(String name, int pages) {
      try {
         lock.lock();
         for (int i = 1; i <= pages; i++) {
            System.out.println(name + i);
            Thread.sleep(70);
         }
         lock.unlock();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   static void print(String name, int pages) {
      try {
         lock.lock();
         for (int i = 1; i <= pages; i++) {
            System.out.println(name + i);
            Thread.sleep(50);
         }
         lock.unlock();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      for (int i = 1; i <= N_THREADS; i++) {
         new Thread(new MyScan(i)).start();
         new Thread(new MyPrint(i)).start();
      }

   }
}
