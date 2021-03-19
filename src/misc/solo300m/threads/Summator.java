package misc.solo300m.threads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Summator extends Thread {
    Thread[] threads;
    Lock lock = new ReentrantLock();
    private int count;
    List <Diapozon> diapozon;

    Summator(int count){
        this.count = count;
        threads = new Thread[count];
        diapozon = new ArrayList<>();
    }
    public BigInteger sum(BigInteger number){
        List<BigInteger>intSum = new ArrayList<>();
        for(int i = 0; i<count; i++){
            intSum.add(new BigInteger("0"));
        }
        BigInteger rez = new BigInteger(String.valueOf(0));
        BigInteger termin = new BigInteger(String.valueOf(0));
        if(count != 0){
            termin = number.divide(BigInteger.valueOf(count));
            for(int i = 0; i<count;i++){
                diapozon.add(new Diapozon());
                //System.out.println(diapozon.get(i).start+"  "+diapozon.get(i).finish);
            }
            diapozon.get(0).start = BigInteger.valueOf(0);
            diapozon.get(0).finish = diapozon.get(0).start.add(termin);
            System.out.println(diapozon.get(0).start+"  "+diapozon.get(0).finish);
        }
        for(int i = 1; i<diapozon.size(); i++){
            diapozon.get(i).start = diapozon.get(i-1).finish.add(BigInteger.valueOf(1));
            diapozon.get(i).finish = diapozon.get(i).start.add(termin.subtract(BigInteger.valueOf(1)));
            //System.out.println(diapozon.get(i).start+"  "+diapozon.get(i).finish);
        }

        for(int i = 0; i < this.count; i++){
            int ch = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    BigInteger j = new BigInteger(String.valueOf(diapozon.get(ch).start));

                    while (j.compareTo(diapozon.get(ch).finish)<=0){
                        intSum.set(ch,intSum.get(ch).add(j));
                        j = j.add(BigInteger.ONE);
                    }
                    System.out.println(intSum.get(ch)+" Поток "+Thread.currentThread());
                }
            });

        }
        for(int d = 0; d<count; d++){
            lock.lock();
            threads[d].start();
            try {
                threads[d].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
        for(int i = 0; i < intSum.size(); i++){
            rez = rez.add(intSum.get(i));
        }
        return rez;
    }
}
class Diapozon{
    public BigInteger start;
    public BigInteger finish;

    public Diapozon(){
        start = BigInteger.valueOf(0);
        finish = BigInteger.valueOf(0);
    }
}
class Main22{
    public static void main(String[] args) {
        Summator sum = new Summator(5);
        System.out.println(sum.sum(new BigInteger(String.valueOf(15))));
    }
}