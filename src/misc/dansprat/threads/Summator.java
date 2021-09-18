package misc.dansprat.threads;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;

public class Summator {

    private class PreSummator extends Thread{
        CountDownLatch countDownLatch;
        private BigInteger begin;
        private BigInteger end;
        private BigInteger result;

        public BigInteger getResult() {
            return result;
        }

        public PreSummator(BigInteger begin, BigInteger end,CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
            this.begin=begin;
            this.end=end;
        }
        @Override
        public void run() {
           result = BigInteger.ZERO;
           for (BigInteger b =begin;b.compareTo(end)<=0;b = b.add(BigInteger.ONE)){
               result = result.add(b);
           }
           countDownLatch.countDown();
        }
    }
    private int threads;
    public Summator(int count){
        threads = count;
    }

    public BigInteger sum(BigInteger number){
        BigInteger section = number.divide(new BigInteger(String.valueOf(threads)));
        BigInteger mod = number.mod(new BigInteger(String.valueOf(threads)));
        BigInteger current = BigInteger.ZERO;
        PreSummator [] summators = new PreSummator[threads];
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i =1;i<=threads;i++){
            PreSummator preSummator;
            if (i!=threads){
                preSummator = new PreSummator(current.add(BigInteger.ONE),current.add(section),countDownLatch);
            } else {
                preSummator = new PreSummator(current.add(BigInteger.ONE),current.add(section.add(mod)),countDownLatch);
            }
            summators[i-1] =preSummator;
            current = current.add(section);
            preSummator.start();
        }
        BigInteger result = BigInteger.ZERO;
        try {
            countDownLatch.await();
            for (PreSummator summator:summators){
                result = result.add(summator.result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        Summator summator =new Summator(4);
//        System.out.println(summator.sum(new BigInteger("1000000")));
//    }
    public static void main(String[] args) {
        Summator test = new Summator(100000);
        System.out.println("result = " + test.sum(new BigInteger("1356543")));
    }

}
