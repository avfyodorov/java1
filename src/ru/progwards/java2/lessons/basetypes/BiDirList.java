package ru.progwards.java2.lessons.basetypes;

        import java.util.Iterator;
        import java.util.Spliterator;
        import java.util.function.Consumer;

public class BiDirList<T> implements Iterable<T>{
   @Override
   public Iterator<T> iterator() {
      return new Iterator<T>() {
         Node<T> current = new Node<>(null,head,null);
         @Override
         public boolean hasNext() {
            if (current.next==null) return false;
            return true;
         }
         @Override
         public T next() {
            T val = current.next.value;
            current = current.next;
            return val;
         }
      };
   }

   @Override
   public void forEach(Consumer<? super T> action) {
      Node<T> tmp = head;
      while (tmp!=null){
         action.accept(tmp.value);
         tmp = tmp.next;
      }
   }

   private class Node <T> {
      Node<T> next;
      Node<T> previous;
      T value;

      public Node(T val){
         this.value=val;
      }
      public Node(T val,Node<T> next,Node<T> previous){
         this.value = val;
         this.next = next;
      }
      public void setNext(Node<T> next){
         this.next=next;
         if (next != null)
            next.previous = this;
      }
      public void setPrevious(Node<T> previous){
         this.previous = previous;
         if (previous!=null)
            previous.next = this;
      }
   }
   int size;
   Node <T> head;
   Node <T> tail;
   public BiDirList(){
      size =0;
      head = null;
      tail = null;
   }
   public void addLast(T item){
      if(size == 0){
         head = new Node<T>(item);
         tail = head;
      } else {
         tail.setNext(new Node<T>(item));
         tail = tail.next;
      }
      size++;
   }
   public void addFirst(T item){
      if(size == 0){
         head= new Node<T>(item);
         tail = head;
      } else {
         head.setPrevious(new Node <T>(item));
         head = head.previous;
      }
      size++;
   }
   public void remove(T item){
      Node <T> tmp = head;
      while (tmp!=null){
         if (tmp.value.equals(item)){
            size--;
            if (tmp.previous!=null){
               tmp.previous.setNext(tmp.next);
               if (tmp.next == null){
                  tail = tail.previous;
               }
            } else {
               if (tail == head){
                  tail = null;
               }
               head = head.next;
               if (head!=null)
                  head.previous =null;
            }
            return;
         }
         tmp = tmp.next;
      }
   }
   public T at(int i){
      if (i < 0 || i>=size){
         throw new NullPointerException();
      } else {

         Node <T> tmp = head;
         for(int j=0;j<i;j++){
            tmp = tmp.next;
         }
         return tmp.value;
      }
   }
   public int size(){
      return size;
   }
   public static<T> BiDirList<T> from(T[] array){
      BiDirList<T> biDirList = new BiDirList<>();
      for(T x:array)
         biDirList.addLast(x);
      return biDirList;
   }
   public static<T> BiDirList<T> of(T...array){
      BiDirList<T> biDirList = new BiDirList<>();
      for(T x:array)
         biDirList.addLast(x);
      return biDirList;
   }
   public void toArray(T[] array){
      Node <T> tmp = head;
      int i =0;
      while (tmp!=null){
         array[i] = tmp.value;
      }
   }

   public static void main(String[] args) {
      BiDirList <Integer> biDirList = new BiDirList<>();
      biDirList.addFirst(1);
      biDirList.addLast(2);
      biDirList.addLast(3);
      for(var a:biDirList){
         System.out.println(a);
      }
      biDirList.forEach(System.out::println);
      System.out.println(biDirList.at(1));
      biDirList.remove(3);
      biDirList.remove(1);
      biDirList.addLast(4);
      biDirList.remove(2);
      System.out.println(biDirList.at(0));
      Iterator<Integer> it = biDirList.iterator();
   }
}
