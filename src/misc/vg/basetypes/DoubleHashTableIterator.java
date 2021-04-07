package misc.vg.basetypes;

import java.util.Iterator;

public class DoubleHashTableIterator<T> implements Iterator<T> {
    private final T[] table;
    private int pos;

    DoubleHashTableIterator(T[] table) {
        this.table = table;
        pos = 0;
    }

    @Override
    public boolean hasNext() {
        while (pos < table.length) {
            if (table[pos] == null) {
                pos = pos+1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        pos = pos+1;
        return table[pos-1];
    }
}
