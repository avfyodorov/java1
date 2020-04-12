package examples.les8;

public class Test8_2 implements Speaking,Eating
{
  @Override
  public String eat()
  {
    return "Гав";
  }

  @Override
  public String say()
  {
    return "";
  }
}


//реализовать 2 класса, Dog и Goat.
//        У класса Dog метод say() должен вернуть "Гав"
//        У класса Dog метод eat() должен вернуть "Мясо"
//        У класса Goat метод say() должен вернуть "Мее"
//        У класса Goat метод eat() должен вернуть "Сено"
//

