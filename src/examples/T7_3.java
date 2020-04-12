package examples;

public class T7_3
{
  public static void main(String[] args)
  {
    System.out.println(intToGrade(5));
  }

  static Grade intToGrade(int grade)
  {
    Grade res;

    switch (grade)
    {
      case 1: res=Grade.VERYBAD; break;
      case 2: res=Grade.BAD; break;
      case 3: res=Grade.SATISFACTORILY; break;
      case 4: res=Grade.GOOD; break;
      case 5: res=Grade.EXCELLENT; break;
      default: res=Grade.NOTDEFINED;
    }

    return res;
  }

//  Создайте метод, возвращающий значение enum Grade по числовой оценке.
//  Сигнатура метода static Grade intToGrade(int grade)
//
//  Соответствие оценок
//
//1 - VERYBAD
//2 - BAD
//3 - SATISFACTORILY
//4 - GOOD
//5 - EXCELLENT
//  все остальное NOTDEFINED
//
//  Например, intToGrade(4) должно вернуть GOOD


}
enum Grade
{
 VERYBAD,
 BAD,
 SATISFACTORILY,
 GOOD,
 EXCELLENT,
 NOTDEFINED,
}