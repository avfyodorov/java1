package ru.progwards.java2.lessons.recursion;

public class HanoiTower
{
  boolean on = false;
  int size;
  int pos;

  public HanoiTower(int size, int pos)
  {
    this.size = size;
    this.pos = pos;
    screen = makeScreen(size);
  }

  void moveTowers(int diskN, int from, int inter, int to)
  {
    if (diskN == 1)
    {
//      System.out.println("Disk 1 from " + from + " to " + to);
      pushRing(to,popRing(from));
      print();
    } else
    {
      moveTowers(diskN - 1, from, to, inter);
//      System.out.println("Disk " + diskN + " from " + from + " to " + to);
      pushRing(to,popRing(from));
      print();
      moveTowers(diskN - 1, inter, from, to);
    }
  }

  public void move(int from, int to)
  {
    int inter;
    if (from == 0)
      inter = to == 2 ? 1 : 2;
    else if (from == 1)
      inter = to == 2 ? 0 : 2;
    else
      inter = to == 0 ? 1 : 0;

    moveTowers(size, from, inter, to);
  }

  int calcIndex(int pin, int i)
  {
    return i * (WIDTH + 1) + pin * 6;
  }

  int calcTopRing(int pin)
  {
    int i = 0;
    while (i < size)
    {
      if (screen.charAt(calcIndex(pin, i)) != ' ')
        break;
      i++;
    }
    return i;
  }

  String popRing(int pin)
  {
    int i = calcTopRing(pin);
    int index = calcIndex(pin, i);

    StringBuilder sb = new StringBuilder(screen);
    String res = sb.substring(index, index+5);
    sb.delete(index, index + 5);
    sb.insert(index, PIN);
    screen = sb.toString();

    return res;
  }

  void pushRing(int pin, String ring)
  {
    int i = calcTopRing(pin) - 1;
    int index = calcIndex(pin, i);

    StringBuilder sb = new StringBuilder(screen);
    sb.delete(index, index + 5);
    sb.insert(index, ring);
    screen = sb.toString();
  }

  void setTrace(boolean on)
  {
    this.on = on;
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
    String s = "";
    for (int i = 1; i <= height; i++)
    {
      switch (pos)
      {
        case 0:
          s = s+ ringAsString(i) + " " + PIN + " " + PIN + "\n";
          break;
        case 1:
          s = s+PIN + " " + ringAsString(i) + " " + PIN + "\n";
          break;
        default:
          s = s+PIN + " " + PIN + " " + ringAsString(i) + "\n";
          break;
      }
    }
    return s;
  }

  public void print()
  {
    if (on)
    {
      System.out.print(screen);
      System.out.println("=".repeat(WIDTH) + "\n");
    }
  }

  public static void main(String[] args)
  {
    HanoiTower mane = new HanoiTower(3, 0);
    mane.setTrace(true);
    mane.print();
//    mane.setTrace(false);
    mane.move(0, 2);
    //    mane.print();
//    mane.pushRing(1, mane.popRing(0));
//    mane.print();
  }
}
