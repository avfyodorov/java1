package misc.kadyrovas.threads;

import java.math.BigInteger;

public class Summator{
    int count; // Количество потоков
    BigInteger result;

    public Summator(int count) {
        this.count = count;
        result = BigInteger.ZERO;
    }

    public synchronized void inc(BigInteger value){
        result = result.add(value);
    }

    class ThreadSum implements Runnable{
        BigInteger valueStart;
        BigInteger valueFinis;
        ThreadSum(BigInteger valueStart, BigInteger valueFinis){
            this.valueStart = valueStart;
            this.valueFinis = valueFinis;
        }

        @Override
        public void run() {
            do {
                inc(valueStart);
                valueStart = valueStart.add(BigInteger.ONE);
            } while (valueStart.compareTo(valueFinis) <= 0);
        }
    }


    public BigInteger sum(BigInteger number) throws InterruptedException {
        BigInteger delta;
        BigInteger valueLast = BigInteger.ZERO;
        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i ++) {
            delta = number.subtract(valueLast).divide(BigInteger.valueOf(count - i));
            threads[i] = new Thread( new ThreadSum(valueLast.add(BigInteger.ONE), valueLast.add(delta)));
            threads[i].start();
            valueLast = valueLast.add(delta);
        }

        for (int i = 0; i < count; i ++)
            threads[i].join();
        return result;
    }

    public static void main(String[] args) {
        misc.shurupinh.threads.Summator test = new misc.shurupinh.threads.Summator(100000);

        System.out.println("result = " + test.sum(new BigInteger("1356543")));
    }

//    public static void main(String[] args) throws InterruptedException {
//        BigInteger number = BigInteger.valueOf(14);
//        Summator summator = new Summator(3);
//        System.out.println(summator.sum(number));
//    }
}
