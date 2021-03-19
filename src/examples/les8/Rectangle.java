package examples.les8;

public class Rectangle {
   private double a;
   private double b;
   public Rectangle(double a, double b) {
      this.a = a;
      this.b = b;
   }
   public double area() {
      return a*b;
   }
   public int compareTo(Rectangle anRectangle) {
      double b = anRectangle.area();
      double a = area();
      if (a > b) {
         System.out.print("a   > b : ");
         return 1;
      }
      if (a == b) {
         System.out.print("a == b : ");
         return 0;
      }
      if (a < b) {
         System.out.print("прямоугольник" + a + "<" + b +" результат " );
         return -1;
      }
      int c = 0;
      return compareTo(null);
   }
   public static void main(String[] args) {
      System.out.println( new Rectangle(1, 3).compareTo(new Rectangle(2, 3)));
   }
}
