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
  }
}

