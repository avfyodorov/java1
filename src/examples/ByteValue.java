package examples;

public class ByteValue {
   public static void main(String[] args) {
      result1(255);
      byte value = (byte) 2;
      byte mask=0x1;
      mask= (byte) (mask<<1);
      int result =  (value & 0x1)==1  ? 1 : 0;
      System.out.printf("result="+result+" mask="+mask);
   }

   static int result1(int value) {
      byte x = (byte) (value & 0x1);
      int result;
      if (x == 1) {
         result = 1;
      } else result = 0;
      System.out.println(result);
      return result;
   }
}