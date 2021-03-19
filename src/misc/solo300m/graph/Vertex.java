package misc.solo300m.graph;

public class Vertex {
    public int label;
    public boolean Visited;
    public int weight;// суммарный вес по алгоритму Дейкстра
    public Vertex parentDij;//задействуется только в алгоритме Дейкстра

    public Vertex(int lab){
        this.label = lab;
        this.Visited = false;
        this.weight = 0;
        this.parentDij = null;
    }

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}