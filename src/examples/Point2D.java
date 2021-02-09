package examples;

public class Point2D {
   int x = 3;
   int y = 5;

   Point2D() {
   }


   @Override
   public String toString() {

      return "" + x + "," + y;
   }

}
class Point3D extends Point2D {
   private int z;


   Point3D() {
   }

   @Override
   public String toString() {
      return "" + x + "," + y + "," + z;
   }


   public static void main(String[] args) {
      Point2D point2D = new Point2D();
      System.out.println(point2D);
//            System.out.println(Point3D);
   }
}
