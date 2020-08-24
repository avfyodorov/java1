package ru.progwards.java2.lessons.examples.synchro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Deadlock
{
  public static void main(String[] args) throws InterruptedException
  {
    int ponder = 5;
    int size = 5;

    ExecutorService exec = Executors.newCachedThreadPool();

    Chopstick[] stick = new Chopstick[size];
    for (int i = 0; i < size; i++)
      stick[i] = new Chopstick();

//взаимные блокировки
//    for (int i = 0; i < size; i++)
//      exec.execute(new Philo(stick[i], stick[(i + 1) % size], i, ponder));

//все, кроме последнего, правая-левая, а последний - левая-правая
    for (int i = 0; i < size; i++)
      if (i < (size - 1))
        exec.execute(new Philo(stick[i], stick[i + 1], i, ponder));
      else
        exec.execute(new Philo(stick[0], stick[i], i, ponder));

    Thread.sleep(5000);
    System.out.println("=================================");
    exec.shutdownNow();
  }
}
