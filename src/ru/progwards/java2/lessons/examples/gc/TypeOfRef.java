package ru.progwards.java2.lessons.examples.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class TypeOfRef
{
  public static void main(String[] args)
  {
    SoftReference<StringBuilder>soft=new SoftReference<>(new StringBuilder("soft ref"));
    WeakReference<StringBuffer>weak=new WeakReference<>(new StringBuffer("weak ref"));
    System.out.println("soft.get="+soft.get());
    System.out.println("weak.get="+weak.get());
    System.gc();
    System.out.println("soft.get="+soft.get());
    System.out.println("weak.get="+weak.get());

  }
}
