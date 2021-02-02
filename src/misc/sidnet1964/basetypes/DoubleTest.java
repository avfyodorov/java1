package misc.sidnet1964.basetypes;

public class DoubleTest {
   public static void main(String[] args) {
      final int en = 3;
      final int em = 1000;
      DoubleHashTable<Integer,String> hTable = new DoubleHashTable(en);
//        System.out.println(hTable);
//  заполнение таблицы
//        for (int i=0; i < em; i++)
//            hTable.add(i, "i=" + i);

//        hTable.add(3, "i=3/1");
//        hTable.add(3, "i=3/2");
      hTable.add(6, "i=6");
      hTable.remove(6);
      hTable.add(3, "i=3/1");
      hTable.add(3, "i=3/2");
      hTable.add(6, "i=6");
      hTable.add(9, "i=9");

      System.out.println(hTable);
      System.out.println("hTable.size() = " + hTable.size());
      System.out.println("hTable.table.length = " + hTable.table.length);

//        hTable.remove(101);
//        System.out.println(hTable);
//        System.out.println(hTable.size());

      for (var inic : hTable)
         System.out.println(inic);

      var iter = hTable.getIterator();

//        hTable.add(1, "111");
//        hTable.change(0, 101);
//        for (int i=125; i<1000; i++)
//            System.out.println(hTable.get(i));
//
//        System.out.println(hTable.size());
//        System.out.println(hTable);
   }
}
