public class T_01_3
{
  public static void main(String[] args)
  {
    byte c=123; //Переменная с типом байт, имя b1 значение 123;

    short s1=32023; //Малое целое, имя s1, значение 32023;
    int i1=65432; //Целое, имя i1, значение 65432;
    long l1=123456789012345L;   //Длинное целое, имя l1, значение, 123456789012345;

    float c3=1.22278f; //Число с плавающей точкой float, имя fl значение 1,22278
    double c4=3.1415926535;  //Число с плавающей точкой, имя pi значение 3,1415926535
    double c5=1.5e7;  //Число с плавающей точкой, имя koe, значение 1.5 *10 ^ 7

    String message="Запись фильма \"Матрица\" находится в файле C:\\video\\matrix.avi";
    //Строковая переменная, имя message,
      //    значение "Запись фильма "Матрица" находится в файле C:\video\matrix.avi"

//double x=5.234%2;
    double x=5/2;

    //System.out.println(x);
    fractional(153);
  }

  static double fractional(double num)
  {
    Double d=num;
    System.out.println(num % 1);
    return d;
  }
}
