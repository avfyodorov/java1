package misc.sidnet1964.gc;

import java.util.*;

public class Heap {
   static short sFill = 1; //  символ заполнения
   static short sClea = 1; //  символ заполнения
   long start, stop;
   int iter = 0;
   int delBlock = 0;    //  счетчик удалений
   int insBlock = 0;    //  счетчик добавлений
   int freBlock = 0;    //  счетчик освобождений
   int allSize;            //  занято памяти
   int maxHeapSize;        //  размер кучи
   byte[] bytes;           // - собственно, куча
   TreeMap<Integer, Integer> busyTree; //  карта занятых блоков (+)
   /// новые переменные
   TreeMap<Integer, TreeSet<Integer>> freeTree;
   TreeSet<Integer> freeSet;   //  пустая заготовка для одного набора

   public Heap(int maxHeapSize) {
//        start = System.currentTimeMillis(); //  отсчет от момента создания объекта
      this.maxHeapSize = maxHeapSize;
      bytes = new byte[this.maxHeapSize];
      busyTree = new TreeMap<>();
      /// новые переменные
      freeTree = new TreeMap<>();
      freeSet = new TreeSet<>(Set.of(0));
//        freeSet.add(0);     //  нулевой адрес блока
      freeTree.put(maxHeapSize, freeSet);

//debug        memFill(0, maxHeapSize, (byte) (-1*(sClea++))); //  первоначальное заполнение (debug)
   }
   //  --------------------------------
   //  распечатать цепочку блоков
   void memPrint(Iterable<Allock> myList, short nFill){
      ///out.println("-----------------");
      for (Allock elem : myList ){
         ///out.println(elem + "~" + nFill);
      }
      ///out.println("=================");
   }
   //  заполнить выделенный блок данными
   void memFill(int ptr, int size, byte data){
      for (int i = 0; i<size; i++)
         bytes[i+ptr] = data;
   }
   //  --------------------------------
   //  "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size
   public int malloc(int size){
      //  поиск сбободного участка
      if (freeTree.size() == 0){
         ///out.println("freeTree.size() == 0 -> " + freeTree.size());
         throw new OutOfMemoryException("Нет свободных блоков для ", size);
      }
//        System.///out.println("seek = " + size + " freeTree = " + freeTree);
//        long start = System.currentTimeMillis();
      Map.Entry<Integer, TreeSet<Integer>> firstPtr = freeTree.ceilingEntry(size);
//        System.///out.println("size = " + freeTree.size() + " firstPtr = " + firstPtr);
//          if (firstPtr == null) - запустить компактизацию
      if (firstPtr != null){
         int findSize = firstPtr.getKey();    //  реальный размер блока
         TreeSet<Integer> findSet = firstPtr.getValue(); //  набор адресов
//  Retrieves and removes the first (lowest) element, or returns null if this set is empty.
         int findPtr = findSet.pollFirst();  //  возвращает первый элемент и удаляет
//            if (findPtr == null) - убедиться, что этого не произойдет
         if (findSet.isEmpty())
            freeTree.remove(findSize);
         else {
            //  блок для исследования ситуации
            ///out.println("Запрос  = " + size + " остаток в findSet = " + findSet);
         }
         //  откусить от этого блока нужный кусок
//debug            memFill(pointBeginBlock, size, (byte) sFill++);  //  заполнить "кучу" данными
         allSize += size;    //  общий объем занятой памяти
         if (size == findSize) {
            //  создать запись в списке занятых областей
            busyTree.put(findPtr, size);
            //  если блок занять полностью - удалить из списка свободных
            //  уже удален (при чтении)
            delBlock++;
         }
         else {
            //  вариант с перезаписью блока
            //  создать запись в списке занятых областей
            busyTree.put(findPtr, size);
            //  остался кусок размером (findSize - size) с адресом (findPtr + size)
            freeTreeAdd(findSize - size, findPtr + size);
            insBlock++;
         }
//            if (iter++ % 10_000 == 0) {
//                stop = System.currentTimeMillis();
//                System.///out.println(iter + "/" + (stop - start) + "/" + freeTree.size());
//            }
         return findPtr;
      }
      ///out.println("-- Ошибка размещения = " + size);
//        memPrint(freeHash, (short) 0);
//        memPrint(busyList, (short) 1);
      //  проанализировать общий объем памяти
      if (maxHeapSize - allSize < size)
         throw new OutOfMemoryException("Нет достаточного размера памяти", size);

      throw new OutOfMemoryException("Нет свободного участка памяти", size);
   }
   //  --------------------------------
   public void freeTreeAdd (int keyTree, int valueTreeAsSet) {
      if (freeTree.containsKey(keyTree)){
         //  такой элемент существует, найти его
         TreeSet<Integer> existSet = freeTree.get(keyTree);
         existSet.add(valueTreeAsSet);
//            freeTree.replace(keyTree, existSet);    /// проверить необходимость
      }
      else {
         //  такого элемента еще нет - создать его
         TreeSet<Integer> existSet = new TreeSet<>(Set.of(valueTreeAsSet));
//            existSet.add(valueTreeAsSet);
         freeTree.put(keyTree, existSet);
      }
   }

