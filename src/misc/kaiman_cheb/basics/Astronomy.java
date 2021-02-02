package misc.kaiman_cheb.basics;

public class Astronomy {
   public static Double sphereSquare(Double r) {
      final double PI = 3.14;
      double s = 4 * PI * r * r;
      return s;
   }
   public static Double earthSquare(){
      double s = (sphereSquare(6371.2));
      return s;
   }
   public static Double mercurySquare(){
      double s = (sphereSquare(2439.7));
      return s;
   }
   public static Double jupiterSquare(){
      double s = (sphereSquare(71492.0));
      return s;
   }
   public static Double earthVsMercury(){
      double vs = earthSquare()/mercurySquare();
      return vs;
   }
   public static Double earthVsJupiter(){
      double vs = earthSquare()/jupiterSquare();
      return vs;
   }

   public static void main(String[] args) {
      System.out.println(earthSquare());
      System.out.println(mercurySquare());
      System.out.println(jupiterSquare());
      System.out.println(earthVsMercury());
      System.out.println(earthVsJupiter());
   }

}