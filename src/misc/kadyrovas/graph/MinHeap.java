package misc.kadyrovas.graph;

public class MinHeap<K,V extends Comparable> {
    private NodeDijkstra[] nodeHeap;
    private int size;
    private int maxSize;

    public MinHeap() {
        this.size = 0;
        this.maxSize = 16;
        this.nodeHeap = new NodeDijkstra[maxSize];
    }

    public void change(K key, V value) {
        for (int i = 0; i < size; i ++)
            if (nodeHeap[i].key == key){
                nodeHeap[i].value = value;
                return;
            }
    }

    public void put(NodeDijkstra node) {
        //Запись нового value в кучу
        size++;

        if (size > maxSize) increase();
        nodeHeap[size - 1] = node;
        if (size > 1) shiftUp();
    }

    private void shiftUp() {

        int parent = size >> 1;
        int sun = size;

        while (nodeHeap[parent - 1].value.compareTo(nodeHeap[sun - 1].value)>0) {
            swap(parent - 1, sun - 1);
            sun = parent;
            if (sun > 1) parent = sun >> 1;
        }
    }

    private void increase() {
        NodeDijkstra[] newValueHeap;
        maxSize *= 2;
        newValueHeap = new NodeDijkstra[maxSize];

        for (int i = 0; i < size - 1; i++)
            newValueHeap[i] = nodeHeap[i];

        nodeHeap = newValueHeap;
    }

    private void shiftDown(int index) {
        int son = index == 0 ? 1 : ((index + 1) << 1) - 1;
        while (son < size && nodeHeap[index].value.compareTo(nodeHeap[son].value)>0 ||
                son + 1 < size && nodeHeap[index].value.compareTo(nodeHeap[son + 1].value)>0) {

            if (son + 1 < size && nodeHeap[son + 1].value.compareTo(nodeHeap[son].value) <0)
                index = swap(index, son + 1);
            else
                index = swap(index, son);
            son = ((index + 1) << 1) - 1;
        }
    }

    public void delete(int index) {
        nodeHeap[index] = nodeHeap[size - 1];
        size--;
        shiftDown(index);
    }

    private int swap(int index1, int index2) {
        NodeDijkstra helper = nodeHeap[index1];
        nodeHeap[index1] = nodeHeap[index2];
        nodeHeap[index2] = helper;
        return index2;
    }

    public K getKey(int index) {
        return (K) nodeHeap[index].key;
    }

    public int getSize() {
        return size;
    }

}
