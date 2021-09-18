package misc.lama5267.graph;

import java.util.*;

public class Dijkstra {

    private final int[][] graph;
    private final Map<Integer, Node> nodes = new HashMap<>(); /* для хранения узлов с длинами путей и информации о родительских узлах */
    private Node node;
    private final Queue<Integer> queue = new LinkedList<>(); /* очередь обработки узлов по минимальной длине пути */

    public Dijkstra(int[][] graph) {
        this.graph = graph;
    }

    public void find(int n) {
        if (n < 0 || n > graph.length - 1)
            throw new IllegalArgumentException("Значение n не может быть меньше 0 и больше " + (graph.length - 1) + "!");
        initializationFirstNode(n); /* инициализируем стартовый узел N */
        int count = 0;
        while (count < graph.length) {
            int key = queue.isEmpty() ? n : queue.poll(); /* ключ обрабатываемого узла */
            if (!nodes.get(key).visited) { /* если узел не отмечен как посещенный */
                searchPathsToNodes(nodes.get(key), key); /* ищем и добавляем в TreeSet в узле информацию о ближайших (смежных) узлах */
                nodes.get(key).visited = true; /* отмечаем узел как посещенный */
            }

            for (NodeSet sortedNode : nodes.get(key).sortedNodes) { /* в цикле обрабатываем ранее собранную информацию о ближайших (смежных) узлах */
                int pathSize = nodes.get(key).pathLength + sortedNode.pathLengthSet; /* длина пути от стартового N узла до текущего + до смежного */
                if (nodes.containsKey(sortedNode.numberNodeSet)) { /* если такой узел уже есть в nodes */
                    if (nodes.get(key).pathLength > pathSize) { /* проверяем, является ли новый путь короче старого */
                        nodes.get(key).pathLength = pathSize; /* если да, то обновляем путь */
                        nodes.get(key).cameFrom = sortedNode.cameFromSet; /* обновляем информацию о узле из которого пришел новый путь */
                    }
                } else { /* если узла в nodes нет, то создаем его */
                    node = new Node();
                    node.numberNode = sortedNode.numberNodeSet;
                    node.cameFrom = sortedNode.cameFromSet;
                    node.pathLength = pathSize;
                    node.visited = sortedNode.visitedSet;
                    nodes.put(sortedNode.numberNodeSet, node);
                }
            }
            count++;
        }
        /* вывод информации о кратчайших путях до каждого узла от узла N */
        if (nodes.size() == 1)
            System.out.println("Смежных узлов не найдено!");
        else
            nodes.entrySet().forEach(System.out::println);
    }

    private void initializationFirstNode(int n) { /* инициализация N узла */
        node = new Node();
        node.numberNode = n;
        node.pathLength = 0;
        nodes.put(n, node);
    }

    private void searchPathsToNodes(Node node, int key) { /* формирование смежных узлов в sortedNodes внутри узла */
        for (int i = 0; i < graph.length; i++) {
            if (graph[key][i] != 0 && i != node.cameFrom) { /* если ячейка не 0 и не является узлом-родителем */
                if (nodes.containsKey(i)) { /* если смежный узел уже есть в nodes */
                    int pathSize = graph[key][i] + node.pathLength; /* длина пути от стартового N узла до текущего + до смежного */
                    if (pathSize < nodes.get(i).pathLength) { /* проверяем, является ли новый путь короче старого */
                        nodes.get(i).pathLength = pathSize; /* если да, то обновляем путь */
                        nodes.get(i).cameFrom = key; /* обновляем информацию о узле из которого пришел новый путь */
                    }
                } else { /* если узла в sortedNodes нет, то создаем его */
                    NodeSet nodeSet = new NodeSet();
                    nodeSet.pathLengthSet = graph[key][i];
                    nodeSet.numberNodeSet = i;
                    nodeSet.cameFromSet = key;
                    node.sortedNodes.add(nodeSet);
                }
            }
        }
        for (NodeSet sortedNode : node.sortedNodes) /* формируем очередь приоритета обработки узлов */
            queue.add(sortedNode.numberNodeSet);
    }

    static class Node { /* основной узел из nodes */
        private boolean visited = false;
        private int cameFrom = 0;
        private int numberNode = 0;
        private int pathLength = Integer.MAX_VALUE;
        private final Set<NodeSet> sortedNodes;

        public Node() {
            this.sortedNodes = new TreeSet<>(Comparator.comparingInt(o -> o.pathLengthSet));
        }

        @Override
        public String toString() {
            return "Node{" +
                    "visited=" + visited +
                    ", comeFrom=" + cameFrom +
                    ", numberNode=" + numberNode +
                    ", pathLength=" + pathLength +
                    '}';
        }
    }

    static class NodeSet { /* вспомогательный узел для хранения смежных узлов в sortedNodes */
        private final boolean visitedSet = false;
        private int cameFromSet = 0;
        private int numberNodeSet = 0;
        private int pathLengthSet = Integer.MAX_VALUE;
    }


    public static void main(String[] args) {
        /* ориентированный граф */
        int[][] matrix = {{0, 10, 6, 8, 0, 0, 0, 0, 0},
                {0, 0, 0, 5, 13, 0, 11, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0},
                {0, 0, 2, 0, 5, 7, 12, 0, 0},
                {0, 0, 0, 0, 0, 9, 0, 0, 12},
                {0, 0, 0, 0, 0, 0, 0, 8, 10},
                {0, 0, 0, 0, 0, 4, 0, 6, 16},
                {0, 0, 0, 0, 0, 0, 0, 0, 15},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        /* неориентированный граф */
        int[][] oriMatrix = {{0, 10, 6, 8, 0, 0, 0, 0, 0},
                {10, 0, 0, 5, 13, 0, 11, 0, 0},
                {6, 0, 0, 2, 3, 0, 0, 0, 0},
                {8, 5, 2, 0, 5, 7, 12, 0, 0},
                {0, 13, 3, 5, 0, 9, 0, 0, 12},
                {0, 0, 0, 7, 9, 0, 4, 8, 10},
                {0, 11, 0, 12, 0, 4, 0, 6, 16},
                {0, 0, 0, 0, 0, 8, 6, 0, 15},
                {0, 0, 0, 0, 12, 10, 16, 15, 0}};

        Dijkstra dijkstra = new Dijkstra(matrix);
        dijkstra.find(0);
    }
}
