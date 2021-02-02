package misc.sidnet1964.basetypes;


//        import org.jetbrains.annotations.NotNull;

        import java.util.Arrays;
        import java.util.Iterator;
        import java.util.Map;

//  Реализовать класс DoubleHashTable - хэш таблица с двойным хэшированием
//  При двойном хешировании используются две независимые хеш-функции h1(k) и h2(k).
//  Пусть k — это наш ключ, m — размер нашей таблицы, n mod m — остаток от деления n на m,
//  тогда сначала исследуется ячейка с адресом h1(k), если она уже занята,
//  то рассматривается (h1(k)+h2(k)) mod m, затем (h1(k)+2⋅h2(k)) mod m и так далее.
//  В общем случае идёт проверка последовательности ячеек (h1(k)+i⋅h2(k)) mod m где i=(0,1,...,m−1)
public class DoubleHashTable<K,V> implements Iterable<Map.Entry<K,V>>{
   @Override
   public String toString() {
      return "DHT{" + "table=" + Arrays.toString(table) + '}' + '\n' + "delet=" + Arrays.toString(delet);
   }
   //  ----------------------------------------------
   //  описание итератора
  // @NotNull
   @Override
   public Iterator<Map.Entry<K, V>> iterator() {
      return new MyIterator<>();  //  this.table[0]
   }
   class MyIterator<K,V> implements Iterator<Map.Entry<K,V>>{
      //  DHashItem<K,V> curr;
      private int ind;

      MyIterator(){
         ind = -1;
      }

      @Override
      public boolean hasNext() {
         for (int i = ind+1; i < table.length - 0; i++) {
//                System.out.println((table[i] != null && !delet[i]) + " -> i = " + i + "||" + table[i] + "||" + delet[i]);
            if (table[i] != null && !delet[i]) {
//  вариант 1                        ind = i-1;
//  вариант 2
               ind = i;
               return true;
            }
         }
         return false;
      }

      @Override
      public Map.Entry<K, V> next() {
//  вариант 1            ind++;
//  вариант 2
         try {
            return (Map.Entry<K, V>) table[ind];
         } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
         }
      }
   }
   //  ----------------------------------------------------
   //  описание класса-элемента
   class DHashItem<K,V> implements Map.Entry<K,V> {
      //  переменные класса-элемента
      private K key;
      private V item;

      DHashItem(K key, V item) {
         this.key = key;
         this.item = item;
      }
      @Override
      public K getKey() {
         return key;
      }
      @Override
      public V getValue() {
         return item;
      }
      @Override
      public V setValue(V value) {
         return null;
      }
      @Override
      public String toString() {
         return "{" + key + "/" + item + '}';
      }
   }
   //  ----------------------------------------------------
   //  описание класса
   DHashItem<K,V>[] table;   //  переменная класса
   boolean[] delet;
   private int size;   //  количество элементов
   //  конструктор класса
   DoubleHashTable(int n) { //  n = 101
      table = new DHashItem[n];
      delet = new boolean[n];
      size = 0;
   }
   //  2.1 - добавить пару ключ-значение
   void add(K key, V item) {
      int x = getHash1(key);
      int y = getHash2(key);
      //  на малых знчениях table.length выражение table.length/10 будет = 0
      for (int i = 0; i < (table.length / 10) + 1; i++) {
         x = (x + i * y) % table.length;
         //  проверка свободной ячейки с индексом x
         if (table[x] == null || delet[x]) {
            //  создать объект для размещения
            DHashItem<K,V> li = new DHashItem(key, item);
            //  поместить его в ячейку (x) массива
            table[x] = li;
            delet[x] = false;
            size++; //  количество элементов в таблице
            return; //  все замечательно
         }
//            x = (x + i * y) % table.length;
      }
      resize();
      add(key, item); //  повторный вызов
      //        table.resize(); // ошибка, требуется увеличить размер таблицы
   }
   //  увеличение размера таблицы
   void resize(){
      int oldSize = table.length;
      int newSize = primeNext(oldSize * 2);   //  расчет простого числа
      System.out.println("Расширение таблицы, old = " + oldSize + ", new = " + newSize);
      //  интересная конструкция с объявлением нового массива
      DHashItem<K,V>[] newArray = new DHashItem[newSize];
      System.arraycopy(table, 0, newArray, 0, oldSize);
      table =  newArray;  //  массив увеличенного размера
      boolean[] newDelet = new boolean[newSize];
      System.arraycopy(delet, 0, newDelet, 0, oldSize);
      delet = newDelet;
   }
   //  2.2 - получить значение по ключу
   V get(K key) {
      int x = getHash1(key);
      int y = getHash2(key);
      for (int i = 0; i < table.length; i++) {
//            System.out.println(Integer.compare(table[x].key, key));
         if (table[x] != null)
            if ((int)table[x].key == (int) key && !delet[x])
               return table[x].item;
            else
               return null;
         x = (x + y) % table.length;
      }
      return null;
   }
   //  2.3 - удалить элемент по ключу
   public void remove(K key){
      int x = getHash1(key);
      int y = getHash2(key);
      for (int i = 0; i < table.length; i++) {
         if (table[x] != null)
            if (table[x].key == key) {
               delet[x] = true;
               size--;
            }
            else
               return;
         x = (x + y) % table.length;
      }
   }
   //  2.4 - изменить значение ключа у элемента с key1 на key2
   public void change(K key1, K key2){
      int x = getHash1(key1);
      int y = getHash2(key1);
      for (int i = 0; i < table.length; i++) {
         if (table[x] != null)
            if (table[x].key == key1)
               table[x].key = key2;
            else
               return;
         x = (x + y) % table.length;
      }
   }
   //  2.5 - получить количество элементов
   public int size() {
      return size;
   }

   //  2.6 public Iterator<DoubleHashTable<K,V>> getIterator()
   public MyIterator<K, V> getIterator(){
      return new MyIterator<K,V>();   //  this.table[0]
   }
   //  ----------------------------------------------------
   public int getHash1(K key) {
      return (int)key % table.length;  //  key % table.length
   }
   //  h2(k)=k mod (m−1)+1
   public int getHash2(K key) {
      if (table.length == 1)
         return 1;
      return (int)key % (table.length - 1) + 1;
   }
   //  вычисление простого числа больше заданного
   boolean prime (int n){
      for (int i=2; i*i<=n; i++)
         if (n%i == 0)
            return false;
      return true;
   }
   int primeNext(int n){
      while (!prime(n))
         n++;
      return n;
   }
}
//более 10% при одной серии пробирований -  что значит одна серия пробирований?
//[07:10] mazneff: имеется ввиду вставка одного конкретного ключа. А не то, что эти коллизии накопились от нескольких вставок
