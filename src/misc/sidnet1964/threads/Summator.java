package misc.sidnet1964.threads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Summator {
    int count;
    BigInteger[] rezult;    //  массив для итогов по потокам

    public Summator(int count) {
        this.count = count;
        rezult = new BigInteger[count];    //  массив для итогов по потокам
    }
    //  --------------------------------
    //  класс для создания потока
    class MyCounter implements Runnable {
        private final int section;
        private final BigInteger beg; //  начало интервала
        private final BigInteger end; //  конец интервала
        private BigInteger rez;

        public MyCounter(int section, BigInteger beg, BigInteger end) {
            this.section = section;
            this.beg = beg;
            this.end = end;
            rez = BigInteger.ZERO;
        }

        @Override
        public void run() {
            for (BigInteger i = beg; i.compareTo(end) <= 0;) {
                rez = rez.add(i);
                //  счетчик цикла вынесен отдельно
                i = i.add(BigInteger.ONE);
            }
            rezult[section] = rez;
        }
    }
    //  --------------------------------
    public BigInteger sum(BigInteger number) throws InterruptedException {
//        long n = number.longValue() / count;  //  количество итераций в одном потоке
        BigInteger nn = number.divide(BigInteger.valueOf(count));   //возвращает частное двух чисел
        BigInteger mm = number.mod(BigInteger.valueOf(count));      //остаток от деления двух чисел
        List<Thread> threads = new ArrayList<>();

        for (int l = 0; l<count; l++) {
            if (l == count-1)
                //  конец интервала - number
                threads.add(l, new Thread(new MyCounter(l, nn.multiply(BigInteger.valueOf(l)).add(BigInteger.ONE), number)));
            else
                threads.add(l, new Thread(new MyCounter(l, nn.multiply(BigInteger.valueOf(l)).add(BigInteger.ONE), nn.multiply(BigInteger.valueOf(l+1)))));
            threads.get(l).start();
        }
        for (int l = 0; l<count; l++) {
            threads.get(l).join();
        }

        BigInteger itog = BigInteger.ZERO;
        for (int l = 0; l<count; l++) {
//            System.out.println("l = " + l + " -> " + rezult[l]);
            itog = itog.add(rezult[l]);
        }
        return itog;
    }

    public static void main(String[] args) throws InterruptedException {
        Summator summator = new Summator(33);
        BigInteger itog = BigInteger.ZERO;
        BigInteger pred = BigInteger.valueOf(1000);//Integer.MAX_VALUE / 2);    //  (long)
        long start = System.currentTimeMillis();

        itog = summator.sum(pred);

        System.out.println(pred + ";" + itog + ";" + (System.currentTimeMillis() - start));
    }
}
