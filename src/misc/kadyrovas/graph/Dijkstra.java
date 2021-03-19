package misc.kadyrovas.graph;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Dijkstra {
    int[][] graph;
    public Deque<Integer>[] paths; //массив путей
    enum Visit {WAS, UNVISITED, USED};  //был, еще не был, вершина использована

    public Dijkstra(int[][] graph) {
        this.graph = graph;
        this.paths = new ArrayDeque[graph.length];
    }

    public int[] find(int n) {
        Visit[] visits = new Visit[graph.length];
        int[] weight = new int[graph.length]; //минимальная стоимость вершины
        int[] connections = new int[graph.length]; //ссылки на родительские вершины

        int nodeNumber;

        MinHeap heap = new MinHeap();

        for (int i = 0; i < graph.length; i ++){
            weight[i] = Integer.MAX_VALUE;
            visits[i] = Visit.UNVISITED;
        }
        weight[n] = 0;
        heap.put(new NodeDijkstra(n, 0));
        connections[n] = n;

        while (heap.getSize() > 0) {
            nodeNumber = (int) heap.getKey(0);
            for (int i = 0; i < graph.length; i++) {
                if (graph[nodeNumber][i] != 0 && visits[i] != Visit.USED &&
                        weight[i] > weight[nodeNumber] + graph[nodeNumber][i]) {
                    weight[i] = weight[nodeNumber] + graph[nodeNumber][i];

                    if (visits[i] == Visit.UNVISITED)
                        heap.put(new NodeDijkstra(i, weight[i]));
                    else
                        heap.change(i, weight[i]);
                    visits[i]=Visit.WAS;
                    connections[i] = nodeNumber;
                }
            }

            heap.delete(0);
            visits[nodeNumber] = Visit.USED;
        }

        //Создание массива путей
        for (int i = 0; i < graph.length; i ++) {
            paths[i] = new ArrayDeque<>();
                int finis = connections[i];
                while (n != finis) {
                    paths[i].offerFirst(finis);
                    finis = connections[finis];
                }
                paths[i].offerFirst(n);
                paths[i].offerLast(i);
            }
        return weight;
    }


    public static void main(String[] args) {
        final int ITERATION = 6;
        int[][] ar = new int[6][6];

        //веса ребер графа
        ar[0][1] = 7;
        ar[0][2] = 9;
        ar[0][5] = 14;
        ar[1][2] = 10;
        ar[1][3] = 15;
        ar[2][3] = 11;
        ar[2][5] = 2;
        ar[3][4] = 6;
        ar[4][5] = 9;

//создаем симметрию в неориентированном графе
        for (int i = 0; i < ITERATION; i++)
            for (int k = i + 1; k < ITERATION; k++)
                ar[k][i] = ar[i][k];

        System.out.println("Исходный граф:");
        for (int i = 0; i < ITERATION; i ++)
            System.out.println(Arrays.toString(ar[i]));

        System.out.println();

        Dijkstra dijkstra = new Dijkstra(ar);

        System.out.println("Минимальные веса вершин графа:");
        System.out.println(Arrays.toString(dijkstra.find(4)));

        System.out.println("-------------------");
        for (int i = 0; i < ITERATION; i ++){
            System.out.print("Путь к вершине " + i + ": ");
            while (!dijkstra.paths[i].isEmpty())
                System.out.print(dijkstra.paths[i].pollFirst() + " ");
            System.out.println();
        }
    }
}
