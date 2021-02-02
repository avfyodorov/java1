

package misc.topograh95.gc;

//import ru.progwards.java2.lessons.gc.OutOfMemoryException;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;
// пустой класс ничего не делает
//  malloc time: 40 free time: 0
//  total time: 2168 count: 3181609

// образец для подражания и поклонения
//  malloc time: 4248 free time: 458
//  total time: 5368 count: 4292964

// это MY
//  malloc time: 536 free time: 78
//  total time: 1035 count: 4283842

public class HeapTest {
    static final int maxSize = 1_932_735_283;
//    static final int maxSize = 932_735_283;

    static final int maxSmall = 10;
    static final int maxMedium = 100;
    static final int maxBig = 1000;
    static final int maxHuge = 10000;
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

    public static <OutOfMemoryException extends Throwable> void main(String[] args)
      throws
            InvalidPointerException,
      OutOfMemoryException {
      Heap heap = new Heap(maxSize);
        ArrayDeque<Block> blocks = new ArrayDeque<>();
        int count = 0;
        int allocTime = 0;
        int freeTime = 0;

        long start = System.currentTimeMillis();
        // alloc and free 30% random
        while ((maxSize - allocated) > 50000) {
            long lstart, lstop;
            int size = getRandomSize();
            allocated += size;
            count++;
            lstart = System.currentTimeMillis();
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
                    heap.free(block.ptr);
                    lstop = System.currentTimeMillis();
                    freeTime += lstop - lstart;
                    allocated -= block.size;
                }
                //blocks.remove(n);
            }
            n = Math.abs(ThreadLocalRandom.current().nextInt()%100000);
            if (n==0)
                System.out.println(maxSize-allocated);
        }
        long stop = System.currentTimeMillis();
        System.out.println("malloc time: "+allocTime+" free time: "+freeTime);
        System.out.println("total time: "+(stop-start)+" count: "+count);
    }
}
