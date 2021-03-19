package examples.les05;

public class Anim implements CompareWeight{
   double weight;

   public Anim(double weight) {
      this.weight = weight;
   }

   @Override
   public CompareResult compareWeight(CompareWeight smthHasWeigt) {
      if (getWeight() < smthHasWeigt.getWeight())
         return CompareWeight.CompareResult.LESS;
      else if (getWeight() > smthHasWeigt.getWeight())
         return CompareWeight.CompareResult.GREATER;
      else
         return CompareWeight.CompareResult.EQUAL;

   }

   @Override
   public double getWeight() {
      return weight;
   }

   public static void main(String[] args) {
      Anim a1=new Anim(1.0);
      Anim a2=new Anim(2.0);
      System.out.println(a1.compareWeight(a2));
   }
}
