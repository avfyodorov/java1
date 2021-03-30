package ru.progwards.java1.lessons.arrays;

//массив чисел, собственно, "решето"
//private boolean[] sieve, для того, чтобы узнать, я является ли число n простым,
// нужно взять значение по индексу в массиве. sieve[n].
// Значение true, означает простое число, false - составное.
//        1.2 Реализовать конструктор
//public Eratosthenes(int N), который должен разместить массив sieve с размером в N
// и заполнить его значениями true, после чего вызвать метод sift()
//        1.3 Реализовать метод
//private void sift(), который, собственно и реализует алгоритм Эратосфена,
// просеивая составные числа.
//        Подсказка - нужно реализовать 2 вложенных цикла, внешний,
//        например по i от 2 до N-1, и внутренний, например по j который будет просеивать числа,
//        кратные переменной внешнего цикла i*j.
//        1.4 Реализовать метод
//public boolean isSimple(int n), который возвращает sieve[n], что бы можно было узнать,
// простое число n или составное


import java.util.Arrays;

public class Eratosthenes
{
  private boolean[] sieve;

  public Eratosthenes(int N)
  {
    sieve = new boolean[N];
    Arrays.fill(sieve, true);
    sift();
  }

  private void sift()
  {
    sieve[0] = false;
    sieve[1] = false;
    for (int i = 2; i < sieve.length; ++i)
    {
      if (sieve[i])
      {
        for (int j = 2; i * j < sieve.length; ++j)
        {
          sieve[i * j] = false;
        }
     }
    }
  }

  public boolean isSimple(int n)
  {
    return n >= 0 && n < sieve.length ? sieve[n] : false;
  }

  public static void main(String[] args)
  {
    Eratosthenes era=new Eratosthenes(16);
    for (int i=0; i<era.sieve.length; i++)
      System.out.println(""+i+" : "+era.isSimple(i));
    System.out.println(Arrays.toString(era.sieve));
  }
}
