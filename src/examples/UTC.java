package examples;

public class UTC {
   public static void main(String[] args) {
      String my="Вызвана функция calculation() с параметрами ";
      String nemy="Вызвана функция calculation() c параметрами ";
      for (int i = 0; i < my.length(); i++) {
         if (my.charAt(i)!=nemy.charAt(i)) {
            System.out.println("'c'="+(byte)'с');
            System.out.println("Pos=" + i + " my='" + my.charAt(i) + "' code=" + (byte) my.charAt(i) + " nemy=" + (byte) nemy.charAt(i));
         }
      }
     if( my.equals(nemy))
        System.out.println("OK");
else
        System.out.println("BAD");








      BB bb = new BB();
      AA a = (AA) bb;

      char c = 'A';
      byte b = (byte) c;
      System.out.println("'" + c + "'" + "=" + b);
   }
}

class AA {
}

class BB extends AA {
}
