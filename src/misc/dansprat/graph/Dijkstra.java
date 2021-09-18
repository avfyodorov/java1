package misc.dansprat.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
    private class Path{
        int length;
        int path [];

        private Path(int size){
            length = 0;
            path = new int[size];
        }

        private void  add(int n){
            path[length++] = n;
        }
        private void copy(Path path){
            if (length<=path.length){
                for (int i=0;i<path.length;i++){
                    this.path[i]=path.path[i];
                }
            } else {
                for (int i=0;i<path.length;i++){
                    this.path[i]=path.path[i];
                }
                for (int i = path.length;i<length;i++){
                    this.path[i] = 0;
                }
            }
            length = path.length;
        }
    }

    private class Node{
        Double weight;
        Integer number;
        Path path;

        private void change(Node node,int weight){
            this.weight =node.weight+weight;
            path.copy(node.path);
            path.add(node.number);
        }
        Node(int number,double weight) {
            path = new Path(size);
            this.number= number;
            this.weight=weight;
        }
    }

    private int graph[][];
    int size;

    Dijkstra(int[][] graph){
        size = graph.length;
        this.graph = new int [size][size];
        for (int i = 0; i<size;i++){
            for (int j=0; j<size;j++){
                this.graph[i][j]=graph[i][j];
            }
        }
    }

    public int[][] find(int n){
        Node nodes [] = new Node[graph.length]; // Список всех вершин
        int [] [] paths = new int [graph.length][];
        PriorityQueue <Node> notes = new PriorityQueue<>(Comparator.comparing(x->x.weight)); // "Куча" вершин
        Node addNode;

        for (int i =0;i<graph.length;i++){
            if (i == n-1)
                continue;
            addNode = new Node(i+1,Integer.MAX_VALUE);
            notes.add(addNode);
            nodes[i] = addNode;
        }

        addNode = new Node(n,0);
        nodes[n-1] = addNode;
        notes.add(addNode);
        while(!notes.isEmpty()){
            Node v = notes.peek();
            int weight;
            for (int i = 0;i<size;i++){
                if (graph[v.number-1][i]!=0){
                    weight = graph[v.number-1][i];
                    Node toChange;
                    if (v.weight+weight < nodes[i].weight){
                        toChange = nodes[i];
                        toChange.change(v,weight);
                    }
                }
            }
            notes.remove(v);
        }
        for (int i =0;i<size;i++){
            nodes[i].path.add(nodes[i].number);
            paths[i] = nodes[i].path.path;
        }
        return paths;
    }

    static void printArr(int arr[][]){
        for (int i[]:arr) {
            for (int j : i) {
                System.out.printf("%5d",j);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // Вершины нумеруются начиная с 1
        int nodes [][] = {{0,7,9,0,0,14},
                          {7,0,10,15,0,0},
                          {9,10,0,11,0,2},
                          {0,15,11,0,6,0},
                          {0,0,0,6,0,9},
                          {14,0,2,0,9,0}};
        printArr(nodes);
        System.out.println("==============================");
        Dijkstra DJ = new Dijkstra(nodes);
        int paths[][] = DJ.find(1);
        printArr(paths);
        paths = DJ.find(6);
        System.out.println("==============================");
        printArr(paths);

    }
}
