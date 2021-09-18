package misc.vova777.basetypes;

//Реализовать класс DoubleHashTable - хэш таблица с двойным хэшированием
//        В качестве размера таблицы выбирать простое число, первоначальное значение 101
//        При количестве коллизий более 10% при одной серии пробирований - перестраивать таблицу, увеличивая размер
//        Стратегия роста - удвоение размера, но с учетом правила - размер таблицы простое число. Т.е. искать ближайшее ,большее простое
//        Ключи должны реализовывать интерфейс
//          interface HashValue {
//          int getHash();
//
//}
//Таким образом, мы унифицируем ключи любого типа, приведя их к целочисленному.
//        Реализовать 2 класса ключей:
//        - IntKey - алгоритм хэширования может быть любой, можно вернуть просто сам число.
//        - StringKey - выбрать что-то, из функций, представленных в лекции,
//        Само двойное хэширование реализуем уже после получения целочисленного значения через getHas().
//        В самой хэш-таблице должно быть 2 функции хэширования: деление и умножение
//        Методы
//        2.1 public void add(K key, V value) - добавить пару ключ-значение
//        2.2 public V get(K key) - получить значение по ключу
//        2.3 public void remove(K key) - удалить элемент по ключу
//        2.4 public void change(K key1, Key key2) - изменить значение ключа у элемента с key1 на key2
//        2.5 public int size() - получить количество элементов
//        2.6 реализовать интерфейс Iterable<T>


import java.util.Arrays;
import java.util.Iterator;

public class DoubleHashTable<K, V> implements Iterable {
    static int bufferSize = 101; // размер самого массива, сколько памяти выделено под хранение нашей таблицы
    static int countCollision = 0;
    final int percentCollisionBeforeIncrease = 10;
    int size = 0;// фактический размер (колличество пар)
    int sizeWithoutDelete; // сколько элементов у нас сейчас в массиве (без учета deleted)
    int sizeWithDelete; // сколько элементов у нас сейчас в массиве (с учетом deleted)
    //эти значения понадобились бы ели при опр колличестве удалееных мы перерасчитывали позицию всех элементов обнулив удалленны,
    // но заданием это не треб-ся
    Item[] array = new Item[bufferSize];
    int iteration = 0;

    @Override
    public Iterator iterator() {
        Item item = null;
        for (int i = 0; i < array.length; i++){
            iteration++;
            if (array[i] != null && array[i].state) {item = array[i]; break;}

        }
        if (item == null) throw new UnsupportedOperationException("table is empty");
        return item;
    }



    class Item<K, V> implements Iterator<Item> {

        K key;
        V value;
        boolean state = true;
        Item (K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + " - " + value;
        }


        @Override
        public boolean hasNext() {
            if  (iteration  < array.length) return true;
            else if (iteration == array.length && array[array.length-1] != null && array[array.length-1].state)
            {iteration++; return true;} //это условие чтобы если последний элемент null, крайний элемент не повторялся 2 раза
            else return false;
        }

        Item it = this;

        @Override
        public Item next() {
            Item result = it;
            for (int i = iteration ; i < array.length; i++){
                iteration++;
                if (array[i] != null && array[i].state) {it = array[i]; break;}

            }

            return result;
        }
    }

    public void add(K key, V value){
        HashValue hashValue = getObjectNeedTypeHashValue(key);
        int hash = hashValue.getFirstHash();
        if (array[hash] == null || !array[hash].state) {array[hash] = new Item(key, value); size++; return;}
        else{
            int n = hashValue.getHash();
            for (int i = (hash); i < array.length; ){
                if (array[i] != null && array[i].key.equals(key)) { array[i] = new Item(key, value); return;}
                if (array[i] == null || !array[hash].state)
                {array[i] = new Item(key, value); countCollision = 0; size++; return;}
                else {
                    countCollision++;
                    if (isPercentCollision()) {increaseArray(); add(key, value);}
                }
                i+=n;
                if (i > array.length) {increaseArray(); add(key, value);}
            }
            countCollision = 0;
        }
    }

    public V get(K key){
        HashValue hashValue = getObjectNeedTypeHashValue(key);
        int hash = hashValue.getFirstHash();
        if (array[hash] != null && key.equals(array[hash].key) && array[hash].state) return (V) array[hash].value;
        else {
            int n = hashValue.getHash();
            for (int i = hash + n; i < array.length;){
                if (array[i] == null) return null;
                if (key.equals(array[i].key) && array[i].state) return (V) array[i].value;
                i += n;
            }
        }
        //throw new NullPointerException("Value with such key have not found");
        return null;
    }

