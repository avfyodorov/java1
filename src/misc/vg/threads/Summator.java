package misc.vg.threads;

import java.math.BigInteger;

public class Summator {
    static class MyThread extends Thread {
        int n;
        int m;
        long s;

        MyThread(int n, int m) {
            this.n = n;
            this.m = m;
            this.s = 0l;
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

    Summator(int count) {
        this.count = count;
        threads = new MyThread[count];
    }

    public BigInteger sum(BigInteger number) {
        BigInteger c = new BigInteger(String.valueOf(count));
        BigInteger d = number.divide(c);
        BigInteger mod = number.mod(c);
        BigInteger n = BigInteger.ONE;
        BigInteger m = d;
        for (int i = 0; i < count-1; i++) {
            threads[i] = new MyThread(n.intValue(), m.intValue());
            threads[i].start();
            n = n.add(d);
            m = m.add(d);
        }
        threads[count-1] = new MyThread(n.intValue(), m.add(mod).intValue());
        threads[count-1].start();

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
        System.out.println("result = " + test.sum(BigInteger.valueOf(num)) + "   потоков - " + tr);

        tr = 2;
        test = new Summator(tr);
        System.out.println("result = " + test.sum(BigInteger.valueOf(num)) + "   потоков - " + tr);
    }
}
