package misc.kadyrovas.graph;


class Edge<N, E> {
    E info; // информация о ребре
    Node<N, E> out; // вершина, из которой исходит ребро
    Node<N, E> in; // вершина, в которую можно попасть
    // по этому ребру
    double weight; // стоимость перехода

    @Override
    public String toString() {
        return info.toString();
    }

    public Edge(E info, Node<N, E> out, Node<N, E> in, double weight) {
        this.info = info;
        this.out = out;
        this.in = in;
        this.weight = weight;
    }
}