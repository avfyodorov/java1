package ru.progwards.java2.lessons.examples.synchro;

import java.util.Random;

public class Philo implements Runnable
{
  private Chopstick left;
  private Chopstick right;
  private final int id;
  private final int ponderFactor;
  private Random rand = new Random(47);

  private void pause() throws InterruptedException
  {
    if (ponderFactor == 0) return;
    Thread.sleep(rand.nextInt(ponderFactor * 250));
  }

  public Philo(Chopstick left, Chopstick right, int id, int ponderFactor)
  {
    this.left = left;
    this.right = right;
    this.id = id;
    this.ponderFactor = ponderFactor;
  }

  @Override
  public String toString()
  {
    return "Philo{" + "id=" + id + '}';
  }

  public void print(String txt)
  {
    System.out.println(this + " " + txt);
  }

  @Override
  public void run()
  {
    try
    {
      while (!Thread.interrupted())
      {
        print("thinking");
        pause();

        print("take right Fork");
        right.take();

        print("take left Fork");
        left.take();

        print("eating");
        pause();
        right.drop();
        left.drop();

        Thread.interrupted();
      }
    } catch (InterruptedException e)
    {
      print("exiting via interrupt");
    }

  }
}
