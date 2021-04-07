package misc.vg.basetypes;

import java.util.Iterator;

public class BiDirListIterator<T> implements Iterator<T> {
    private BiDirListItem<T> current;

    BiDirListIterator(BiDirListItem<T> head) {
        current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T next = current.getItem();
        current = current.getNext();
        return next;
    }
}
