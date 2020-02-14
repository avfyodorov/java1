package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

//import static ru.progwards.java1.lessons.interfaces.CompareResult.GREATER;

public class Food implements CompareWeight
{
  private int weight; //вес еды в граммах,

  Food(int weight)
  {
    this.weight = weight;
  }

  public int getWeight()
  {
    return weight;
  }

  @Override
  public String toString()
  {
    return String.valueOf(weight);
  }

  @Override
  public CompareResult compareWeight(CompareWeight smthHasWeigt)
  {
    Food food = (Food) smthHasWeigt;
    if (getWeight() < food.getWeight())
      return CompareResult.LESS;
    else if (getWeight() > food.getWeight())
      return CompareResult.GREATER;
    else
      return CompareResult.EQUAL;
  }

  public static void sort(CompareWeight[] a)
  {
    CompareWeight z;

    for (int i = 0; i < a.length; i++)
      for (int j = i + 1; j < a.length; j++)
      {
        Food food = (Food) a[i];
        if (food.compareWeight(a[j]) == CompareResult.GREATER)
        {
          z = a[i];
          a[i] = a[j];
          a[j] = z;
        }
      }
  }


  public static void main(String[] args)
  {
    System.out.println(new Food(5).compareWeight(new Food(8)));

    Food[] arr = {
            new Food(59),
            new Food(19),
            new Food(60),
            new Food(5),
            new Food(8),
    };

    System.out.println(Arrays.deepToString(arr));
    Food.sort(arr);
    System.out.println(Arrays.deepToString(arr));

  }
}

