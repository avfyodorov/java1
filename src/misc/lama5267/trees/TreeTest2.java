package misc.lama5267.trees;

import misc.dansprat.trees.AvlTree;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class TreeTest2 {
   static final int ITERATIONS = 1_000_000;

   public static void main(String[] args) {
      misc.dansprat.trees.AvlTree<Integer,Integer> avlTree = new AvlTree<>();
      TreeMap <Integer,Integer> treeMap = new TreeMap<>();
      ArrayList <Integer> list = new ArrayList<>();
      System.out.println("Тесты на случаном наборе данных\n");

      for(int i=0; i<ITERATIONS;i++){
         list.add(ThreadLocalRandom.current().nextInt());
      }
      long start = System.currentTimeMillis();
      for (var x:list){
         treeMap.put(x,x);
      }
      System.out.println("Добавление в Map: "+ (System.currentTimeMillis()-start)+" ms");
      start = System.currentTimeMillis();
      for(var x:list){
         avlTree.put(x,x);
      }
      System.out.println("Добавление в AvlTree: "+ (System.currentTimeMillis()-start)+" ms\n");

      start=System.currentTimeMillis();
      for(var x:list){
         treeMap.get(x);
      }
      System.out.println("Поиск в Map: "+ (System.currentTimeMillis()-start)+" ms");
      start=System.currentTimeMillis();
      for(var x:list){
         avlTree.find(x);
      }
      System.out.println("Поиск в AvlTree: "+ (System.currentTimeMillis()-start)+" ms\n");

      ArrayList<Integer> keys = new ArrayList<>();
      for (var x:treeMap.keySet()){
         keys.add(x);
      }
      start=System.currentTimeMillis();
      for(var x:keys){
         treeMap.remove(x);
      }
      System.out.println("Удаление в Map: "+ (System.currentTimeMillis()-start)+" ms");
      start=System.currentTimeMillis();
      for(var x:keys){
         avlTree.delete(x);
      }
      System.out.println("Удаление в AvlTree: "+ (System.currentTimeMillis()-start)+" ms\n");

      System.out.println("Тесты на сортированном наборе данных\n");
      start = System.currentTimeMillis();
      for(int i = 0;i<ITERATIONS;i++){
         treeMap.put(i,i);
      }
      System.out.println("Добавление в Map: "+ (System.currentTimeMillis()-start)+" ms");
      start = System.currentTimeMillis();
      for(int i = 0;i<ITERATIONS;i++){
         avlTree.put(i,i);
      }
      System.out.println("Добавление в AvlTree: "+ (System.currentTimeMillis()-start)+" ms\n");

      start = System.currentTimeMillis();
      for(int i = 0;i<ITERATIONS;i++){
         treeMap.get(i);
      }
      System.out.println("Поиск в Map: "+ (System.currentTimeMillis()-start)+" ms");
      start = System.currentTimeMillis();
      for(int i = 0;i<ITERATIONS;i++){
         avlTree.find(i);
      }
      System.out.println("Поиск в AvlTree: "+ (System.currentTimeMillis()-start)+" ms\n");

      start = System.currentTimeMillis();
      for(int i = 0;i<ITERATIONS;i++){
         treeMap.remove(i);
      }
      System.out.println("Удаление в Map: "+ (System.currentTimeMillis()-start)+" ms");
      start = System.currentTimeMillis();
      for(int i = 0;i<ITERATIONS;i++){
         avlTree.delete(i);
      }
      System.out.println("Удаление в AvlTree: "+ (System.currentTimeMillis()-start)+" ms\n");


   }

}

