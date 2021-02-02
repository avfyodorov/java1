package misc;

public class Astronomy {

   public static Double sphereSquare(Double r) {

      final Double π = 3.14;
      Double S = 4 * π * r * r;

      return r;

   }

   public static Double earthSquare() {
      Double r = 6371.2;
      return sphereSquare(r);
   }

   public static Double mercurySquare() {
      Double r = 2439.7;
      return sphereSquare(r);
   }

   public static Double jupiterSquare() {
      Double r = 71492.0;
      return sphereSquare(r);

   }

   public static Double earthVsMercury() {

      return earthSquare() / mercurySquare();
   }

   public static Double earthVsJupiter() {

      return earthSquare() / jupiterSquare();
   }


   public static void main(String[] args) {

      System.out.println(earthSquare());
      System.out.println(mercurySquare());
      System.out.println(jupiterSquare());
      System.out.println(earthVsMercury());
      System.out.println(earthVsJupiter());
   }
}