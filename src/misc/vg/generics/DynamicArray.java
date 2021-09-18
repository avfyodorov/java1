package misc.vg.generics;

public class DynamicArray<T> {
    private T[] blockArray;
    int initCapacity = 2;
    int capacity;
    int realSize;

    public DynamicArray() {
        blockArray = (T[])new Object[initCapacity];
        capacity = initCapacity;
        realSize = 0;
    }

    void checkSize() {
        if (realSize + 1 > capacity) {
            T[] tempArray = (T[]) new Object[2 * capacity];
            System.arraycopy(blockArray, 0, tempArray, 0, capacity);
            blockArray = (T[]) new Object[2 * capacity];
            System.arraycopy(tempArray, 0, blockArray, 0, capacity);
            capacity = 2 * capacity;
        }
    }

    public void add(T o) {
        checkSize();
        blockArray[realSize++] = o;
    }

    public void insert(int pos, T o) {
        checkSize();
        if (pos<realSize-1) {
            T[] tempArray = (T[]) new Object[realSize - pos];
            System.arraycopy(blockArray, pos, tempArray, 0, realSize - pos);
            blockArray[pos] = o;
            System.arraycopy(tempArray, 0, blockArray, pos + 1, realSize - pos);
            realSize++;
        } else {
            realSize = pos+1;
            checkSize();
            blockArray[pos] = o;
        }
    }

    public void remove(int pos) {
        if (pos<realSize-1) {
            realSize--;
            T[] tempArray = (T[]) new Object[realSize - pos];
            System.arraycopy(blockArray, pos + 1, tempArray, 0, realSize - pos);
            System.arraycopy(tempArray, 0, blockArray, pos, realSize - pos);
        } else {
            System.out.println("Удаление невозможно");
        }
    }

    public T get(int pos) {
        return blockArray[pos];
    }

    public int size() {
        return realSize;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> da = new DynamicArray<>();
        da.add(0);
        da.add(1);
        da.add(2);
        da.add(3);
        da.add(4);
        da.add(5);
        for (int i = 0; i<da.size(); i++) {
            System.out.print(da.get(i) + " ");
        }
        System.out.println();
        // pos = {0..}
        da.insert(9, 9);
        for (int i = 0; i<da.size(); i++) {
            System.out.print(da.get(i) + " ");
        }
        System.out.println();
        da.insert(7, 7);
        for (int i = 0; i<da.size(); i++) {
            System.out.print(da.get(i) + " ");
        }
        System.out.println();
        // pos = {0..realSize-1}
        da.remove(0);
        for (int i = 0; i<da.size(); i++) {
            System.out.print(da.get(i) + " ");
        }
    }
}
