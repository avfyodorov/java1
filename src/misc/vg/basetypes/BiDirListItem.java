package misc.vg.basetypes;

public class BiDirListItem<T> {
    private final T item;
    private BiDirListItem<T> next;
    private BiDirListItem<T> prev;

    BiDirListItem(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }

    void setNext(BiDirListItem<T> item) {
        next = item;
    }

    void setPrev(BiDirListItem<T> item) {
        prev = item;
    }

    BiDirListItem<T> getNext() {
        return next;
    }

    BiDirListItem<T> getPrev() {
        return prev;
    }
}
