package misc.topograh95.gc;


        import java.util.ArrayDeque;
        import java.util.ArrayList;

class Heap {
   byte[] bytes;
   ArrayDeque<Bloc> occupiedBloc;  // Очередь занятых блоков;
   int maxHeapSize;
   int freeCell = 0;  // Индекс свободной ячейки в массиве byte[] bytes;
   int ptrFree = 0;   // Указатель на свободный блок;
   int sizeFree = 0;  // Размер свободного блока;

   Heap(int maxHeapSize) {
      //    bytes = new byte[maxHeapSize];
      this.maxHeapSize = maxHeapSize;
      occupiedBloc = new ArrayDeque<>();
   }

   static class Bloc implements Comparable<Bloc> {
      public int ptr;
      public int size;

      Bloc(int ptr, int size) {
         this.ptr = ptr;
         this.size = size;
      }

      @Override
      public String toString() {
         return "{"+ ptr +" "+ size +"}";
      }

      @Override
      public int compareTo(Bloc o) {
         return Integer.compare(this.ptr, o.ptr);
      }
   }

   public int malloc(int size) {
      // Сначала занимаем блоки последовательно до конца массива;
      if (freeCell+size <= maxHeapSize) {
         // Помещаем занятые блоки в конец очереди;
         occupiedBloc.offer(new Bloc(freeCell, size));
         //   bytes[freeCell] = 1;
         freeCell += size;
         return freeCell - size;
      }
      // Если массив занят и свободного блока нет, выбрасываем исключение;
      if (sizeFree == 0) throw new OutOfMemoryException("Недостаточно памяти!");
      // Если размер свободного блока недостаточен, вызываем метод compact();
      if (sizeFree < size) {
         compact();
         return malloc(size);
      }
      // Иначе - занимаем свободный блок, отделяем от свободного блока нужный размер;
      sizeFree -= size;
      int ptr = ptrFree;
      ptrFree += size;
      // Помещаем занятый блок в конец очереди;
      occupiedBloc.offer(new Bloc(ptr, size));
      //   bytes[ptr] = 1;
      return ptr;
   }

   public void free(int ptr) {
      // Удаляем занятый блок из начала очереди;
      Bloc bloc = occupiedBloc.pop();
      // Проверяем указатель, если не соответствует, выбрасываем исключение;
      if (bloc.ptr != ptr) throw new InvalidPointerException("Неверный указатель!");
      //   bytes[ptr] = 0;
      // Добавляем размер свободного блока;
      sizeFree += bloc.size;
   }

   public void compact() {
      bytes = new byte[maxHeapSize];
      freeCell = 0; // Индекс свободной ячейки устанавливаем 0;
      int newPtr = 0, oldPtr;
      // Формируем список из очереди занятых блоков;
      ArrayList<Bloc> list = new ArrayList<>(occupiedBloc);
      // Сортируем список по указателям;
      list.sort( Bloc::compareTo );
      // Проходим по списку, переписываем указатели и копируем данные в массиве;
      for (Bloc bloc : list) {
         oldPtr = bloc.ptr;
         bloc.ptr = newPtr;
         freeCell += bloc.size;
         // Если в начале массива находятся занятые блоки, то оставляем их на месте;
         if (newPtr == oldPtr) {
            newPtr += bloc.size;
         } else {
            bytes[oldPtr] = 0;
            while (newPtr < freeCell)  bytes[newPtr++] = bytes[oldPtr++];
            bytes[bloc.ptr] = 1;
         }
      }
      ptrFree = 0;
      sizeFree = 0; // Обнуляем размер свободного блока;
      occupiedBloc.clear();
      // Формируем новую очередь занятых блоков из списка;
      occupiedBloc = new ArrayDeque<>(list);
      // Передаем в HeapTest очередь с новыми указателями методом setNewDeque(...);
      //       new HeapTest().setNewDeque(occupiedBloc);
   }

//    public static void main(String[] args) {
//        Heap heap = new Heap(15);
//        System.out.println(heap.malloc(3));
//        System.out.println(heap.malloc(2));
//        System.out.println(heap.malloc(4));
//        System.out.println(heap.malloc(1));
//        System.out.println(heap.malloc(3));
//        heap.free(0);
//        heap.free(3);
//        System.out.println(heap.malloc(7));
//     //   System.out.println(Arrays.toString(heap.bytes));
//        System.out.println("Занятые блоки:  "+heap.occupiedBloc.toString());
//        System.out.println("Свободный блок: {"+heap.ptrFree+' '+heap.sizeFree+'}');
//    }
}
