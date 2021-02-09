package misc.kadyrovas.gc;

        import java.util.HashMap;
        import java.util.Map;

public class Heap1 {

   byte[] bytes; //куча
   HashMap<Integer,Integer> mapFree = new HashMap<>();
   HashMap<Integer,Integer> mapBusy = new HashMap<>();
   Heap1(int maxHeapSize){
      this.bytes = new byte[maxHeapSize];
      mapFree.put(0,maxHeapSize);
   }
   public int malloc(int size) throws OutOfMemoryException {
      int ptr = 0;
      int dif;
      int min = Integer.MAX_VALUE;
      for (Map.Entry<Integer,Integer> entry: mapFree.entrySet())
         if (entry.getValue() - size < min && entry.getValue() >= size){
            min = entry.getValue() - size;
            ptr = entry.getKey();
         }
      if (min == Integer.MAX_VALUE)
         throw new  OutOfMemoryException("Нет свободного блока подходящего размера");
      mapBusy.put(ptr, size);
      dif = mapFree.get(ptr) - size;
      if (dif != 0)
         mapFree.put(ptr + size, dif);
      mapFree.remove(ptr);
      return ptr;
   }

   public void free(int ptr) throws InvalidPointerException {
      if (mapBusy.get(ptr) == null)
         throw new InvalidPointerException("Неверный указатель");
      int size = mapBusy.get(ptr);
      mapBusy.remove(ptr);
      mapFree.put(ptr, size);
   }

   public void defrag(){
      boolean wasFound = true;
      int ptr=0;
      int size, sizeAll;
      while (wasFound) {
         wasFound = false;
         for (Map.Entry<Integer, Integer> entry: mapFree.entrySet())
            if (mapFree.get(entry.getKey() + entry.getValue()) != null){
               wasFound = true;
               ptr = entry.getKey();
            }
         if (wasFound) {
            size = mapFree.get(ptr);
            sizeAll = size + mapFree.get(ptr + size);
            mapFree.remove(ptr + size);
            mapFree.put(ptr, sizeAll);
         }
      }
   }

   public void compact() {
      int ptrTo = 0;
      int ptrFrom;
      int size = 0;
      while (true) {
         while (mapBusy.get(ptrTo) != null)
            ptrTo += mapBusy.get(ptrTo);
         if (mapFree.get(ptrTo) == null) break;
         ptrFrom = ptrTo;
         while (mapBusy.get(ptrFrom) == null && mapFree.get(ptrFrom) != null)
            ptrFrom += mapFree.get(ptrFrom);
         if (mapBusy.get(ptrFrom) == null) break;
         move(ptrFrom, ptrTo);
         size = mapBusy.get(ptrFrom);
         mapBusy.put(ptrTo, size);
         mapBusy.remove(ptrFrom);
         mapFree.remove(ptrTo);
         mapFree.put(ptrTo + size, ptrFrom - ptrTo);
         ptrTo += size;
      }
   }

   public void move(int ptrFrom, int ptrTo){
      for (int i = ptrFrom; i < ptrFrom + mapBusy.get(ptrFrom); i ++)
         bytes[i - ptrFrom + ptrTo] = bytes[i];
   }

   public void getBytes(int ptr, byte[] bytes) {
      //System.arraycopy(this.bytes, ptr, bytes, 0, size);
      for (int i = ptr; i < ptr + mapBusy.get(ptr); i ++)
         bytes[i - ptr] = this.bytes[i];
   }

   public void setBytes(int ptr, byte[] bytes) {
      //System.arraycopy(bytes, 0, this.bytes, ptr, size);
      for (int i = ptr; i < ptr + mapBusy.get(ptr); i ++)
         this.bytes[i] = bytes[i - ptr];

   }

   public static void main(String[] args) throws OutOfMemoryException, InvalidPointerException {
      Heap1 myHeap = new Heap1(20);

      System.out.println(myHeap.malloc(3));
      System.out.println(myHeap.malloc(5));
      System.out.println(myHeap.malloc(4));
      System.out.println(myHeap.malloc(7));

      myHeap.free(3);
      myHeap.compact();


      System.out.println("Занятые блоки");
      for (Map.Entry<Integer,Integer> entry: myHeap.mapBusy.entrySet())
         System.out.println(entry.getKey() + " " + entry.getValue());

      System.out.println("Свободные блоки");
      for (Map.Entry<Integer,Integer> entry: myHeap.mapFree.entrySet())
         System.out.println(entry.getKey() + " " + entry.getValue());
   }
}

