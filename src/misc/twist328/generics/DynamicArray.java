package misc.twist328.generics;

public class DynamicArray<T> {

    int blocksize;
    T[] array;
    int size;

    public DynamicArray(int initsize, int blocksize) {

        array = (T[]) new Object[initsize];
        this.blocksize = blocksize;
        size = 0;
    }

    public DynamicArray() {
        this.array = array;
    }

    public void add(T item) {

        if (size >= array.length) {
            T[] newArray = (T[]) new Object[array.length + blocksize];
            copyData(array, newArray);
            array = newArray;
        }
        array[size++] = (T) item;
    }

    void copyData(T[] src, T[] dst) {
        System.arraycopy(src, 0, dst, array.length, blocksize);
        dst = src;
    }

    public void set(T item, int index) {
        array[index] = (T) item;
    }

    public void remove(int pos) {
        if (pos >= array.length)
            throw new RuntimeException("Position " + pos + " is greater than length " + array.length + " of array");
        if (pos < 0) throw new RuntimeException("Position " + pos + " is less than first element index");
        System.arraycopy(blocksize, pos + 1, blocksize, pos, array.length - pos - 1);
        size -= 1;
    }

    public void insert(T item, int index) {
        if (index >= array.length)
            throw new RuntimeException("Position " + index + " is greater than length " + array.length + " of array");
        if (index < 0) throw new RuntimeException("Position " + index + " is less than first element index");
        size++;
        T[] temp = (T[]) array;
        array = (T[]) new Object[size];
        for (int i = 0; i < index; ++i)
            array[i] = (T) temp[i];
        array[index] = item;
        for (int i = index + 1; i < size; ++i)
            array[i] = (T) temp[i];
    }

    public T get(int index) {
        if (index >= array.length)
            throw new RuntimeException("Position " + index + " is greater than length " + array.length + " of array");
        if (index < 0) throw new RuntimeException("Position " + index + " is less than first element index");
        return array[index];
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> arr=new DynamicArray<>();
        arr.add(1);
    }
}