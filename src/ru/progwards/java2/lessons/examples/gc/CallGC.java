package ru.progwards.java2.lessons.examples.gc;

import java.time.Instant;
import java.util.ArrayList;

public class CallGC
{
  static void printInfo(String info)
  {
    System.out.println(Instant.now());
    System.out.println(info);
    Runtime runtime=Runtime.getRuntime();
    System.out.println("total - "+runtime.totalMemory());
    System.out.println("max   - "+runtime.maxMemory());
    System.out.println("free  - "+runtime.freeMemory());
  }

  public static void main(String[] args)
  {
    printInfo("Start==");
    ArrayList<Integer>list=new ArrayList<>();
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 105_000_00; j++)
        list.add(j);

    printInfo("after create");
    list.clear();
    System.gc();
    printInfo("after GC");

  }
}
