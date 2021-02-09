package misc.kadyrovas.gc;

        import java.util.Map;
        import java.util.TreeMap;

public class Heap{
   volatile byte[] bytes; //куча
   TreeMap<Integer,Integer> mapFree = new TreeMap<>();
   TreeMap<Integer,Integer> mapBusy = new TreeMap<>();

   Heap(int maxHeapSize){
      this.bytes = new byte[maxHeapSize];
      mapFree.put(0,maxHeapSize);
   }

   public int malloc(int size) throws OutOfMemoryException {
      int ptr = 0;
      int dif;

      //Находим блок памяти, который больше или равен size

      for (int i = 0; i < 2; i ++) {
         for (Map.Entry<Integer, Integer> entry : mapFree.entrySet())
            if (entry.getValue() >= size) {
               ptr = entry.getKey();
               mapBusy.put(ptr, size);
               dif = mapFree.get(ptr) - size;
               mapFree.remove(ptr);
               if (dif != 0)
                  mapFree.put(ptr + size, dif);

               return ptr;
            }
         //Размещение в памяти не получилось
         compact();
      }
      throw new  OutOfMemoryException("Нет свободного блока подходящего размера");
   }

   public void free(int ptr) throws InvalidPointerException {
      Map.Entry<Integer, Integer> lowerEntry;
      Map.Entry<Integer, Integer> higherEntry;
      if (mapBusy.get(ptr) == null)
         throw new InvalidPointerException("Неверный указатель");
      int size = mapBusy.get(ptr);
      mapBusy.remove(ptr);
      mapFree.put(ptr, size);
      //Ищем предыдущий свободный блок
      lowerEntry = mapFree.lowerEntry(ptr);
      higherEntry = mapFree.higherEntry(ptr);
      if (lowerEntry != null && lowerEntry.getKey() + lowerEntry.getValue() == ptr) {
         size = lowerEntry.getValue() + mapFree.get(ptr);
         mapFree.remove(ptr);
         ptr = lowerEntry.getKey();
         mapFree.put(ptr, size);
      }
      if (higherEntry != null && ptr + size == higherEntry.getKey()) {
         mapFree.put(ptr, size + higherEntry.getValue());
         mapFree.remove(higherEntry.getKey());
      }
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
      int ptr = 0;
      int size = 0;
      TreeMap<Integer, Integer> map = new TreeMap<>();
      for (Map.Entry<Integer, Integer> entry: mapBusy.entrySet()){
         move(entry.getKey(), ptr);
         size = entry.getValue();
         map.put(ptr, size);
         ptr += size;
      }
      mapBusy.clear();
      mapBusy = map;
      mapFree.clear();
      if (ptr < bytes.length - 1)
         mapFree.put(ptr, bytes.length - ptr);
   }

   public void move(int ptrFrom, int ptrTo){
      for (int i = ptrFrom; i < ptrFrom + mapBusy.get(ptrFrom); i ++)
         bytes[i - ptrFrom + ptrTo] = bytes[i];
   }

   public void getBytes(int ptr, byte[] bytes) {
      for (int i = ptr; i < ptr + mapBusy.get(ptr); i ++)
         bytes[i - ptr] = this.bytes[i];
   }

   public void setBytes(int ptr, byte[] bytes) {
      for (int i = ptr; i < ptr + mapBusy.get(ptr); i ++)
         this.bytes[i] = bytes[i - ptr];
   }

   public static void main(String[] args) throws OutOfMemoryException, InvalidPointerException {
      Heap1 myHeap = new Heap1(20);
      for (int i = 0; i < 10; i ++) myHeap.malloc(2);
      for (int i = 2; i < 20; i += 4) myHeap.free(i);
      myHeap.free(8);
      myHeap.malloc(3);
      myHeap.malloc(4);

      System.out.println("Занятые блоки");
      myHeap.mapBusy.entrySet().forEach((value)->System.out.println(value.getKey() + "--" + value.getValue()));
      System.out.println("Свободные блоки");
      myHeap.mapFree.entrySet().forEach((value)->System.out.println(value.getKey() + "--" + value.getValue()));
   }
}