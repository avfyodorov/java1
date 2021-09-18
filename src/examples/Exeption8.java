package examples;

public class Exeption8 {
   public static void main(String[] args) {
      String what =  method();
      System.out.println(what);
   }

   public static String method() {
      try {
         return "SomeString";
      } catch(Exception ex) {
         return "Catch message";
      } finally {
         return "Finally message";
      }
   }
   //Вывод

}
