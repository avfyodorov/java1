public class A extends B
{
  public int f1;
  public static void main(String[] args)
  {
    try {
      System.out.println(sqrt(4.0));
      System.out.println(sqrt(0.0));
      System.out.println(sqrt(-4.0));
    } catch (Exception e){
      System.out.println(e.getMessage());
    }



//    B b=new B();
 //   A a=(A)b;

  }
  public static double sqrt(double x) {
    if (x<0)
      throw new  java.lang.IllegalArgumentException("Expected non-negative number, got "+x);
    return Math.sqrt(x); // your implementation here
  }

}

class B //extends A
{
  public int f3;
}
