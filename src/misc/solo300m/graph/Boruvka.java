package misc.solo300m.graph;

import java.util.ArrayList;
import java.util.List;

public class Boruvka<N,E> {
    public static List<Edge> minTree(Graph graph) {
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < graph.nodes.size(); i++) {
            Node tmp;
            tmp = (Node) graph.nodes.get(i);
            Edge edgIn = tmp.minInEdge(tmp);
            if (!list.contains(edgIn) && edgIn != null) {
                list.add(edgIn);
                continue;
            }
        }
        for (int i = 0; i < graph.nodes.size(); i++) {
            Node tmp;
            tmp = (Node) graph.nodes.get(i);
            Edge edgOut = tmp.minOutEdge(tmp);
            if (!list.contains(edgOut) && edgOut != null) {
                list.add(edgOut);
                continue;
            }
        }
        List<Edge> corList = new ArrayList<>();
        for (int j = 0; j < graph.nodes.size(); j++) {
            int col = 0;
            for (int i = 0; i < list.size(); i++) {
                String a = list.get(i).info.toString().substring(0, 1);
                Node bb = (Node) graph.nodes.get(j);
                String b = bb.info.toString();
                String c = list.get(i).info.toString().substring(1);
                if (a.equals(b) || c.equals(b)) {
                    col++;
                }
            }
            if (col == 1) {
                for (int i = 0; i < list.size(); i++) {
                    String a = list.get(i).info.toString().substring(0, 1);
                    Node bb = (Node) graph.nodes.get(j);
                    String b = bb.info.toString();
                    String c = list.get(i).info.toString().substring(1);
                    if (a.equals(b) || c.equals(b)) {
                        corList.add(list.get(i));
                        list.remove(i);
                    }
                }
            }

        }
        LinkGraph LG = new LinkGraph();
        for (Edge e : list) {
            LG.addLGraph(e);
        }
        List<LinkNode> doubleParent = new ArrayList<>();//поиск начала цикла
        List<LinkNode> doubleChild = new ArrayList<>();//поиск "вершины" цикла
        for (int i = 0; i<LG.lGraph.size();i++) {
            LinkNode linkNode = (LinkNode)LG.lGraph.get(i);
            if (linkNode.child.size()>1) {
                doubleChild.add(linkNode);
                //System.out.println(linkNode.info/*+" "++linkNode.child.get(0)+" "+linkNode.child.get(1)+" "+linkNode.edge.info*/);
            }
            if(linkNode.parent.size()>1){
                doubleParent.add(linkNode);
            }
        }
        LinkNode LN;
        Edge maxEdge = new Edge();
        Edge maxEdge2 = new Edge();
        for(int i = 0; i < doubleChild.size(); i++) {
            LinkNode DCH = doubleChild.get(i);
            LinkNode DP = doubleParent.get(i);
            for(int c = 0; c < DCH.child.size(); c++) {
                LN = (LinkNode) DCH.child.get(c);
                if (LN.edge.size() != 0 && maxEdge.weight == 0.0)
                    maxEdge = (Edge) LN.edge.get(0);
                while (LG.lGraph.indexOf(LN) != LG.lGraph.indexOf(DP)) {
                    if (LN.visited == false) {
                        LN.visited = true;
                        //maxEdge = (Edge) LN.edge.get(0);
                        if (maxEdge.compareTo(LN.edge.get(0)) < 0)
                            maxEdge = (Edge) LN.edge.get(0);
                        LN = (LinkNode) LN.child.get(0);
                        if (LN.equals(DP) && maxEdge.compareTo(LN.edge.get(c)) < 0)
                            maxEdge = (Edge) LN.edge.get(c);
                        else if (maxEdge.compareTo(LN.edge.get(0)) < 0)
                            maxEdge = (Edge) LN.edge.get(0);
                    }
                }
            }
        }

        if(maxEdge.weight!=0.0)
            list.remove(maxEdge);

        list.addAll(corList);
        System.out.println(list.toString());
        return list;
    }

}
class LinkNode<N, E> {
    N info;
    List<LinkNode<N, E>> parent;
    List<LinkNode<N, E>> child;
    List<Edge<N, E>> edge;

    boolean visited;

    LinkNode(){}
    LinkNode(N info) {
        this.info = info;
        parent = new ArrayList<>();
        child = new ArrayList<>();
        edge = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "info=" + info + "{ parent=" + parent +/*", child=" + child.toString() +*/", edge=" + edge + '}';
    }
}

class LinkGraph<N, E> {
    List<LinkNode<N, E>> lGraph;

    LinkGraph() {
        lGraph = new ArrayList<>();
    }

    public void addLGraph(Edge<N, E> edge) {
        Node<N, E> out = edge.out;
        Node<N, E> in = edge.in;
        boolean test = false;
        int inIndex = -1;
        int outIndex = -1;
        for(int i = 0; i<lGraph.size();i++){
            if(in.info.equals(lGraph.get(i).info))
                inIndex = i;
        }
        for(int i = 0; i<lGraph.size();i++){
            if(out.info.equals(lGraph.get(i).info))
                outIndex = i;
        }

        for (LinkNode ln : lGraph) {
            if (ln.info.equals(edge.out.info) && inIndex == -1) {
                LinkNode<N, E> nova = new LinkNode<>(edge.in.info);
                nova.parent.add(ln);
                ln.child.add(nova);
                nova.edge.add(edge);
                nova.visited = false;
                lGraph.add(nova);
                test = true;
                break;
            }
            else if(ln.info.equals(edge.out.info) && inIndex >= 0){
                LinkNode<N,E> parent = lGraph.get(outIndex);
                LinkNode<N,E> children = lGraph.get(inIndex);
                children.parent.add(parent);
                parent.child.add(children);
                children.edge.add(edge);
                lGraph.set(inIndex,parent);
                lGraph.set(outIndex,children);
                test = true;
                break;
            }
        }
        if (test == false) {
            LinkNode<N, E> novaIn = new LinkNode<>(edge.in.info);
            LinkNode<N, E> novaOut = new LinkNode<>(edge.out.info);
            novaIn.parent.add(novaOut);
            novaOut.child.add(novaIn);
            novaIn.edge.add(edge);
            novaIn.visited = false;
            novaOut.visited = false;
            lGraph.add(novaOut);
            lGraph.add(novaIn);
        }
    }


}

