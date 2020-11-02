package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;

public class FruitBox<E> extends ArrayList
{
//  3. Обратить внимание на метод добавления фрукта в коробку.
//  Каждая коробка может содержать фрукты только одного типа, либо я блоки, либо апельсины.

  public  boolean addFruit(E o)
  {
    return super.add(o);
  }

//4.Сделать метод getWeight(), который высчитывает вес коробки.
// Задать вес одного фрукта: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
// Количество считаем как количество элементов массива.
//public double getWeight(){return size()>0? size()*(Fruit)(get(0)).getWeight():0;}

//  5. Реализовать метод moveTo, который позволяет пересыпать фрукты из текущей коробки в другую,
//  переданную в качестве параметра. Помним про сортировку фруктов:
//  нельзя яблоки высыпать в коробку с апельсинами. Если у нас фрукты одного типа,
//  то в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
//  В противном случае выдать исключение UnsupportedOperationException.
//
//  6. Реализовать метод сompareTo, который позволяет сравнить текущую коробку с переданной в качестве
//  параметра. Вернуть -1 – если их масса текущей меньше массы переданной параметром,
//  0 если их массы равны и 1 в противоположном случае.
//    Коробки с яблоками и апельсинами тоже можно сравнивать.


  public static void main(String[] args)
  {
    FruitBox<Orange> o=new FruitBox<>();
    o.addFruit(new Orange());
//    o.addFruit(new Apple());
    System.out.println("count="+o.size());
  }
}

abstract class Fruit{
  abstract public double getWeight();
}
class Orange extends Fruit{
  @Override
  public double getWeight()
  {
    return 8.12;
  }
}
class Apple extends Fruit{
  @Override
  public double getWeight()
  {
    return 3.62;
  }
}