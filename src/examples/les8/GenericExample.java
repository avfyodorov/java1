package examples.les8;

public class GenericExample <T>
{
  T o;
  GenericExample(T o){this.o=o;}
  public String getObjectName(){return o.getClass().getName();}

  public static void main(String[] args)
  {
    System.out.println(new GenericExample<Integer>(5).getObjectName());
  }
}