class Node<N, E> {
    N info; // информация об узле
    List<Edge<N, E>> in; // массив входящих ребер
    List<Edge<N, E>> out; // массив исходящих ребер

    Node(N info) {
        this.info = info;
        in = new ArrayList<>();
        out = new ArrayList<>();
    }

    public Edge<N, E> minInEdge(Node<N, E> node) {
        Edge<N, E> minIn = null;

        if (in != null && in.size() != 0)
            minIn = in.get(0);

        if (in != null && in.size() != 0) {
            for (int i = 1; i < node.in.size(); i++) {
                if (minIn.compareTo(in.get(i)) > 0) {
                    minIn = in.get(i);
                }
            }
        }
        if (minIn != null) {
            return minIn;
        } else
            return null;
    }

    public Edge<N, E> minOutEdge(Node<N, E> node) {
        Edge<N, E> minOut = null;

        if (out != null && out.size() != 0)
            minOut = out.get(0);

        if (out != null && out.size() != 0) {
            for (int i = 1; i < node.out.size(); i++) {
                if (minOut.compareTo(out.get(i)) > 0) {
                    minOut = out.get(i);
                }
            }
        }
        if (minOut != null) {
            return minOut;
        } else
            return null;
    }

    @Override
    public String toString() {
        return "точка = " + info + " {вх.ребра = " + in + ", исх.ребра = " + out + '}';
    }
}

class Edge<N, E> implements Comparable {
    E info; // информация о ребре
    Node<N, E> out; // вершина, из которой исходит ребро
    Node<N, E> in; // вершина, в которую можно попасть
    // по этому ребру
    double weight; // стоимость перехода

    Edge(){}
    Edge(E info, Node<N, E> pointOut, Node<N, E> pointIn, double weight) {
        this.info = info;
        this.out = pointOut;
        this.in = pointIn;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ребро = " + info + "{ из точки = " + out.info + ", в точку = " + in.info + ", вес = " + weight + "}\n";
    }

    @Override
    public int compareTo(Object o) {
        Edge<N, E> tmp = (Edge<N, E>) o;
        if (Double.compare(this.weight, tmp.weight) > 0)
            return 1;
        else if (Double.compare(this.weight, tmp.weight) < 0)
            return -1;
        else
            return 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Edge<N, E> tmp = (Edge<N, E>) obj;
        if (this.info.equals(tmp.info) && Double.compare(this.weight, tmp.weight) == 0)
            return true;
        else
            return false;
    }
}


class Graph<N, E> {
    public List<Node<N, E>> nodes;
    public List<Edge<N, E>> edges;

    Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addEdge(Edge<N, E> edge) {
       
        edge.in.in.add(edge);
        edge.out.out.add(edge);
        if (!conteinNode(edge.in))
            nodes.add(edge.in);
        if (!conteinNode(edge.out))
            nodes.add(edge.out);
        edges.add(edge);
        //edges.add(rev);
    }

    public boolean conteinNode(Node<N, E> node) {
        for (Node n : nodes) {
            if (n.info.equals(node.info))
                return true;
        }
        return false;
    }

    public Node<N, E> getNode(Character info) {
        for (Node n : nodes) {
            if (n.info.equals(info))
                return n;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Graph{" + " точки = " + nodes + ", ребра = " + edges + '}';
    }
}

class Main15{
    public static void main(String[] args) {
        Graph<Character,String> graph = new Graph<>();
        graph.addEdge(new Edge("DA",new Node('D'),new Node<>('A'),5.0));
        graph.addEdge(new Edge<>("AB",graph.getNode('A'),new Node<>('B'),7));
        graph.addEdge(new Edge<>("DB",graph.getNode('D'),graph.getNode('B'),9));
        graph.addEdge(new Edge<>("DE",graph.getNode('D'),new Node<>('E'),15));
        graph.addEdge(new Edge<>("DF",graph.getNode('D'),new Node<>('F'),6));
        graph.addEdge(new Edge<>("FG",graph.getNode('F'),new Node<>('G'),11));
        graph.addEdge(new Edge<>("FE",graph.getNode('F'),graph.getNode('E'),8));
        graph.addEdge(new Edge<>("EG",graph.getNode('E'),graph.getNode('G'),9));
        graph.addEdge(new Edge<>("EC",graph.getNode('E'),new Node<>('C'),5));
        graph.addEdge(new Edge<>("BE",graph.getNode('B'),graph.getNode('E'),9));
        graph.addEdge(new Edge<>("BC",graph.getNode('B'),graph.getNode('C'),8));
        //System.out.println(graph.nodes+"\n"+graph.edges+"\n");
        System.out.println("----------------------------------");
        /*Node<Character,String> a = new Node<>('A');
        Node<Character,String> d = new Node<>('D');
        Edge<Character,String> ad = new Edge<>("AD",a,d);
        Edge<Character,String> da = new Edge<>("DA",d,a);
        graph.addGraf(ad);
        graph.addGraf(da);
        System.out.println(a);
        System.out.println(d);
        System.out.println(ad);
        System.out.println(da);
        System.out.println(graph);*/
        Boruvka.minTree(graph);
    }
}