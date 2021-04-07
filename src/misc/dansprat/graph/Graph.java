package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph<N, E> {

    public static class Node<N,E> {
        N info; // информация об узле
        List<Edge<N, E>> in; // массив входящих ребер
        List<Edge<N, E>> out; // массив исходящих ребер

        public Node (N info,List<Edge<N, E>> in,List<Edge<N, E>> out) {
            this.info=info;
            this.in = in;
            this.out=out;
        }

        public Node (N info) {
            this.info=info;
            this.in = new ArrayList<>();
            this.out= new ArrayList<>();
        }
    }

    public static class Edge<N, E> {
        E info; // информация о ребре
        Node<N, E> out; // вершина, из которой исходит ребро
        Node<N, E> in; // вершина, в которую можно попасть
        // по этому ребру
        double weight; // стоимость перехода

        public Edge(E info, Node<N, E> out,Node<N, E> in,double weight){
            this.weight =weight;
            this.in = in;
            this.out = out;
            this.info = info;
        }

        public Edge(E info,double weight){
            this.weight =weight;
            this.info = info;
        }
    }

    List<Node<N, E>> nodes;
    List<Edge<N, E>> edges;



    public static void main(String[] args) {

    }
}