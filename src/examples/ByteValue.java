package examples;

public class ByteValue {
   public static void main(String[] args) {
      result1(255);
      byte value = (byte) 255;
      int result = (byte) (value & 0x1) == 1 ? 1 : 0;
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