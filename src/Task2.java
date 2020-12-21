public class Task2
{
  public static void printJava()
  {
    String s_good = "Хорошо идут дела";
    String s_learn = "Изучаю Java я!";
    String s_empty = " ";

    System.out.println(s_good);
    System.out.println(s_learn);

    System.out.print(s_good);
    System.out.print(s_empty);
    System.out.println(s_learn);

    System.out.print(s_learn);
    System.out.print(s_empty);
    System.out.println(s_good);
  }

  public static final String X_EQUALS = "x = ";
  public static final String Y_EQUALS = "y = ";
  public static final String A_EQUALS = "a = ";
  public static final String B_EQUALS = "b = ";
  public static final String C_EQUALS = "c = ";

  public static void print_call(String funcName, int x, int y)
  {
    System.out.print("Вызвана функция " + funcName + "() с параметрами x = ");
    System.out.print(x);
    System.out.print(", y = ");
    System.out.println(y);
  }

  public static void print_res(String text, int res)
  {
    System.out.print(text);
    System.out.println(res);
  }

  public static int subtraction(int x, int y)
  {
    print_call("subtraction", x, y);
    return x - y;
  }

  public static int addition(int x, int y)
  {
    print_call("addition", x, y);
    return x + y;
  }

  public static int multiplication(int x, int y)
  {
    print_call("multiplication", x, y);
    return x * y;
  }

  public static void calculation()
  {
    int a, b, c;
    a = 34;
    b = 55;

    print_res("a = ", a);
    print_res("b = ", b);

    c = addition(a, b);
    print_res("a + b = ", c);

    c = subtraction(a, b);
    print_res("a - b = ", c);

    c = multiplication(a, b);
    print_res("a * b = ", c);

  }

  public static void main(String[] args)
  {
    printJava();

    subtraction(45, 12);
    subtraction(23, 55);
    addition(128, 787);
    addition(528, 387);
    multiplication(124, 87);
    multiplication(1528, 3);

    //- вызвать функцию calculation() без параметров
    calculation();
    //- вызвать функцию calculation() c параметрами 11, 25 и 410
    calculation(11, 25, 410);
    //- вызвать функцию calculation() c параметрами 100, 9 и 98
    calculation(100, 9, 98);
  }

  public static void calculation(int a, int b, int c) {
    System.out.print("Вызвана функция calculation() с параметрами ");
    System.out.print(A_EQUALS + a + ", ");
    System.out.print(B_EQUALS + b + ", ");
    System.out.println(C_EQUALS + c);

    System.out.println("a + b + c = " + addition(addition(a, b), c));
    System.out.println("a^3 = " + multiplication(multiplication(a, a), a));
    System.out.println("a - (b + c^2) = " + subtraction(a, addition(b, multiplication(c, c))));
  }

}

