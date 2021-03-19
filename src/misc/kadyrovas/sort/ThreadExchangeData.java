package misc.kadyrovas.sort;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Exchanger;

class ThreadExchangeData implements Runnable {
   Exchanger exWithMain;
   int size;
   BufferedReader[] readers;

   ThreadExchangeData(Exchanger exchanger, BufferedReader[] readers, int size) {
      this.exWithMain = exchanger;
      this.readers = readers;
      this.size = size;
   }

   @Override
   public void run() {
      Heap heap = new Heap();
      EntryHeap entryHeap;

      for (int i = 0; i < size; i++) {
         try {
            heap.add(new EntryHeap(i, Integer.valueOf(readers[i].readLine())));
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      while (heap.size() != 0) {
         entryHeap = (EntryHeap) heap.poll();
         try {
            exWithMain.exchange(entryHeap.value);
            if (readers[entryHeap.key].ready())
               heap.add(new EntryHeap(entryHeap.key, Integer.valueOf(readers[entryHeap.key].readLine())));
            else
               readers[entryHeap.key].close();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      try {
         exWithMain.exchange(null);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}




