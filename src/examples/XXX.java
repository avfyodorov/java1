package examples;

public class XXX {
   public static int sumBits(byte value) {
      int summ = 0;
      int i;
      byte x = value;
      for (i=1; i<8; i++){
         summ += x & 00000001;
         x = (byte) (x >> 1);
      }
      return summ;
   }
   public static void main(String[] args) {
      byte value = (byte)0b11111111;
      int result = sumBits(value);
      System.out.println(result);


      double d=0.00;
      for (int i=0; i<10;i++)
         d=d+0.1;
      System.out.println(d);
   }
}
