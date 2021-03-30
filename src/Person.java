public class Person
{
  private String name;
  private int age;
  private String country;

  Person()
  {
    country = "RU";
  }

  Person(String name, int age)
  {
    this();
    this.name = name;
    this.age = age;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  public String getCountry()
  {
    return country;
  }

  @Override
  public String toString() {
    return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", country='" + country + '\'' +
            '}';
  }

  public static void main(String[] args) {
    Person p=new Person("OCTOPUS", 61);
    System.out.println(p);
  }
}

