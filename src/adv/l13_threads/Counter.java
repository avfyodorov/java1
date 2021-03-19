package adv.l13_threads;

public class Counter {
   public int getCount() {
      return count;
   }

   public void add() {
      synchronized (this) {
         count++;
      }
   }

   int count;

   public Counter(int count) {
      this.count = count;
   }
}

class IncCounter implements Runnable {

   private Counter counter;

   public IncCounter(Counter counter) {
      this.counter = counter;
   }

   @Override
   public void run() {
      for (int i = 0; i < 1000; i++) {
         counter.add();
      }
   }

   public static void main(String[] args) throws InterruptedException {
      Counter c = new Counter(0);
      Thread t1 = new Thread(new IncCounter(c));
      Thread t2 = new Thread(new IncCounter(c));
      Thread t3 = new Thread(new IncCounter(c));

      t1.start();
      t2.start();
      t3.start();

      t1.join();
      t2.join();
      t3.join();

      System.out.println(c.getCount());
   }
}