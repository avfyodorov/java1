package ru.progwards.java1.lessons.interfaces;

import java.util.Objects;

public class Animal implements FoodCompare, CompareWeight
{

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Animal animal = (Animal) o;
    return Double.compare(animal.weight, weight) == 0;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(weight);
  }

  //  возвращает информацию о цене 1 кг еды.
//  метод реализовать в виде switch по FoodKind со следующими значениями
//  HAY : 20
//  CORN: 50
//  UNKNOWN: 0
  public double getFood1kgPrice()
  {
    switch (getFoodKind())
    {
      case HAY:
        return 20.0;
      case CORN:
        return 50.0;
      default:
        return 0.0;
    }
  }

  //добавить метод
//возвращает информацию о цене еды для данного животного по формуле
// calculateFoodWeight() * getFood1kgPrice()
  public double getFoodPrice()
  {
    return calculateFoodWeight() * getFood1kgPrice();
  }

  @Override
  public int compareFoodPrice(Animal animal)
  {
    return Double.compare(getFoodPrice(), animal.getFoodPrice());
  }

  @Override
  public CompareResult compareWeight(CompareWeight smthHasWeigt)
  {
    Animal prm = (Animal) smthHasWeigt;
    if (getWeight() < prm.getWeight())
      return CompareResult.LESS;
    else if (getWeight() > prm.getWeight())
      return CompareResult.GREATER;
    else
      return CompareResult.EQUAL;
  }

  public static void main(String[] args)
  {
    Animal a = new Animal(1.0);
    System.out.println(a.toStringFull());

    Animal c = new Cow(300.0);
    System.out.println(c.toStringFull());

    Animal h = new Hamster(0.2);
    Animal h1 = new Hamster(0.1);
    System.out.println(h.toStringFull());

    Animal d = new Duck(3.2);
    System.out.println(d.toStringFull());

    //ComplexNum cn=new ComplexNum(1,7);

    System.out.println(h.compareFoodPrice(d));
  }

  private double weight;

  public double getWeight()
  {
    return weight;
  }

  public double getFoodCoeff()
  {
    return 0.02;
  }

  public Animal(double weight)
  {
    this.weight = weight;
  }

  public AnimalKind getKind()
  {
    return AnimalKind.ANIMAL;
  }
  //возвращает вид животного (enum AnimalKind - ANIMAL, COW, HAMSTER, DUCK).
  //В данном классе вернуть ANIMAL

  public FoodKind getFoodKind()
  {
    return FoodKind.UNKNOWN;
  }
//  возвращает вид еды, (enum FoodKind - UNKNOWN, HAY, CORN). В данном классе вернуть UNKNOWN

  public String toStringFull()
  {
    return toString() + " " + calculateFoodWeight();
  }

  public double calculateFoodWeight()
  //рассчитывает необходимый вес еды, по формуле -
  // вес-еды = вес-животного * коэффициент веса тела.
  {
    return weight * getFoodCoeff();
  }

  @Override
  public String toString()
  //возвращает информацию о животном в формате:  I am <AnimalKind>, eat <FoodKind>
  {
    return "I am " + getKind() + ", eat " + getFoodKind();
  }

}

enum AnimalKind
{ANIMAL, COW, HAMSTER, DUCK}

enum FoodKind
{UNKNOWN, HAY, CORN}