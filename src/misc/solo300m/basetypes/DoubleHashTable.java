package misc.solo300m.basetypes;

import java.util.Arrays;
import java.util.Iterator;

public class DoubleHashTable<K,T> implements Iterable<T>{
    public class TableItem<K,T> {
        private T item;
        private K key;
        public TableItem(K key,T item) {
            this.item = item;
            this.key = key;
        }

        public T getItem() {
            return item;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "item = " + item + ", key = " + key;
        }

    }

    Object [] table;
    int startTableSize = 101;
    public DoubleHashTable(){
        table = new Object[startTableSize];
    }
    public int getHash(K key) {
        return (int)key % table.length;
    }
    public int getProbirHash(K key){
        double d = 0.6180339887*(int)key;
        return(int)(table.length*(d-Math.floor(d)));
    }
    private void growTable(){
        SimpleNumber sim = new SimpleNumber(table.length*2);
        int newSize;
        if(sim.maxSimle()!=0)
            newSize = sim.maxSimle();
        else
            newSize = sim.minSimle();
        table = Arrays.copyOf(table,newSize);
    }
    private int getColisions(){
        return (int)(table.length*0.1);
    }
    public void add(K key , T value){
        int index = getHash(key);
        int step = getProbirHash(key);
        int colisions = getColisions();
        int countColisions = 0;
        int sumStep = 0;
        while (table[index+sumStep]!=null){
            sumStep += step;
            if(countColisions < colisions)
                countColisions++;
            else{
                growTable();
                index = getHash(key);
                step = getProbirHash(key);
                colisions = getColisions();
                countColisions = 0;
                sumStep = step;
            }
        }
        table[index+sumStep] = new TableItem<K,T>(key,value);
    }
    public T get(K key){
        int index = getHash(key);
        int step = getProbirHash(key);
        int sumStep = step;
        while(table[index+sumStep] != null && ((TableItem<K,T>)(table[index+sumStep])).getKey() != key){
            sumStep += step;
            if(index+sumStep >= table.length)
                return null;
        }
        if(table[index+sumStep]==null)
            return null;
        return ((TableItem<K,T>)(table[index+sumStep])).getItem();
    }
    public void remove(K key){
        int index = getHash(key);
        int step = getProbirHash(key);
        int sumStep = step;
        while(table[index+sumStep] != null && ((TableItem<K,T>)(table[index+sumStep])).getKey() != key){
            sumStep += step;
            if(index+sumStep >= table.length) {
                break;
            }
        }
        if(table[index + sumStep] == null)
            System.out.println("Нет элемента с ключем = " + key);
        else {
            table[index + sumStep] = null;
        }
    }
    public int size(){
        int rez = 0;
        for(int i = 0; i < table.length; i++){
            if (((TableItem<K,T>)(table[i])) != null){
                rez++;
            }
        }
        return rez;
    }
    public void change(K key1, K key2){
        int index = getHash(key1);
        int step = getProbirHash(key1);
        int sumStep = step;
        while(table[index+sumStep] != null && ((TableItem<K,T>)(table[index+sumStep])).getKey() != key1){
            sumStep += step;
            if(index+sumStep >= table.length) {
                break;
            }
        }
        if(table[index + sumStep] == null)
            System.out.println("Нет элемента с ключем = " + key1);
        else {

            T tmpItem = ((TableItem<K,T>)(table[index + sumStep])).getItem();
            table[index + sumStep] = null;
            add(key2,tmpItem);
        }
    }

    public Iterator<T> iterator(){
        Iterator<T> iterator = new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                if(index < table.length)
                    return true;
                else
                    return false;
            }

            @Override
            public T next() {
                while(hasNext() && table[index]==null){
                    index++;
                }
                if(index<table.length)
                    return ((TableItem<K,T>)(table[index++])).getItem();
                else
                    return null;
            }
        };
        return iterator;
    }

}
class T1{
    public static void main(String[] args) {
        DoubleHashTable<Integer,Integer> tab = new DoubleHashTable<>();
        System.out.println(tab.size());

        tab.add(1,100);
        tab.add(2,200);
        tab.add(3,300);
//        tab.add(4,400);
//        tab.add(5,500);
//        tab.add(6,600);
//        tab.add(7,700);
//        tab.add(8,800);
//        tab.add(9,900);
//        tab.add(10,1000);
//        tab.add(11,1100);
        tab.add(12,1300);
        tab.add(12,130);
        for(Integer s: tab) {
            if(s!=null)
                System.out.println(s);
        }
        System.out.println("Value: "+tab.get(3));
//        tab.change(100,200);
//        tab.remove(300);
        for (int i = 1; i <= 8; i++)
            System.out.println(tab.get(i));

    }
}