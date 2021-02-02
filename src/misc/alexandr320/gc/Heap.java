package misc.alexandr320.gc;


        import java.util.*;
        import java.util.concurrent.ConcurrentHashMap;

public class Heap {
   private final byte[] bytes;
   private final Map<Integer, Integer> indexToLengthMap = new  ConcurrentHashMap<>();
   private final Map<Integer, Integer> freeIndexMap = new ConcurrentHashMap<>();
   List<Integer> deleted = new ArrayList<>();

   public static void main(String[] args) {
      Heap heap = new Heap(10);
      heap.malloc(10);
      System.out.println(heap.indexToLengthMap);

      heap = new Heap(10);
      heap.malloc(3);
      heap.malloc(3);
      heap.malloc(4);
      System.out.println(heap.indexToLengthMap);

      heap = new Heap(3);
      heap.malloc(1);
      heap.malloc(1);
      heap.malloc(1);
      heap.free(0);
      heap.free(2);
      heap.malloc(2);
      System.out.println(heap.indexToLengthMap);
   }

   public Heap(int maxHeapSize) {
      if (maxHeapSize < 1) {
         throw new IllegalArgumentException();
      }
      this.bytes = new byte[maxHeapSize];
      freeIndexMap.put(0, maxHeapSize);
   }

   public int malloc(int size) {
      try {
         return tryMalloc(size);
      } catch (OutOfMemoryException e) {
         compact();
         return tryMalloc(size);
      }
   }

   private int tryMalloc(int size) {  //  "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size
      if (size < 1) {
         throw new IllegalArgumentException();
      }
      for (Map.Entry<Integer, Integer> freeEntry : freeIndexMap.entrySet()) {
         if (freeEntry.getValue() >= size) {
            int newBlockStartIndex = freeEntry.getKey();
            indexToLengthMap.put(newBlockStartIndex, size);
            freeIndexMap.remove(newBlockStartIndex);
            int freeStartIndex = newBlockStartIndex + size;
            int lastFreeLength = freeEntry.getValue() - size;
            if (lastFreeLength > 0) {
               freeIndexMap.put(freeStartIndex, lastFreeLength);
            }
            return newBlockStartIndex;
         }
      }
      throw new OutOfMemoryException("Нет свободного блока подходящего размера.");
   }

   public void free(int ptr) { // "удаляет", т.е. помечает как свободный блок памяти по "указателю". Проверять валидность
      // указателя - т.е. то, что он соответствует началу ранее выделенного блока, а не его середине, или вообще, уже свободному
      if (!indexToLengthMap.containsKey(ptr)) {
         return;
      }
      deleted.add(ptr);
      int size = indexToLengthMap.remove(ptr);
      freeIndexMap.put(ptr, size);
   }

   public void defrag() { // осуществляет дефрагментацию кучи
      List<Integer> toRemove = new ArrayList<>();
      Map.Entry<Integer, Integer> prev = null;
      for (Map.Entry<Integer, Integer> curr : freeIndexMap.entrySet()) {
         if (prev == null) {
            prev = curr;
            continue;
         }
         int firstFreeIndex = prev.getKey() + prev.getValue();
         if (firstFreeIndex == curr.getKey()) {
            prev.setValue(prev.getValue() + curr.getValue());
            toRemove.add(curr.getKey());
         } else {
            prev = curr;
         }
      }
      for (Integer index : toRemove) {
         freeIndexMap.remove(index);
      }
   }

   public void compact() {  // компактизация кучи - перенос всех занятых блоков в начало хипа, с копированием самих данных - элементов массива
      Map.Entry<Integer, Integer> prev = null;
      for (Map.Entry<Integer, Integer> curr : indexToLengthMap.entrySet()) {
         int emptyBytesBeforeCurrent = countEmptyBytes(prev, curr);
         if (emptyBytesBeforeCurrent > 0) {
            curr = moveLeft(curr, emptyBytesBeforeCurrent);
         }
         prev = curr;
      }
      if (prev != null) {
         freeIndexMap.clear();
         int firstIndex = prev.getKey() + prev.getValue();
         int size = bytes.length - firstIndex;
         if (size > 0) {
            freeIndexMap.put(firstIndex, size);
         }
      } else {
         freeIndexMap.clear();
         freeIndexMap.put(0, bytes.length);
      }
   }

   private int countEmptyBytes(Map.Entry<Integer, Integer> prev, Map.Entry<Integer, Integer> curr) {
      int currIndex = curr.getKey();
      if (prev == null) {
         return currIndex;
      } else {
         int prevIndex = prev.getKey();
         int prevLength = prev.getValue();
         int firstEmptyIndex = prevIndex + prevLength;
         return currIndex - firstEmptyIndex;
      }
   }

   private Map.Entry<Integer, Integer> moveLeft(Map.Entry<Integer, Integer> curr, int diff) {
      int oldStartIndex = curr.getKey();
      int newStartIndex = oldStartIndex - diff;
      int length = curr.getValue();
      int oldLastIndex = oldStartIndex + length - 1;
      for (int i = oldStartIndex; i <= oldLastIndex; i++) {
         bytes[i - diff] = bytes[i];
      }
      indexToLengthMap.remove(oldLastIndex);
      indexToLengthMap.put(newStartIndex, length);
      return new AbstractMap.SimpleEntry<>(newStartIndex, length);
   }

   public void getBytes(int ptr, byte[] bytes) {
      //System.arraycopy(this.bytes, ptr, bytes, 0, size);
   }

   public void setBytes(int ptr, byte[] bytes) {
      //System.arraycopy(bytes, 0, this.bytes, ptr, size);
   }

   public static class OutOfMemoryException extends RuntimeException {  // нет свободного блока подходящего размера

      public OutOfMemoryException(String message) {
         super(message);
      }
   }

   public static class InvalidPointerException extends RuntimeException { // неверный указатель. Возникает при освобождении блока,
      // если переданный указатель не является началом блока

      public InvalidPointerException(String message) {
         super(message);
      }
   }


}
