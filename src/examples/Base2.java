package examples;
//H2 Домашнее задание: не пройдено, оценка: 44.0
//        Комментарий:
//        OK: Тест "Метод subtraction(int x, int y)" пройден успешно.
//        OK: Тест "Метод addition(int x, int y)" пройден успешно.
//        OK: Тест "Метод multiplication(int x, int y)" пройден успешно.
//        OK: Тест "Метод calculation()" пройден успешно.
//        ERROR: Тест "Метод calculation(int a, int b, int c)" не пройден. Метод осуществляет неверный вывод на консоль. Выведено:
//        Вызвана функция calculation() c параметрами a = 85, b = 84, c = 52
//        Вызвана функция addition() с параметрами x = 85, y = 84
//        Вызвана функция addition() с параметрами x = 169, y = 52
//        a + b + c = 221
//        Вызвана функция multiplication() с параметрами x = 85, y = 85
//        Вызвана функция multiplication() с параметрами x = 85, y = 7225
//        a^3 = 614125
//        Вызвана функция multiplication() с параметрами x = 52, y = 52
//        Вызвана функция addition() с параметрами x = 84, y = 2704
//        Вызвана функция subtraction() с параметрами x = 85, y = 2788
//        a - (b + c^2) = -2703
//        Ожидалось:
//        Вызвана функция calculation() с параметрами a = 85, b = 84, c = 52
//        Вызвана функция addition() с параметрами x = 85, y = 84
//        Вызвана функция addition() с параметрами x = 169, y = 52
//        a + b + c = 221
//        Вызвана функция multiplication() с параметрами x = 85, y = 85
//        Вызвана функция multiplication() с параметрами x = 85, y = 7225
//        a^3 = 614125
//        Вызвана функция multiplication() с параметрами x = 52, y = 52
//        Вызвана функция addition() с параметрами x = 84, y = 2704
//        Вызвана функция subtraction() с параметрами x = 85, y = 2788
//        a - (b + c^2) = -2703
//=======================================
//        ERROR: Тест "Метод main(String[] args)" не пройден. Метод осуществляет неверный вывод на консоль. Выведено:
//        Вызвана функция subtraction() с параметрами x = 45, y = 12
//        Вызвана функция subtraction() с параметрами x = 23, y = 55
//        Вызвана функция addition() с параметрами x = 128, y = 787
//        Вызвана функция addition() с параметрами x = 528, y = 387
//        Вызвана функция multiplication() с параметрами x = 124, y = 87
//        Вызвана функция multiplication() с параметрами x = 1528, y = 3
//        a = 34
//        b = 55
//        Вызвана функция addition() с параметрами x = 34, y = 55
//        a + b = 89
//        Вызвана функция subtraction() с параметрами x = 34, y = 55
//        a - b = -21
//        Вызвана функция multiplication() с параметрами x = 34, y = 55
//        a * b = 1870
//        Вызвана функция calculation() c параметрами a = 11, b = 25, c = 410
//        Вызвана функция addition() с параметрами x = 11, y = 25
//        Вызвана функция addition() с параметрами x = 36, y = 410
//        a + b + c = 446
//        Вызвана функция multiplication() с параметрами x = 11, y = 11
//        Вызвана функция multiplication() с параметрами x = 11, y = 121
//        a^3 = 1331
//        Вызвана функция multiplication() с параметрами x = 410, y = 410
//        Вызвана функция addition() с параметрами x = 25, y = 168100
//        Вызвана функция subtraction() с параметрами x = 11, y = 168125
//        a - (b + c^2) = -168114
//        Вызвана функция calculation() c параметрами a = 100, b = 9, c = 98
//        Вызвана функция addition() с параметрами x = 100, y = 9
//        Вызвана функция addition() с параметрами x = 109, y = 98
//        a + b + c = 207
//        Вызвана функция multiplication() с параметрами x = 100, y = 100
//        Вызвана функция multiplication() с параметрами x = 100, y = 10000
//        a^3 = 1000000
//        Вызвана функция multiplication() с параметрами x = 98, y = 98
//        Вызвана функция addition() с параметрами x = 9, y = 9604
//        Вызвана функция subtraction() с параметрами x = 100, y = 9613
//        a - (b + c^2) = -9513
//        Ожидалось:
//        Вызвана функция subtraction() с параметрами x = 45, y = 12
//        Вызвана функция subtraction() с параметрами x = 23, y = 55
//        Вызвана функция addition() с параметрами x = 128, y = 787
//        Вызвана функция addition() с параметрами x = 528, y = 387
//        Вызвана функция multiplication() с параметрами x = 124, y = 87
//        Вызвана функция multiplication() с параметрами x = 1528, y = 3
//        a = 34
//        b = 55
//        Вызвана функция addition() с параметрами x = 34, y = 55
//        a + b = 89
//        Вызвана функция subtraction() с параметрами x = 34, y = 55
//        a - b = -21
//        Вызвана функция multiplication() с параметрами x = 34, y = 55
//        a * b = 1870
//        Вызвана функция calculation() с параметрами a = 11, b = 25, c = 410
//        Вызвана функция addition() с параметрами x = 11, y = 25
//        Вызвана функция addition() с параметрами x = 36, y = 410
//        a + b + c = 446
//        Вызвана функция multiplication() с параметрами x = 11, y = 11
//        Вызвана функция multiplication() с параметрами x = 121, y = 11
//        a^3 = 1331
//        Вызвана функция multiplication() с параметрами x = 410, y = 410
//        Вызвана функция addition() с параметрами x = 25, y = 168100
//        Вызвана функция subtraction() с параметрами x = 11, y = 168125
//        a - (b + c^2) = -168114
//        Вызвана функция calculation() с параметрами a = 100, b = 9, c = 98
//        Вызвана функция addition() с параметрами x = 100, y = 9
//        Вызвана функция addition() с параметрами x = 109, y = 98
//        a + b + c = 207
//        Вызвана функция multiplication() с параметрами x = 100, y = 100
//        Вызвана функция multiplication() с параметрами x = 10000, y = 100
//        a^3 = 1000000
//        Вызвана функция multiplication() с параметрами x = 98, y = 98
//        Вызвана функция addition() с параметрами x = 9, y = 9604
//        Вызвана функция subtraction() с параметрами x = 100, y = 9613
//        a - (b + c^2) = -9513
//        По данной задаче в целом не зачет, решение возвращено на доработку. Задача выполнена на 70.97%%
//
public class Base2 {
   static final String X_EQUALS = "x = ";
   static final String Y_EQUALS = "y = ";
   static final String A_EQUALS = "a = ";
   static final String B_EQUALS = "b = ";
   static final String C_EQUALS = "c = ";

