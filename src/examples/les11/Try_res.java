package examples.les11;

import java.io.IOException;

public class Try_res
{

  public static void doExceptions(int n) throws Throwable {
    Throwable suppressed = null;
    try {
      System.out.println("doExceptions 1");
      if (n == 0)
        throw new Exception("Exception in try");
      System.out.println("doExceptions 2");
    } catch (Throwable t) {
      suppressed = t;
      throw t;
    } finally {
      try {
        throw new Exception("Exception in finally");
      } catch (Throwable t) {
        if (suppressed != null)
          t.addSuppressed(suppressed);
        throw t;                }
    }
  }

//  Существует метод public void doSomething(int n) throws IOException,
//  создайте свой метод, с сигнатурой public void test(int n),
//  который должен содержать блоки try-catch-finally
//
//        1) В блоке try вызовите метод doSomething(n)
//
//2) В блоке catch выведите на консоль строку, полученную из исключения через  getMessage()
//
//3) Пробросите исключение дальше
//
//4) В блоке finally выведите на консоль фиксированный текст "finally executed"


  public static void main(String[] args) throws IOException
  {
    try {
      doExceptions(1);
    } catch (Throwable e) {
      System.out.println(e.getMessage());
      for (Throwable t: e.getSuppressed())
        System.out.println(t.getMessage() + " (suppressed)");
    }

    Try_res o=new Try_res();
    try
    {
      o.test(0);
    }
    catch (IOException e)
    {
      System.out.println("main - "+e.getMessage());
    }
  }
  public void test(int n) throws IOException
  {
    try
    {
      doSomething(n);
    } catch (IOException e)
    {
      System.out.println(e.getMessage());
      throw new IOException(e.getMessage());
    }
    finally
    {
      System.out.println("finally executed");
    }
  }
  public void doSomething(int n) throws IOException
  {
    throw new IOException("dosomething qyqy");

  }
}
