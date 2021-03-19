package misc.kadyrovas.graph;

import java.util.ArrayList;
import java.util.List;

class Node<N,E> {
    N info; // информация об узле
//    List<Edge<N, E>> in; // массив входящих ребер
//    List<Edge<N, E>> out; // массив исходящих ребер
    List<Edge<N, E>> connection; // массив исходящих ребер

    public Node(N info) {
        this.info = info;
//        in = new ArrayList<>();
//        out = new ArrayList<>();
        this.connection = new ArrayList<>();
    }

    @Override
    public String toString() {
        return info.toString();
    }
}