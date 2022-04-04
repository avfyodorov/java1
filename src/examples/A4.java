package examples;

/**
 * Автор: Фёдоров Александр
 * Дата:  16.02.2022  18:20
 */
public class A4 {
   public String get(){return "a";}

   public static void main(String[] args) {
      A4 a=new B4();
      System.out.println(a.get());
      B4 b=new B4();

//      System.out.println(b.p());
   }
}
class B4 extends A4{
   public void p() {
      System.out.println("mmmmmmmm");
   }
   @Override
   public String get() {
      return "B";
   }
}