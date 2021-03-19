package misc.kadyrovas.graph;

import java.util.ArrayList;
import java.util.List;

class Graph<N, E> {
    List<Node<N, E>> nodes;
    List<Edge<N, E>> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }
}