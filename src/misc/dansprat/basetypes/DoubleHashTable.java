package misc.dansprat.basetypes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class DoubleHashTable<K extends HashValue, V> implements Iterable<V> {

   @Override
   public Iterator<V> iterator() {
      return new Iterator<V>() {
         int current = -1;
         int next = -1;

         @Override
         public boolean hasNext() {
            for (int i = current + 1; i < table.length; ++i) {
               if (table[i] != null) {
                  next = i;
                  return true;
               }
            }
            next = table.length;
            return false;
         }

         @Override
         public V next() {
            if (next > current) {
               current = next;
               return table[next].value;
            }
            current = next;
            return table[current].value;
         }
      };
   }

   @Override
   public void forEach(Consumer<? super V> action) {
      for (var x : table) {
         if (x != null && x.status != Status.DELETED)
            action.accept(x.value);
      }
   }

   enum Status {EMPTY, DELETED, BUSY}

   ;

   public class Pair<K, V> {
      K key;
      V value;
      Status status;

      private Pair() {
         status = Status.EMPTY;
      }

      private Pair(K key, V value) {
         this.key = key;
         this.value = value;
         status = Status.BUSY;
      }

      private void setValue(K key, V value) {
         this.key = key;
         this.value = value;
         status = Status.BUSY;
      }

      private V getValue() {
         return value;
      }

   }

   private static final long UINT_MAX = 4294967295L;

   public static long unsignedInt(long num) {
      return num % UINT_MAX;
   }

   public static int toInt(long num) {
      return (int) (num % Integer.MAX_VALUE);
   }

   private double A = (Math.sqrt(5) - 1) / 2;
   private int size;
   private Pair<K, V> table[];


   public DoubleHashTable() {
      size = 0;
      table = new Pair[101];
   }

   private int mulHash(int k) {
      double d = A * k;
      return (int) (table.length * (d - Math.floor(d)));
   }

   private int divHash(int k) {
      return Math.abs(k) % table.length;
   }

   private int findGrSimple(int n, double mul) {
      int size = (int) (n + n * mul); //
      boolean arr[] = new boolean[size];
      arr[1] = false;
      for (int i = 2; i < Math.sqrt(n); ++i) {
         if (arr[i] != true)
            for (int j = 2 * i; j < arr.length; j += i)
               arr[j] = true;
      }
      for (int k = n; k < size; ++k) {
         if (arr[k] == false)
            return k;
      }
      return findGrSimple(n, mul + 0.1);
   }

   public void add(K key, V value) {
      int tenPercents = (int) (table.length * 0.1);
      int steps = 0;
      int hash = divHash(key.getHash());
      int interval = mulHash(key.getHash());
      do {
         if (table[hash] == null || table[hash].status == Status.DELETED) {
            table[hash] = new Pair<>(key, value);
            size++;
            return;
         }
         steps++;
         hash = (hash + interval) % table.length;
      } while (steps < tenPercents);
      Pair<K, V> newList[] = new Pair[findGrSimple(2 * table.length, 0.1)];
      Pair<K, V> tempList[] = table;
      table = newList;
      size = 0;
      for (var k : tempList) {
         if (k != null && k.status == Status.BUSY) {
            this.add(k.key, k.value);
         }
      }
      this.add(key, value);
   }

   public V get(K key) {
      int interval = mulHash(key.getHash());
      int hash = divHash(key.getHash());

      while (table[hash] != null) {
         if (table[hash].key.equals(key) && table[hash].status == Status.BUSY)
            return table[hash].getValue();
         hash = (hash + interval) % table.length;
      }
      return null;
   }

   public void remove(K key) {
      int interval = mulHash(key.getHash());
      int hash = divHash(key.getHash());
      while (table[hash] != null) {
         if (table[hash].key.equals(key) && table[hash].status == Status.BUSY) {
            table[hash].status = Status.DELETED;
            size--;
            return;
         }
         hash = (hash + interval) % table.length;
      }
   }

   public int size() {
      return size;
   }

   public void change(K key1, K key2) {
      int interval = mulHash(key1.getHash());
      int hash = divHash(key1.getHash());
      while (table[hash] != null) {
         if (table[hash].key.equals(key1) && table[hash].status == Status.BUSY) {
            table[hash].key = key2;
         }
         hash = (hash + interval) % table.length;
      }
   }

   public static void main(String[] args) {
      DoubleHashTable<IntKey, Integer> t = new DoubleHashTable<>();
      for (int i = 0; i < 13; i++) {
       t.add(new IntKey(i * 2), i);
      }
      t.change(new IntKey(0), new IntKey(11));

      System.out.println(t.toString());
      for (Integer integer : t) {
         System.out.print(integer + " ");
      }
      System.out.println();

      DoubleHashTable<StringKey, Integer> t2 = new DoubleHashTable<>();
      for (int i = 0; i < 105; i++) {
         t2.add(new StringKey(Integer.toString(2 * i)), i);
      }
   }
/*
    public static void main(String[] args) {
        final Random random = new Random();
        random.setSeed(28);
        DoubleHashTable<IntKey,String> hashTable = new DoubleHashTable<>();
        for (int i = 0;i<24;++i) {
            hashTable.add(new IntKey(random.nextInt()), "Число " + i);
        }
        hashTable.forEach(System.out::println);
    }

 */
}
