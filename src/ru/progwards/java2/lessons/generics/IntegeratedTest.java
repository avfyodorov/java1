package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;

public class IntegeratedTest
{
//  Создайте статический метод с именем swap типа  void, который принимает параметром List,
//  обобщающего типа, и два int как индексы в списке. Реализовать метод,
//  который меняет элементы с указанными индексами местами.
public static <T> void swap(List<T> list, int x, int y){
  if (list==null || list.size()<=x || list.size()<=y)
    return;

  T tx=list.get(x);
  T ty=list.get(y);
  list.set(x,ty);
  list.set(y,tx);
}

  public static void main(String[] args)
  {
    ArrayList<Integer> list= new ArrayList<>();
    list.add(5);
    list.add(8);
    swap(list,0,1);
    list.forEach(System.out::println);

  }
}
