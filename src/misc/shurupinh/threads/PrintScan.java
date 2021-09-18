package misc.shurupinh.threads;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintScan {
    static int countP = 0;
    static int countS = 0;

    static Lock lockP = new ReentrantLock();
    static Lock lockS = new ReentrantLock();


    /*
    2.1 Метод static void print(String name, int pages) - "печатает" страницы документа с именем name - выводит на консоль

print <name> page 1
print <name> page 2
...
с интервалом в 50 мс. Вместо <name> выводится содержимое name. Пока один документ "печатается",
 второй не может быть напечатан
*/
    static void print(String name, int pages) {
        for (int i = 1; i <= pages; i++) {
            lockP.lock();
            System.out.println("print " + name + " page " + i);
            countP++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockP.unlock();

        }

    }

    /*

2.2 Метод static void scan(String name, int pages) - "сканирует" страницы документа с именем name - выводит на консоль

scan <name> page 1
scan <name> page 2
...
с интервалом в 70 мс. Вместо <name> выводится содержимое name. Пока один документ "сканируется",
второй не может быть отсканирован
*/
    static void scan(String name, int pages) {
        for (int i = 1; i <= pages; i++) {
            lockS.lock();
            System.out.println("scan " + name + " page " + i);
            countS++;
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockS.unlock();
        }

    }

    /*
2.3 Метод static void main(...)

Написать тест, который запускает на печатать параллельно 10 документов и запускает на сканирование еще 10 документов
параллельно. Потоки создает только main, print и scan содержат только синхронизацию.
     */
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            final int z = i;
            Thread tz = new Thread(new Runnable() {
                @Override
                public void run() {
                    scan(z + "doc", 5);

                }
            });
            tz.start();


        }
        for (int i = 1; i <= 10; i++) {
            final int z = i;
            Thread tz = new Thread(new Runnable() {
                @Override
                public void run() {
                    print(z + "doc", 5);

                }
            });
            tz.start();


        }

        try {
            Thread.currentThread().wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(countP + "+" + countS);
    }
}
