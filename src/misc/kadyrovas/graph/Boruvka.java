package misc.kadyrovas.graph;

//import org.apache.logging.log4j.core.appender.rolling.action.IfNot;

import java.util.*;


public class Boruvka<N,E> {
    static List<Edge> minTree(Graph graph) {
        Node node = null;
        List<Edge> listEdge = new ArrayList<>();
        graph.edges.sort(Comparator.comparing(edge->{Edge edge1 = (Edge) edge; return edge1.weight;}));
        //Добавляем для каждой вершины инцидентное ребро с минимальным весом
        node = findNode(graph);
        while (node != null){
            for (int i = 0; i < graph.edges.size(); i ++) {
                Edge edge = (Edge) graph.edges.get(i);
                if ( (edge.in.equals(node) || edge.out.equals(node))){
                    edge.out.connection.add(edge);
                    edge.in.connection.add(edge);
                    listEdge.add(edge);
                    break;
                }
            }
            node = findNode(graph);
        }

        listEdge = unionNodes(listEdge, graph);

        return listEdge;
    }

    public static List<Edge> unionNodes(List<Edge> list, Graph graph){
        //Объединяет все node инцидентными ребрами
        Set<Node> set = new HashSet<>();
        set.add(list.get(0).in);
        set.add(list.get(0).out);
        set = createSet(set);

        while (list.size() != graph.nodes.size() - 1){
            for (int i = 0; i < graph.edges.size(); i++){
                Edge edge = (Edge) graph.edges.get(i);
                boolean contA = set.contains(edge.in);
                boolean contB = set.contains(edge.out);

                //Добавляем edge в том случае, если одна из вершин есть в исходном множестве, а другой нет;
                if ((contA && !contB) || (!contA && contB) ) {
                    list.add(edge);
                    if (set.contains(edge.in)) edge.in.connection.add(edge);
                    else edge.out.connection.add(edge);
                    break;
                }

            }
            set = createSet(set);
        }
        return list;
    }

    public static Set<Node> createSet(Set<Node>set){
        boolean wasFound=true;
        while (wasFound) {
            wasFound=false;
            List<Node>list = new ArrayList(set);
            for (Node node : list) {
                for (int i = 0; i < node.connection.size(); i++) {
                    Edge edge = (Edge) node.connection.get(i);
                    if (!set.contains(edge.in)) {
                        wasFound=true;
                        set.add(edge.in);
                        break;
                    }
                    if (!set.contains(edge.out)) {
                        wasFound=true;
                        set.add(edge.out);
                        break;
                    }
                }
            }
        }
        return set;
    }

    public static Node findNode(Graph graph) {
        //Возвращает node без инцидентных ребер
        for (int i = 0; i < graph.nodes.size(); i ++){
            Node node = (Node) graph.nodes.get(i);
            if (node.connection.size() == 0)
                return node;
        }
        return null;
    }

    public static void main(String[] args) {
        final int ITERATIONS = 6;
        Graph graph = new Graph();
        //Перечень вершин
        String[] labels = {"A","B","C","D","E","F"};
        for (int i = 0; i < ITERATIONS; i++)
            graph.nodes.add(new Node<>(labels[i]));

        //Перечень ребер
        graph.edges.add(new Edge("AB", (Node)graph.nodes.get(0),(Node) graph.nodes.get(1), 13));
        graph.edges.add(new Edge("AC", (Node)graph.nodes.get(0),(Node) graph.nodes.get(2), 6));
        graph.edges.add(new Edge("BC", (Node)graph.nodes.get(1),(Node) graph.nodes.get(2), 7));
        graph.edges.add(new Edge("BD", (Node)graph.nodes.get(1),(Node) graph.nodes.get(3), 1));
        graph.edges.add(new Edge("CD", (Node)graph.nodes.get(2),(Node) graph.nodes.get(3), 14));
        graph.edges.add(new Edge("CE", (Node)graph.nodes.get(2),(Node) graph.nodes.get(4), 8));
        graph.edges.add(new Edge("DE", (Node)graph.nodes.get(3),(Node) graph.nodes.get(4), 9));
        graph.edges.add(new Edge("DF", (Node)graph.nodes.get(3),(Node) graph.nodes.get(5), 3));
        graph.edges.add(new Edge("EF", (Node)graph.nodes.get(4),(Node) graph.nodes.get(5), 2));

//        for (int i = 0; i < graph.nodes.size(); i ++) {
//            System.out.println("Для вершины: " + graph.nodes.get(i));
//            Node node = (Node) graph.nodes.get(i);
//            for (int k = 0; k < graph.edges.size(); k ++){
//                Edge edge = (Edge) graph.edges.get(k);
//                if (node.equals(edge.out)) {
//                    node.connection.add(edge);
//                    System.out.println("out: " + edge + "=" + edge.weight);
//                }
//                if (node.equals(edge.in)) {
//                    node.connection.add(edge);
//                    System.out.println("in: " + edge + "=" + edge.weight);
//                }
//            }
//        }




        minTree(graph).forEach(System.out::println);
    }


}
