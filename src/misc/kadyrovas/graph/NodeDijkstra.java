package misc.kadyrovas.graph;

public class NodeDijkstra<K, V extends Comparable> {
    K key; //Индекс вершины
    V value; //Вес вершины

    public NodeDijkstra(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
