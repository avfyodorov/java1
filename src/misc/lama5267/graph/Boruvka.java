package misc.lama5267.graph;

import java.util.*;

public class Boruvka<N, E> {

    public List<Edge<N, E>> minTree(Graph<N, E> graph) {
        if (graph.nodes == null || graph.edges == null)
            throw new IllegalArgumentException("Граф не может быть null!");
        List<Edge<N, E>> edgeList = new LinkedList<>(); /* лист для ребер минимального остовного дерева */
        TreeSet<Edge<N, E>> edgeSet = new TreeSet<>(Comparator.comparingDouble(o -> o.weight)); /* сет оставшихся ребер для поиска минимального ребра наружу */
        edgeSet.addAll(graph.edges);

        int index = 0;
        while (index < graph.nodes.size()) { /* крутимся в цикле по количеству узлов в графе */
            Edge<N, E> minEdge = findMinEdgeFromNode(graph.nodes.get(index)); /* для конкретного узла находим минимальное ребро */
            if (merge(find(minEdge.in), find(minEdge.out), minEdge)) { /* отправляем на склейку узлы и если true, добавляем ребро в MST */
                edgeList.add(minEdge);
                edgeSet.remove(minEdge); /* удаляем использованное ребро */
            }
            index++;
        }

        while (edgeList.size() < graph.nodes.size() - 1) { /* ищем минимальное ребро между связными компонентами */
            edgeList.add(findMinEdgeFromSet(edgeSet));
        }

        return edgeList;
    }

    private Edge<N, E> findMinEdgeFromNode(Node<N, E> node) { /* поиск минимального по весу ребра в узле */
        Edge<N, E> minEdge = null;
        double min = Double.MAX_VALUE;
        for (Edge<N, E> edge : node.out) {
            if (edge.weight < min) {
                min = edge.weight;
                minEdge = edge;
            }
        }
        return minEdge;
    }

    private Edge<N, E> findMinEdgeFromSet(Set<Edge<N, E>> edgeSet) { /* поиск минимального по весу ребра в сете оставшихся ребер */
        Edge<N, E> minEdge = null;
        double min = Double.MAX_VALUE;
        for (Edge<N, E> edge : edgeSet) {
            if (!find(edge.in).equals(find(edge.out))) /* проверяем, чтобы вершины ребра не принадлежали одной компоненте */
                if (edge.weight < min) {
                    minEdge = edge;
                    edgeSet.remove(edge); /* удаляем найденное минимальное ребро из оставшихся рёбер */
                    break; /* так как сет сортированный по весу ребер, после первого соответствия прерываем цикл */
                }
        }
        return minEdge;
    }

    private Node<N, E> find(Node<N, E> node) { /* ищем корневой узел компоненты */
        if (node.next != null)
            while (node.next != null) { /* крутимся в цикле пока next узла не null */
                node = node.next;
            }
        return node;
    }

    private boolean merge(Node<N, E> u, Node<N, E> v, Edge<N, E> minEdge) { /* склеиваем узлы - присваиваем ссылку next */
        if (!u.equals(v)) { /* если корневые узлы не равны */
            u.next = minEdge.out;
            return true;
        }
        return false;
    }

    static class Node<N, E> {
        N info; // информация об узле
        List<Edge<N, E>> in; // массив входящих ребер
        List<Edge<N, E>> out; // массив исходящих ребер
        Node<N, E> next;

        public Node() {
            in = new LinkedList<>();
            out = new LinkedList<>();
            next = null;
        }
    }

    static class Edge<N, E> {
        E info; // информация о ребре
        Node<N, E> out; // вершина, из которой исходит ребро
        Node<N, E> in; // вершина, в которую можно попасть по этому ребру
        double weight; // стоимость перехода

        @Override
        public String toString() {
            return "Edge{" +
                    "out=" + out.info +
                    ", in=" + in.info +
                    ", weight=" + weight +
                    '}';
        }
    }

    static class Graph<N, E> {
        List<Node<N, E>> nodes = new LinkedList<>();
        List<Edge<N, E>> edges = new LinkedList<>();
    }


