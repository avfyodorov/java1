package misc.shurupinh.threads;
import java.math.BigInteger;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Summator {
    int count;
    static int recount;
    static BigInteger rezult = BigInteger.ZERO;

    static Lock lockS = new ReentrantLock();

    static long threadName;

    /*
    Реализовать класс Summator который суммирует все числа от 1 до number в несколько потоков.
     Например для числа 5 должно быть просуммировано 1+2+3+4+5

1.1 Конструктор Summator(int count) - инициализирует класс, с указанием в какое количество потоков надо будет проводить суммирование,
 count - количество потоков.
*/
    public Summator(int count) {
        this.count = count;
        recount = count;
    }

    /*
1.2 Метод public BigInteger sum(BigInteger number) - который, собственно и запускает потоки выполняющие суммирование,
number - число, до которого надо просуммировать числа. Для этого нужно будет разбить весь диапазон суммируемых чисел на блоки равного размера, по количеству потоков. Каждому потоку выдать блок для суммирования от n...m. Например, если мы суммируем 1000 в 3 потока, то первому достанется от 1 до 333 второму от 334 до 666, третьему от 667 до 1000.
После чего результат суммирования каждого блока нужно будет инкрементировать в общую сумму и вернуть как результат метода.
     */

    public BigInteger sum(BigInteger number) {
        BigInteger part = number.divide(BigInteger.valueOf(count));
        BigInteger remDiv = number.mod(BigInteger.valueOf(count));

        for (int i = 0; i < count; i++) {
            BigInteger str = (BigInteger.ONE).add(part.multiply(BigInteger.valueOf(i)));
            BigInteger end = part.add(part.multiply(BigInteger.valueOf(i)));
            if (i == count - 1) end = end.add(remDiv);

            var trd = new ThreadCalc(str, end);
            trd.start();
        }
        synchronized (Thread.currentThread()) {
            try {
                threadName = Thread.currentThread().getId();

                Thread.currentThread().wait();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return rezult;
    }

    static void addBI(BigInteger bi) {
        lockS.lock();
        rezult = rezult.add(bi);
        recount--;
        if (recount == 0) {// условие законченности
            // System.out.println("get up");

            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

            for (Thread x : threadSet) {
                if (x.getId() == threadName) {
                    //   System.out.println(Thread.currentThread().getId()+" **** "+threadName);
                    synchronized (x) {
                        x.notify();
                    }

                }

            }

        }


        lockS.unlock();
    }


/////////////////////////// T  R  E  A  D /////////////////////////


    static class ThreadCalc extends Thread {
        BigInteger start;
        BigInteger end;

        BigInteger rez;

        public ThreadCalc(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;

        }

        @Override
        public void run() {
            rez = start;
            BigInteger tmp = start;
            while (tmp.add(BigInteger.ONE).compareTo(end) <= 0) {

                tmp = tmp.add(BigInteger.ONE);
                rez = rez.add(tmp);

            }
            //System.out.print("*");
            addBI(rez);
        }
    }


    public static void main(String[] args) {
        Summator test = new Summator(100000);

        System.out.println("result = " + test.sum(new BigInteger("1356543")));
    }

}

