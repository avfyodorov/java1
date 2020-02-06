public class Z
{
  public static void main(String[] args)
  {
//    System.out.println(addAsStrings(1,3));
//    System.out.println(textGrade(443));
    System.out.println(factorial(4));
  }


  static long factorial(long n)
  {
    long res = 1;
    for (int i = 1; i <= n; i++)
    {
      res = res * i;
    }
    return res;
  }

  static String textGrade(int grade)
  {
    String res = "";
    if (grade == 0)
      res = "не оценено";
    else if (grade < 21)
      res = "очень плохо";
    else if (grade < 41)
      res = "плохо";
    else if (grade < 61)
      res = "удовлетворительно";
    else if (grade < 81)
      res = "хорошо";
    else if (grade < 101)
      res = "отлично";
    else
      res = "не определено";

    return res;
  }

  static int addAsStrings(int n1, int n2)
  {
    return Integer.parseInt("0" + n1 + n2);
  }
}
/*
class Point2D
{
  private int x;
  private int y;

  Point2D(int x, int y)
  {
    this.x=x;
    this.y=y;
  }

  @Override
  public String toString()
  {
    return ""+x+","+y;
  }
}

class Point3D extends Point2D
{
  private int z;

  Point3D(int x, int y, int z)
  {
    super(x,y);
    this.z=z;
  }

  @Override
  public String toString()
  {
    return super.toString()+","+z;
  }

}
*/