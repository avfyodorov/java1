package misc.vova777.generics;

import java.util.Arrays;

public class DynamicArray<T> {
//Реализовать класс по типу BlockArray - обобщающий динамический массив, растущий блоками, на основе обычного
// статического массива. Стратегия роста - при полном заполнении текущего объема, новый размер массива должен
// быть в 2 раза больше предыдущего.
//3.1 в классе разместить private переменную - массив обобщающего типа.
//3.2 конструктор - по умолчанию.
//3.2 метод add - добавляет элемент в конец массива.
//3.3 метод с именем insert - добавляет элемент в заданную позицию позицию массива. Параметр int pos - первый,
// параметр с элементом массива - второй.
//3.4 метод remove(int pos) - удаляет элемент в позиции pos массива.
//3.5 метод с get(int pos) - возвращает элемент по индексу pos.
//3.6 метод с size() - возвращает текущий реальный объем массива.
    private static int size = 0;
    private T[] array = (T[]) new Object[1];

    public void add(T t){
        if (isEndDynamicArray()) array = addBlockWithUsingOtherMethod(array);
        array[size] = t;
        size ++;
    }

    public void insert(int pos, T t){
        getArrayOutOfBoundsException(size, pos);
        if (isEndDynamicArray()) array = addBlockWithUsingOtherMethod(array);

        for (int i = size - 1; i >= pos; i--){
            array[i+1] = array[i];
        }
        array[pos] = t;
        size ++;
    }

    public void remove(int pos){
        getArrayOutOfBoundsException(size, pos);
        for (int i = pos; i < size(); i++){
            array[i] = array[i+1];
        }
        size --;
    }

    public T get(int pos){
        getArrayOutOfBoundsException(size, pos);
        return array[pos];
    }

    public int size(){
        return size;
    }

    public int realSize(){
        return array.length;
    }

    private T[] addBlockWithUsingOtherMethod(T[] array){
       array  = Arrays.copyOf(array, array.length * 2);
       return array;
    }

    private boolean isEndDynamicArray(){
        return ((array[array.length - 1]) != null);
    }

     private void getArrayOutOfBoundsException(int size, int pos){
         if (pos >= size()) throw new IndexOutOfBoundsException("Выход за границу массива. Длина массива " + size +
                 ", индекс " +  pos);
         if (pos < 0 ) throw new IndexOutOfBoundsException("Выход за границу массива. Индекс массива не может быть отрицательным");
    }

    @Override
    public String toString() {
        return "DynamicArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public static void main(String[] args) throws Exception {
//        Integer[] arr = new Integer[10];
//        System.out.println(Arrays.toString(arr));
//        List list = new ArrayList();
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(12);
        dynamicArray.add(3);
        dynamicArray.add(5);
        dynamicArray.add(6);
        dynamicArray.add(7);
        System.out.println("Метод add(): " + dynamicArray.toString());
        System.out.println("Метод size(): " + dynamicArray.size());
        System.out.println("Метод realSize(): " + dynamicArray.realSize());

        dynamicArray.insert(2,122);// можно ли вставить в конец;
        System.out.println("Метод insert(): " + dynamicArray.toString());
        System.out.println("Метод size(): " + dynamicArray.size());
        System.out.println("Метод realSize(): " + dynamicArray.realSize());

       dynamicArray.remove(0);
       System.out.println("Метод remove(): " + dynamicArray.toString());
       System.out.println("Метод size(): " + dynamicArray.size());
       System.out.println("Метод realSize(): " + dynamicArray.realSize());

       System.out.println("Метод get(): " + dynamicArray.get(0));
        //System.out.println("Метод size(): " + dynamicArray.size());
      // System.out.println("Метод realSize(): " + dynamicArray.realSize());
    }
}
