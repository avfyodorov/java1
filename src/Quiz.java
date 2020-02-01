import java.security.MessageDigest;

public class Quiz
{
  public static void main(String[] args) {
    int a,b;
    boolean a1, b1;
    for (a=0;a<2;a++) {
      for (b=0;b<2;b++) {
        System.out.printf("%d %d   ",a,b);     //Показываем наши значения переменных а и b
        /*приводим значения переменных к булевскому типу
         *так как не все операторы сравнения могут сравнивать int*/
        if (a==0)
          a1 = false;
        else
          a1 = true;
        if (b==0)
          b1 = false;
        else
          b1 = true;

        /* Выводим на консоль получившиеся значения
         * так как пар операторов в задании не много менял их в ручную*/
        System.out.println((a&b)+" "+(a1==b1));
      }
    }
  }

/*
  public static void main(String[] args) throws Exception
  {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] digest = md.digest("abracadabra".getBytes("UTF-8"));
    for (byte b : digest) {
      System.out.printf("%02x", b);
    }
  }
  */
}

