package examples.les09;

public class Person
{
  public String name;
  public Person(String name) {
    this.name = name;
  }

  public static void main(String[] args)
  {
//    Напишите фрагмент кода, в котором создайте переменную класса
//    PersonCompare personCompare и проинициализируйте ее анонимным классом,
//    который перекрывает метод compare реализуя его через p1.name.compareTo(p2.name)

    PersonCompare personCompare = new PersonCompare()
    {
      @Override
      public int compare(Person p1, Person p2)
      {
        return p1.name.compareTo(p2.name);
      }
    };
  }
}
abstract class PersonCompare {
  public int compare(Person p1, Person p2) {
    return 0;
  }
}

