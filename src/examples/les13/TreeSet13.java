package examples.les13;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSet13
{

  //output 985421
  public static void doTreeSet1() {
    TreeSet<Integer> treeSet = new TreeSet<>();
    treeSet.add(9);
    treeSet.add(1);
    treeSet.add(5);
    treeSet.add(2);
    treeSet.add(4);
    treeSet.add(8);

    String s = "";
    Iterator<Integer> iterator = treeSet.descendingIterator();
    while (iterator.hasNext())
      s += iterator.next();
    System.out.println(s);
  }

  public static void doTreeSet() {
    TreeSet<Integer> treeSet = new TreeSet<>();
    treeSet.add(19);
    treeSet.add(12);
    treeSet.add(15);
    treeSet.add(10);

    String s = "";
    Iterator<Integer> iterator = treeSet.iterator();
    while (iterator.hasNext())
      s += iterator.next();
    System.out.println(s);
  }
  public static void main(String[] args)
  {
    doTreeSet();
  }
}
