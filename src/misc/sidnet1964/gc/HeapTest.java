package misc.sidnet1964.gc;
        import java.util.ArrayDeque;
        import java.util.concurrent.ThreadLocalRandom;

public class HeapTest {
   static final int maxSize = 900_000_000;
   //    static final int maxSize = 1_048_576;
   static final int maxSmall = 10;
   static final int maxMedium = 100;
   static final int maxBig = 1_000;
   static final int maxHuge = 10_000;
   static int allocated = 0;

   static class Block {
      public int ptr;
      public int size;

      public Block(int ptr, int size) {
         this.ptr = ptr;
         this.size = size;
      }
   }

   static int getRandomSize() {
      int n = Math.abs(ThreadLocalRandom.current().nextInt()%10);
      int size = Math.abs(ThreadLocalRandom.current().nextInt());
      if (n < 6)
         size %= maxSmall;
      else if (n < 8)
         size %= maxMedium;
      else if (n < 9)
         size %= maxBig;
      else
         size %= maxHuge;
      return size > (maxSize-allocated)-1 ? (maxSize-allocated)/2+1 : size+1;
   }
   //  ====================================
   public static void main(String[] args) throws InvalidPointerException, OutOfMemoryException {
      Heap heap = new Heap(maxSize);
      ArrayDeque<Block> blocks = new ArrayDeque<>();
      int count = 0;
      int allocTime = 0;
      int freeTime = 0;
      heap.start = System.currentTimeMillis(); //  отсчет от момента создания объекта

      long start = System.currentTimeMillis();
      // alloc and free 30% random
      while ((maxSize - allocated) > 50000) {
         long lstart, lstop;
         int size = getRandomSize();
         allocated += size;
         count++;
         lstart = System.currentTimeMillis();
//  malloc
         int ptr = heap.malloc(size);
         lstop = System.currentTimeMillis();
         allocTime += lstop-lstart;
         blocks.offer(new Block(ptr, size));
         int n = Math.abs(ThreadLocalRandom.current().nextInt()%25);
         if (n == 0) {
            //n = Math.abs(ThreadLocalRandom.current().nextInt()%blocks.size());
            for (int i=0; i<5; i++) {
               Block block = blocks.poll();
               if (block == null)
                  break;
               lstart = System.currentTimeMillis();
//  free
               heap.free(block.ptr);
               lstop = System.currentTimeMillis();
               freeTime += lstop - lstart;
               allocated -= block.size;
            }
            //blocks.remove(n);
         }
         n = Math.abs(ThreadLocalRandom.current().nextInt()%100000);
//  временно отключить
//            if (n == 0)
//                System.out.println(maxSize-allocated);
      }
      heap.stop = System.currentTimeMillis();
      System.out.println(heap.iter + "/" + (heap.stop - start) + "/" + heap.freeTree.size());

      long stop = System.currentTimeMillis();
      System.out.println("malloc time: "+allocTime+" free time: "+freeTime);
      System.out.println("total time: "+(stop-start)+" count: "+count);
      System.out.println(heap.delBlock + "/" + heap.insBlock+"/"+heap.freBlock);
      System.out.println("busyTree.size() = " + heap.busyTree.size());
      System.out.println("freeTree.size() = " + heap.freeTree.size());
      System.out.println("maxHeapSize  = " + heap.maxHeapSize);
      System.out.println("heap.allSize = " + heap.allSize);
//        System.out.println("busyTree = " + heap.busyTree);
      System.out.println("freeTree = " + heap.freeTree);
   }
}
//  static final int maxSize = 900_000_000;
//  malloc time: 2031 free time: 265
//  total time: 2891 count: 1990900
//  malloc time: 2016 free time: 579
//  total time: 2875 count: 2006429

