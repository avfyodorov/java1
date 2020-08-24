package ru.progwards.java2.lessons.examples.synchro;

public class Chopstick
{
  private boolean taken=false;
  public synchronized void take() throws InterruptedException
  {
    while (taken)
      wait();
    taken=true;
  }
  public synchronized void drop()
  {
    taken=false;
    notifyAll();
  }
}
