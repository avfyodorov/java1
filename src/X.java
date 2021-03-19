public class X {
   public static void main(String[] args) {
      System.out.println("Буря ");
      System.out.print("мглою ");
      System.out.print("небо ");
      System.out.println("кроет,");
      System.out.print("Вихри ");
      System.out.print("снежные ");
      System.out.println("крутя");
      System.out.println("строка 4");


      for (int i = 0; i < a.length; i++) {
         System.out.print(a[i]+", ");
      }
   }

   static int[] a=arr();
   static int[] arr()
   {
      return new int[]{1, 2, 3};
   }

   static int[] b=arrB();
   static int[] arrB()
   {
      int[] x=new int[5];
      for (int i = 0; i < x.length; i++)
         x[i]=i;
      return x;
   }

}