   public static int subtraction(int x, int y){
      int k = x - y;
      System.out.println("Вызвана функция subtraction() с параметрами " + X_EQUALS + x + ", " +  Y_EQUALS + y);
      return k;
   }

   public static int addition(int x, int y){
      int k = x + y;
      System.out.println("Вызвана функция addition() с параметрами " + X_EQUALS + x + ", " +  Y_EQUALS + y);
      return k;
   }

   public static int multiplication(int x, int y){
      int k = x * y;
      System.out.println("Вызвана функция multiplication() с параметрами " + X_EQUALS + x + ", " +  Y_EQUALS + y);
      return k;
   }

   public static void calculation(){
      int a, b, c;
      a = 34;
      b = 55;
      System.out.println(A_EQUALS + a);
      System.out.println(B_EQUALS + b);
      c = addition(a, b);
      System.out.println("a + b = " + c);
      c = subtraction(a, b);
      System.out.println("a - b = " + c);
      c = multiplication(a, b);
      System.out.println("a * b = " + c);
   }

   public static void calculation(int a, int b, int c){
      System.out.println("Вызвана функция calculation() c параметрами " + A_EQUALS + a + ", " + B_EQUALS + b + ", " + C_EQUALS + c);
      int add1 = addition(a, b);
      int add2 = addition(add1, c);
      System.out.println("a + b + c = " + add2);
      System.out.println("a^3 = " + multiplication(a, multiplication(a,a)));
      System.out.println("a - (b + c^2) = " + subtraction(a,addition(b,multiplication(c,c))));
   }

   public static void main(String[] args){
      subtraction(45,12);
      subtraction(23,55);
      addition(128,787);
      addition(528,387);
      multiplication(124,87);
      multiplication(1528, 3);
      calculation();
      calculation(11,25,410);
      calculation(100,9,98);
   }

}
