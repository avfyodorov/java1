package misc.sidnet1964.basetypes;
//  Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0 - удаление из пустого
//  Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 1, Size: 1 - удаление отсутствующего

        import java.util.*;

public class BiTest {
   public static void main(String[] args) {
      final int MILLION = 1;
//        BiDirList<Integer> list = new BiDirList<>();
//        BiDirList<Integer> list = BiDirList.of(1, 2, 3);
      Integer[] arr = {1, 2, 3, 4, 5};
      BiDirList<Integer> list = BiDirList.from(arr);

      for (Integer inic : list)
         System.out.println(inic);
//        Iterator<Integer> iter = list.iterator();
//        list.forEach(item -> System.out.println(item));

//        if  (iter.hasNext())
//            System.out.println(iter.next());
//        if  (iter.hasNext())
//            System.out.println(iter.next());
//        if  (iter.hasNext())
//            System.out.println(iter.next());
//        if  (iter.hasNext())
//            System.out.println(iter.next());

//        list.getIterator();
//        list.remove(1);
//        list.toArray(arr);
//        list.addLast(1);
//        list.addLast(2);
//        list.addLast(3);
//        list.addFirst(11);
//        list.addFirst(12);
//        list.addFirst(13);

//        System.out.println(list);
//        for (var intic : list) {
//            System.out.println(intic);
//        }

//        BiDirList<Integer>.ListItem<Integer> current = list.getHead();
//        while(current != null) {
//            System.out.println("> " + current.getItem());
//            current = current.getNext();
//        }
//        System.out.println(list.at(0));
//        list.remove(list.at(0).getItem());
//        list.remove(1);
//        List linkedList = new LinkedList();
////        linkedList.add(0);
//        linkedList.clear();
//        List<Integer> list5 = new ArrayList();
//        list5.remove(Integer.valueOf(5));

   }
}
//BiDirList{
// head={item=1, next={item=2, next={item=3, next={item=4, next={item=5, next=null}}}}},
// tail={item=5, next=null}}