   //  --------------------------------
   //  "удаляет", т.е. помечает как свободный блок памяти по "указателю"
   public void free(int ptr){
      freBlock++;
      int busySize = busyTree.get(ptr);   //  размер освобождаемого блока
      if (busySize > 0) {
         allSize -= busySize;    //  общий объем занятой памяти
         busyTree.remove(ptr);   //  удалить блок из списка занятых
         //  добавить блок в список свободных (сделать отдельной процедурой)
         //  появился кусок с адресом ptr и размером busySize
         freeTreeAdd(busySize, ptr);
         return;
      }
      throw new InvalidPointerException("Неверный адрес участка памяти", ptr);
   }

   @Override
   public String toString() {
      return "Heap{" + Arrays.toString(bytes) + '}';
   }

   class Allock {
      public int ptr;
      public int size;

      public Allock(int ptr, int size) {
         this.ptr = ptr;
         this.size = size;
      }

   }
   //  ------------------------------------
   public static void main(String[] args) {
      final int mSize = 64;
      Heap extMemory = new Heap(mSize);
      extMemory.aMallok(0);
//        extMemory.aMallok(1);
//        extMemory.aMallok(2);
//        System.///out.println(extMemory.malloc(2));

      ///out.println("-- Размещение");
      ///out.println("Занято - " + extMemory.allSize + " , свободно - " + (mSize - extMemory.allSize));
      ///out.println("Занятых - " + extMemory.busyTree.size() + " , свободных - " + (extMemory.freeTree.size()));
      ///out.println(extMemory.freeTree);
      ///out.println(extMemory.busyTree);
      ///out.println(extMemory.toString());

      for (int i = 1; i <= extMemory.busyTree.size()/3; i++)
         extMemory.aFree(0);
//        extMemory.aFree(0);
//        extMemory.aFree(0);
//        extMemory.aFree(0);

      ///out.println("-- Освобождение");
      ///out.println("Занято - " + extMemory.allSize + " , свободно - " + (mSize - extMemory.allSize));
      ///out.println("Занятых - " + extMemory.busyTree.size() + " , свободных - " + (extMemory.freeTree.size()));
      ///out.println(extMemory.freeTree);
      ///out.println(extMemory.busyTree);
      ///out.println(extMemory.toString());

//        extMemory.defrag();
//
//        System.///out.println();
      ///out.println("-- Дефрагментация");
//        System.///out.println(extMemory.freeList);
//        System.///out.println(extMemory.busyTree);
//        System.///out.println(extMemory.toString());

      extMemory.aMallok(0);

      ///out.println("-- Размещение -- вторая волна");
      ///out.println("Занято - " + extMemory.allSize + " , свободно - " + (mSize - extMemory.allSize));
      ///out.println("Занятых - " + extMemory.busyTree.size() + " , свободных - " + (extMemory.freeTree.size()));
      ///out.println(extMemory.freeTree);
      ///out.println(extMemory.busyTree);
      ///out.println(extMemory.toString());

//        extMemory.compact();
//
//        System.///out.println();
//        System.///out.println("-- Компактизация");
//        System.///out.println(extMemory.inputList);
//        System.///out.println(extMemory.outputList);
//        System.///out.println(extMemory.toString());
//
//        extMemory.defrag();
//
//        System.///out.println();
//        System.///out.println("-- Дефрагментация");
//        System.///out.println(extMemory.inputList);
//        System.///out.println(extMemory.outputList);
//        System.///out.println(extMemory.toString());

   }
   //  ------------------------------------
   public static final int mathRandomInterval( int min, int max) {
      return (int)((Math.random() * (max - min)) + min);
   }
   //
   //  вспомагательная процедура заполнения
   void aMallok(int step){
      switch (step) {
         case 0: //  случайная последовательность, заполнение половины
            for (int i = 0; i < maxHeapSize/3; i++){
               try {
                  int rand = mathRandomInterval(1, 6);
                  malloc(rand);
               } catch (OutOfMemoryException e){
                  ///out.println(e);
                  return;
               }
            }
            break;
         case 1: //  возрастающая последовательность
            for (int i = 0; i < maxHeapSize; i++){
               try {
                  malloc(i+1);
               } catch (OutOfMemoryException e){
                  ///out.println(e);
                  return;
               }
            }
            break;
         case 2: //  убывающая последовательность
            for (int i = (int) Math.sqrt(maxHeapSize); i > 0; i--){
               try {
                  malloc(i+1);
               } catch (OutOfMemoryException e){
                  ///out.println(e);
                  return;
               }
            }
            break;
      }
   }
   //  ------------------------------------
   //  вспомагательная процедура освобождения
   public void aFree(int step) {
      Allock oBlock;
      switch (step) {
         case 0: //  освобождение случайного блока
            int rand = mathRandomInterval(0, busyTree.size());
            ///out.println("Освобождение блока # " + rand + " из " + busyTree.size());
            int k = 0;
            for(Map.Entry<Integer, Integer> entry : busyTree.entrySet()) {
//                    System.///out.println(entry.getKey() + " -> " + entry.getValue());
               if (k++ == rand) {
//                    oBlock = busyList.get(rand);
                  free(entry.getKey());
                  return;
               }
            }
            break;
      }
   }
}
//------------------------------------------------------------------------------
////    контрольный экземпляр
////  static final int maxSize = 800_000_000
////  malloc time: 1093 free time: 311
////  total time: 1734 count: 1778065
////  без перезаписи блока freeBlock.put(sizeB, tempSet);
////  malloc time: 1018 free time: 293
////  total time: 1765 count: 1774629

//  01.07.2020 15:55 вариант с двумя деревьями
//  static final int maxSize = 800_000_000;
//  malloc time: 1070 free time: 272
//  total time: 1713 count: 1776496
//  повторно
//  malloc time: 1142 free time: 169
//  total time: 1625 count: 1775444
//  //  static final int maxSize = 800_000_000;
//  malloc time: 2373 free time: 204    -- увеличение в 2 раза
//  total time: 2922 count: 1992552
//  повторно
//  malloc time: 1997 free time: 268
//  total time: 2826 count: 1991455
//  //  static final int maxSize = 900_000_000;
//  переписал сздание элемента freeTree через подпрограмму
//  malloc time: 1698 free time: 559 - увеличилось время free
//  total time: 2764 count: 1991968
//  //  static final int maxSize = 800_000_000;
//  malloc time: 971 free time: 250
//  total time: 1563 count: 1770911
//  mazneff Вчера, в 20:03
//  Добрый вечер!
//  Сейчас посмотрю
//  malloc time: 2835 free time: 686
//  очень хороший результат
