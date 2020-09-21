package examples;

import java.util.Optional;

class Person1
{
  private Car1 car1;
  public Car1 getCar1()
  {
    return car1;
  }
}

class Person2
{
  private Optional<Car2> car2;
  public Optional<Car2> getCar2()
  {
    return car2;
  }
}

class Car1
{
  private Insurance insurance;
  public Insurance getInsurance()
  {
    return insurance;
  }
}

class Car2
{
  private Optional<Insurance> insurance;
  public Optional<Insurance> getInsurance()
  {
    return insurance;
  }
}


class Insurance
{
  private String name;

  public String getName()
  {
    return name;
  }
}

public class OptionalTest
{
  /*
  Optional<String> o = null;

  public Optional<String> getO()
  {
    return o;
  }

  public String getCarInsuranceName1(Person1 person1)
  {
    return person1.getCar().getInsurance().getName();
  }

  public String getCarInsuranceName2(Person1 person1) throws NullPointerException
  {
    return person1.getCar().getInsurance().getName();
  }

  public String getCarInsuranceName3(Person1 person1)
  {
    if (person1 != null)
    {
      Car1 car1 = person1.getCar();
      if (car1 != null)
      {
        Insurance insurance = car1.getInsurance();
        if (insurance != null)
          return insurance.getName();
      }
    }

    return "Неопределено";
  }

  public String getCarInsuranceName4(Person1 person1)
  {
    if (person1 == null)
      return "Неопределено";

    Car1 car1 = person1.getCar();
    if (car1 == null)
      return "Неопределено";

    Insurance insurance = car1.getInsurance();
    if (insurance == null)
      return "Неопределено";

    return insurance.getName();
  }

  public String getCarInsuranceName5(Person2 person2)
  {
    return person2.getCar().getInsurance().getName();
  }

  public static void main(String[] args)
  {
    OptionalTest tst = new OptionalTest();
    System.out.println(tst.getO());

    try
    {
      System.out.println(tst.getCarInsuranceName1(new Person1()));
    } catch (NullPointerException e)
    {
      System.out.println("qyqy");
    }
  }
  */
}
