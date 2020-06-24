package ru.progwards.java2.lessons.recursion;

public class HanoiTower
{
  boolean flTrace = false;
  int size;
  int pos;

  public HanoiTower(int size, int pos)
  {
    this.size = size;
    this.pos = pos;
    screen = makeScreen(size);
  }

  public void move(int from, int to)
  {
  }

  int calcIndex(int pin, int i)  {return i * (WIDTH + 1) + pin * 6;}
  int calcTopRing(int pin)
  {
    int i=0;
    while (i < size)
    {
      if (screen.charAt(calcIndex(pin,i)) != ' ')
        break;
      i++;
    }
    return i;
  }

  String popRing(int pin)
  {
    int i=calcTopRing(pin);
    int index=calcIndex(pin,i);

    StringBuilder sb=new StringBuilder(screen);
    String res=sb.substring(index,5);
    sb.delete(index,index+5);
    sb.insert(index,PIN);
    screen=sb.toString();

    return res;
  }

  void pushRing(int pin, String ring)
  {
    int i=calcTopRing(pin)-1;
    int index=calcIndex(pin,i);

    StringBuilder sb=new StringBuilder(screen);
    sb.delete(index,index+5);
    sb.insert(index,ring);
    screen=sb.toString();
  }

  void setTrace(boolean on)
  {
    flTrace = on;
  }

  final String PIN = "  I  ";
  final int WIDTH = 17;
  String screen;

  String ringAsString(int n)
  {
    String s = String.valueOf(n);
    return "<" + "0".repeat(3 - s.length()) + s + ">";
  }

  String makeScreen(int height)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= height; i++)
    {
      sb.append(ringAsString(i)).append(' ').append(PIN).append(' ').append(PIN).append('\n');
    }

    sb.append("=".repeat(WIDTH)).append('\n');
    return sb.toString();
  }

  public void print()
  {
    System.out.println(screen);
  }

  public static void main(String[] args)
  {
    HanoiTower mane = new HanoiTower(3, 0);
    mane.print();
    mane.pushRing(1,mane.popRing(0));
    mane.print();
  }
}