    public void remove(K key){
        HashValue hashValue = getObjectNeedTypeHashValue(key);
        int hash = hashValue.getFirstHash();
        if (array[hash] != null && key.equals(array[hash].key) && array[hash].state){array[hash].state = false; size--;}
        else {
            int n = hashValue.getHash();
            for (int i = hash + n; i < array.length;){
                if (array[i] != null && key.equals(array[i].key) && array[i].state){array[i].state = false; size--; return;}
                i += n;
            }
            System.out.println("Not value with such key");
        }
    }

    public void change(K key1, K key2){//надо удалять и пересчитывать хэшфункцию. ПЕРЕДелать метод
        V value = get(key1);
        remove(key1);
        add(key2, value);
    }

    public int size(){
        return size;
    }

    private void increaseArray(){
      bufferSize = bufferSize * 2 + 1;
      Item[] arr = new Item[bufferSize];
        for (int i = 0; i < array.length; i++){
            if (!array[i].state) continue;// не копируем в новый массив и окончательно избавляемся от удаленного элемента;
            if (array[i] == null) continue;
            HashValue hashValue = getObjectNeedTypeHashValue((K) array[i].key);
            int firstHash = hashValue.getFirstHash();
            if (arr[firstHash] == null) arr[firstHash] = new Item((K) array[i].key, (V) array[i].value);
            else{
                int n = hashValue.getHash();
                for (int j = (firstHash + n); j < arr.length;){
                    if (arr[j] == null){ arr[j] = new Item((K) array[i].key, (V) array[i].value); array = arr;  return;}
                    i+=n;
                }
            }
        }
        array = arr;
    }

    private boolean isPercentCollision(){
            if (((countCollision * 100) / bufferSize)> percentCollisionBeforeIncrease) return true;
            else return false;
    }

    private HashValue getObjectNeedTypeHashValue(K key){
        HashValue hashValue;
        if (key instanceof String) hashValue = new StringKey((String) key, bufferSize);
        else hashValue = new IntKey((int) key, bufferSize);
        return hashValue;
    }




    @Override
    public String toString() {
        return "DoubleHashTable{" +
                "array=" + Arrays.toString(array) +
                '}';
    }


    public static void main(String[] args) {
        DoubleHashTable table = new DoubleHashTable();
        table.add(1, 123);
        table.add(22, 124);
        table.add(33, 125);
        table.add(44, 127);
        table.add(55, 130);
        table.add(555, 7777777);
//        System.out.println(bufferSize);
//        System.out.println("колличество значений: " + table.size);
//        System.out.println(table.get(555));
        System.out.println(table);
//        table.remove(33);
//        System.out.println("колличество значений: " + table.size);
//        System.out.println(table.get(33));
//        System.out.println("колличество значений: " + table.size);
//        table.change(555, 777);
//        System.out.println("Revise of change. Must get value 7777777. This value: " + table.get(777));
//        System.out.println("колличество значений: " + table.size());
        Iterator iterator = table.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}


interface HashValue {
        int getFirstHash();
        int getHash();
    }

class StringKey implements HashValue{
    String key;
    int default_size;

    public StringKey(String key, int default_size) {
        this.key = key;
        this.default_size = default_size;
    }
    public StringKey(String key) {
        this.key = key;
        this.default_size = 123;
    }

    public int getFirstHash() {
        int hash = 0;
        for(int i = 0; i < key.length(); i++)
            hash = (31 * hash + key.charAt(i)) % default_size;
        return hash;
    }

    @Override
    public int getHash() {
        return (int) (getFirstHash() * 0.1 % default_size);
    }
}

class IntKey implements HashValue{
    int key;
    int default_size;

    public IntKey(int key, int default_size) {
        this.key = key;
        this.default_size = default_size;
    }
    public IntKey(int key) {
        this.key = key;
        this.default_size = 123;
    }

    @Override
    public int getFirstHash(){
        final double A = (Math.sqrt(5) - 1) / 2;
        double d = A * key;
        return (int)(default_size * (d - Math.floor(d)));
    }

    @Override
    public int getHash() {
        return (getFirstHash() % default_size);
    }

}





