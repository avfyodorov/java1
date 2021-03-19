package misc.solo300m.graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Dijkstra {

    private List<Vertex> vList;
    private int smMat[][];


    public Dijkstra(int [][]graf){
        smMat = graf.clone();
        vList = new ArrayList<>();
        for(int i = 0; i < smMat.length; i++){
            vList.add(new Vertex(i+1));
        }
    }

    public int getSmMat(int i, int j) {
        return smMat[i][j];
    }
    public int getWidthMat(){
        return smMat.length;
    }
    public void printSmMat(){
        System.out.println("      Матрица смежности");

        System.out.print("   ");
        for(int i = 0; i < getWidthMat(); i++){
            System.out.print("\t"+vList.get(i)+" ");
        }
        //System.out.println();
        System.out.println("\n   ___________________________________");
        for(int i = 0; i < getWidthMat(); i++ ) {
            System.out.print(vList.get(i)+" |");
            for (int j = 0; j < getWidthMat(); j++) {
                System.out.print("\t"+getSmMat(i,j)+" ");
            }
            System.out.println();
        }
    }
    public void printSmMat(int[][]arr){
        System.out.println("      Матрица смежности");

        System.out.print("   ");
        for(int i = 0; i < arr.length; i++){
            System.out.print("\t"+vList.get(i)+" ");
        }
        //System.out.println();
        System.out.println("\n   ___________________________________");
        for(int i = 0; i < arr.length; i++ ) {
            System.out.print(vList.get(i)+" |");
            for (int j = 0; j < arr.length; j++) {
                System.out.print("\t"+arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public int[][] find(int n){
        int[][]path = new int[getWidthMat()][getWidthMat()];
        int sch = n-1;
        Queue<Integer> steck = new ArrayDeque<>();
        steck.offer(sch);
        while(!steck.isEmpty()) {
            int elem = steck.poll();
            Vertex root = vList.get(elem);
            root.Visited = true;
            vList.set(elem, root);
            for (int i = 0; i < smMat.length; i++) {
                if (smMat[i][elem] != 0) {
                    Vertex tmp = vList.get(i);
                    if(tmp.parentDij!=null) {
                        if (!tmp.Visited && (tmp.weight >= vList.get(elem).weight + smMat[i][elem] || tmp.weight == 0)) {
                            steck.offer(i);
                            tmp.parentDij = vList.get(elem);
                            tmp.weight = tmp.parentDij.weight + smMat[i][elem];
                            vList.set(i, tmp);
                           /* System.out.println("label =" + vList.get(i).label + " вес =" + vList.get(i).weight +
                                    " родитель =" + vList.get(i).parentDij.label);*/
                        }
                    }else{
                        if (!tmp.Visited && (tmp.weight >= vList.get(elem).weight/*tmp.weight*/ + smMat[i][elem] || tmp.weight == 0)) {
                            steck.offer(i);
                            tmp.parentDij = vList.get(elem);
                            tmp.weight = tmp.parentDij.weight + smMat[i][elem];
                            vList.set(i, tmp);
                            /*System.out.println("label =" + vList.get(i).label + " вес =" + vList.get(i).weight +
                                    " родитель =" + vList.get(i).parentDij.label);*/
                        }
                    }
                }
            }
            /*System.out.println("______________");
            System.out.println(steck.toString());*/
        }
        /*for(int i = 0; i<vList.size(); i++){
            System.out.print(vList.get(i).label+" "+vList.get(i).weight+" "+vList.get(i).parentDij);
            System.out.println();
        }*/
        for(int i = 0; i < path.length; i++){
            for (int j = 0; j < path.length; j++){
                path[i][j] = 0;
                //System.out.println(path[i][j]);
            }
        }
        for(int i = 0; i < vList.size(); i++){
            if(vList.get(i).parentDij!=null) {
                path[i][(vList.get(i).parentDij.label) - 1] = smMat[i][(vList.get(i).parentDij.label) - 1];
                path[(vList.get(i).parentDij.label) - 1][i] = smMat[(vList.get(i).parentDij.label) - 1][i];
            }
            else
                path[i][i] = 0;
            //
        }
        return path;
    }
}
class Main{
    public static void main(String[] args) {
        int[][]graf = {
                {0,9,0 ,0,7,4,0,0,14},
                {9,0,13,0,0,0,0,0, 0},
                {0,13,0,11,0,0,6,21,0},
                {0,0, 11,0,0,0,0,5,0},
                {7,0,0,0,0,10,0,0,0},
                {4,0,0,0,10,0,16,0,18},
                {0,0,6,0,0,16,0,5,0},
                {0,0,21,5,0,0,5,0,0},
                {14,0,0,0,0,18,0,0,0}
        };
        Dijkstra DA = new Dijkstra(graf);
        DA.printSmMat();
        int [][] path = DA.find(1);
        DA.printSmMat(path);
    }
}