    public static void main(String[] args) {
        Boruvka<String, Integer> boruvka = new Boruvka<>();
        Graph<String, Integer> graph = new Graph<>();

        /* nodes */
        char c = 'A';
        for (int i = 0; i < 7; i++) {
            Node<String, Integer> node = new Node<>();
            node.info = "Node-" + c;
            graph.nodes.add(node);
            c++;
        }
        /* edges */
        for (int i = 0; i < 11; i++) {
            Edge<String, Integer> edge = new Edge<>();
            graph.edges.add(edge);
        }
        /* Create nodes */
        /* A */
        graph.nodes.get(0).out.add(graph.edges.get(0));
        graph.nodes.get(0).in.add(graph.edges.get(0));
        graph.nodes.get(0).out.add(graph.edges.get(1));
        graph.nodes.get(0).in.add(graph.edges.get(1));
        /* B */
        graph.nodes.get(1).out.add(graph.edges.get(0));
        graph.nodes.get(1).in.add(graph.edges.get(0));
        graph.nodes.get(1).out.add(graph.edges.get(2));
        graph.nodes.get(1).in.add(graph.edges.get(2));
        graph.nodes.get(1).out.add(graph.edges.get(3));
        graph.nodes.get(1).in.add(graph.edges.get(3));
        graph.nodes.get(1).out.add(graph.edges.get(4));
        graph.nodes.get(1).in.add(graph.edges.get(4));
        /* C */
        graph.nodes.get(2).out.add(graph.edges.get(3));
        graph.nodes.get(2).in.add(graph.edges.get(3));
        graph.nodes.get(2).out.add(graph.edges.get(5));
        graph.nodes.get(2).in.add(graph.edges.get(5));
        /* D */
        graph.nodes.get(3).out.add(graph.edges.get(1));
        graph.nodes.get(3).in.add(graph.edges.get(1));
        graph.nodes.get(3).out.add(graph.edges.get(2));
        graph.nodes.get(3).in.add(graph.edges.get(2));
        graph.nodes.get(3).out.add(graph.edges.get(6));
        graph.nodes.get(3).in.add(graph.edges.get(6));
        graph.nodes.get(3).out.add(graph.edges.get(7));
        graph.nodes.get(3).in.add(graph.edges.get(7));
        /* E */
        graph.nodes.get(4).out.add(graph.edges.get(5));
        graph.nodes.get(4).in.add(graph.edges.get(5));
        graph.nodes.get(4).out.add(graph.edges.get(4));
        graph.nodes.get(4).in.add(graph.edges.get(4));
        graph.nodes.get(4).out.add(graph.edges.get(6));
        graph.nodes.get(4).in.add(graph.edges.get(6));
        graph.nodes.get(4).out.add(graph.edges.get(8));
        graph.nodes.get(4).in.add(graph.edges.get(8));
        graph.nodes.get(4).out.add(graph.edges.get(9));
        graph.nodes.get(4).in.add(graph.edges.get(9));
        /* F */
        graph.nodes.get(5).out.add(graph.edges.get(7));
        graph.nodes.get(5).in.add(graph.edges.get(7));
        graph.nodes.get(5).out.add(graph.edges.get(8));
        graph.nodes.get(5).in.add(graph.edges.get(8));
        graph.nodes.get(5).out.add(graph.edges.get(10));
        graph.nodes.get(5).in.add(graph.edges.get(10));
        /* G */
        graph.nodes.get(6).out.add(graph.edges.get(9));
        graph.nodes.get(6).in.add(graph.edges.get(9));
        graph.nodes.get(5).out.add(graph.edges.get(10));
        graph.nodes.get(5).in.add(graph.edges.get(10));

        /* Create edges */
        /* 7 */
        graph.edges.get(0).out = graph.nodes.get(0);
        graph.edges.get(0).in = graph.nodes.get(1);
        graph.edges.get(0).weight = 7;
        /* 4 */
        graph.edges.get(1).out = graph.nodes.get(0);
        graph.edges.get(1).in = graph.nodes.get(3);
        graph.edges.get(1).weight = 4;
        /* 9 */
        graph.edges.get(2).out = graph.nodes.get(1);
        graph.edges.get(2).in = graph.nodes.get(3);
        graph.edges.get(2).weight = 9;
        /* 11 */
        graph.edges.get(3).out = graph.nodes.get(1);
        graph.edges.get(3).in = graph.nodes.get(2);
        graph.edges.get(3).weight = 11;
        /* 10 */
        graph.edges.get(4).out = graph.nodes.get(1);
        graph.edges.get(4).in = graph.nodes.get(4);
        graph.edges.get(4).weight = 10;
        /* 5 */
        graph.edges.get(5).out = graph.nodes.get(2);
        graph.edges.get(5).in = graph.nodes.get(4);
        graph.edges.get(5).weight = 5;
        /* 15 */
        graph.edges.get(6).out = graph.nodes.get(3);
        graph.edges.get(6).in = graph.nodes.get(4);
        graph.edges.get(6).weight = 15;
        /* 6 */
        graph.edges.get(7).out = graph.nodes.get(3);
        graph.edges.get(7).in = graph.nodes.get(5);
        graph.edges.get(7).weight = 6;
        /* 12 */
        graph.edges.get(8).out = graph.nodes.get(4);
        graph.edges.get(8).in = graph.nodes.get(5);
        graph.edges.get(8).weight = 12;
        /* 8 */
        graph.edges.get(9).out = graph.nodes.get(4);
        graph.edges.get(9).in = graph.nodes.get(6);
        graph.edges.get(9).weight = 8;
        /* 13 */
        graph.edges.get(10).out = graph.nodes.get(5);
        graph.edges.get(10).in = graph.nodes.get(6);
        graph.edges.get(10).weight = 13;


//        boruvka.minTree(graph);

        for (Edge<String, Integer> edge : boruvka.minTree(graph)) {
            System.out.println(edge);
        }
    }
}
