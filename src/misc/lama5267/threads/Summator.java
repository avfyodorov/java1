package misc.lama5267.threads;


import java.math.BigInteger;

public class Summator {

   static class MyThread extends Thread {
      int n;
      int s;
      int m;

      public MyThread(int n, int m) {
         this.n = n;
         this.m = m;
         this.s = 0;
      }


      @Override
      public void run() {
         for (int i = n; i <= m; i++) {
            s = s + i;
         }
      }
   }

   int count;
   MyThread[] threads;

   public Summator(int count) {
      this.count = count;
      threads = new MyThread[count];
   }

   public BigInteger sum(BigInteger number) {
      BigInteger c = new BigInteger(String.valueOf(count));
      BigInteger d = number.divide(c);
      BigInteger mod = number.mod(c);
      BigInteger n = BigInteger.ONE;
      BigInteger m = d;
      for (int i = 0; i < count - 1; i++) {
         threads[i] = new MyThread(n.intValue(), m.intValue());
         threads[i].start();
         n = n.add(d);
         m = m.add(d);
      }
      threads[count - 1] = new MyThread(n.intValue(), m.add(mod).intValue());
      threads[count - 1].start();
      BigInteger res = BigInteger.ZERO;
      for (int i = 0; i < count; i++) {
         try {
            threads[i].join();
            res = res.add(new BigInteger(String.valueOf(threads[i].s)));
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      return res;
   }

   public static void main(String[] args) {
      int num = 1356543;
      long l = 0;
      for (int i = 0; i <= num; i++) {
         l = l + i;
      }
      System.out.println("long   = " + l);

      int tr = 100_000;
      Summator test = new Summator(tr);
      System.out.println("result = " + test.sum(new BigInteger("1356543")) + "   потоков - " + tr);

      tr = 2;
      test = new Summator(tr);
      System.out.println("result = " + test.sum(new BigInteger("1356543")) + "   потоков - " + tr);

      //
//        Summator summator = new Summator(2);
//        System.out.println(summator.sum(BigInteger.valueOf(10)));
   }

//
//    public static void main(String[] args) {
//        Summator summator = new Summator(2);
//        System.out.println(summator.sum(BigInteger.valueOf(12)));
//    }
}
