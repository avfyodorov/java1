 package ru.progwards.java1.lessons.classes;

public class Animal
{
  public static void main(String[] args)
  {
    Animal a = new Animal(1.0);
    System.out.println(a.toStringFull());

    Animal c = new Cow(300.0);
    System.out.println(c.toStringFull());

    Animal h = new Hamster(0.2);
    System.out.println(h.toStringFull());

    Animal d = new Duck(4.0);
    System.out.println(d.toStringFull());
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