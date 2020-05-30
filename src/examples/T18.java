package examples;

import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class T18
{
  static void E2()
  {
    String txt =
      "StringTokenizer - этот класс, " +
        "нам строку разобьёт на раз.";
    StringTokenizer tokenizer = new StringTokenizer(txt, " .,");
    while (tokenizer.hasMoreTokens())
      System.out.print(tokenizer.nextToken());
  }

  String swapWords(String sentance)
//который возвращает слова фразы из sentence через одно, начиная с первого,
// через пробел в виде строки. Разделители " .,-!\n"
//  Например, слова фразы "Слово - серебро, молчание - золото!"
//  должны быть преобразованы в  "серебро Слово золото молчание"
  {
    String res = "";
    StringTokenizer tokenizer = new StringTokenizer(sentance, " .,-!\n");
    while (tokenizer.countTokens() >= 2)
    {
      String s = tokenizer.nextToken();
      res = res + tokenizer.nextToken() + " " + s + " ";
    }

    if (tokenizer.countTokens() > 0)
      res = res + tokenizer.nextToken();

    return res.trim();
  }

  public static void main(String[] args)
  {
    T18.E2();
    System.out.println("\n--------------------");
    T18 m = new T18();
    System.out.println(m.swapWords("Слово - серебро, молчание - золото!abram"));

//тест 3 - форматирование
    System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);
  }
}

class Person
{
  public String name;
  public Date birth;
  public double salary;

  Person(String name, Date birth, double salary)
  {
    this.name = name;
    this.birth = birth;
    this.salary = salary;
  }

//, который выводит на консоль содержимое массива persons столбиком,
// одна строка - один человек, причем, каждая строка имеет вид:
//  |Вася      |01/01/1970|200 000,00|

//  1. Разделитель значений - "|"
//  2. Порядок вывода значений: name, birth, salary
//  3. Для имени name, ширина 10 символов, прижим влево
//  4. Для дня рождения birth формат "дд/мм/гггг"
//  5. Для зарплаты salary ширина 10 точность 2, и задать разделитель тысяч, в русской раскладке

  public static void printPersons(Person[] persons)
  {
    Locale locale = new Locale("ru");
    for (int i = 0; i < persons.length; i++)
      System.out.format(locale, "|%-10s|%2$td/%2$tm/%2$tY|%3$,10.2f|\n", persons[i].name, persons[i].birth, persons[i].salary);
  }

}