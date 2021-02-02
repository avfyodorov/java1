package misc.alexandr320.generics;

public class DynamicArray<T> {

   public static void main(String[] args) {
      DynamicArray<String> dynamicArray = new DynamicArray<>();
      dynamicArray.add("0");
      dynamicArray.add("1");
      dynamicArray.add("2");
      dynamicArray.add("3");
      dynamicArray.add("a");
      dynamicArray.add("bb");
      dynamicArray.add("ccc");
      dynamicArray.insert(4, "5");
      dynamicArray.insert(4, "4");
      for (int i = 0; i < dynamicArray.size(); i++) {
         System.out.println(dynamicArray.get(i));
      }
      System.out.println("====================");
      dynamicArray.remove(3);
      dynamicArray.remove(3);
      for (int i = 0; i < dynamicArray.size(); i++) {
         System.out.println(dynamicArray.get(i));
      }
   }

   private T[] array = (T[]) new Object[10];
   //Здесь правильнее сделать так:
   //private T[] array;


   private int index;  // Первый свободный индекс (так же равен количеству элементов)

   public void add(T element) {  // добавляет элемент в конец массива
      if (needNewSize()) {
         newSize();
      }
      array[index++] = element;
   }

   public void insert(int pos, T element) { // добавляет элемент в заданную позицию массива
      if (pos > index || pos < 0) {
         throw  new IndexOutOfBoundsException("Ошибка размера");
      }
      if (needNewSize()) {
         newSize();
      }
      for (int i = index; i > pos; i--) {
         array[i] = array[i - 1];
      }
      array[pos] = element;
      index++;
   }

   public void remove(int pos) {  // удаляет элемент в позиции pos массива
      if (pos >= index || pos < 0) {
         throw  new IndexOutOfBoundsException("Ошибка размера");
      }
      // Осталось сделать удаление элемента
      for (int i = pos+1; i < index; i++) {
         array[i-1] = array[i];
      }
      array[index-1] = null;
      index--;
   }

   public T get(int pos) {  // возвращает элемент по индексу pos
      if (pos >= index || pos < 0) {
         throw  new IndexOutOfBoundsException("Ошибка размера");
      } else {
         return (T) array[pos];
      }
   }

   public int size() {  // возвращает текущий реальный объем массива
      return index;
   }

   private boolean needNewSize() {
      return index >= array.length;
   }

   private void newSize() {
      T[] myArray1 = (T[]) new Object[array.length * 2];
      //Object[] myArray1 = new Object[Math.round(myArray.length * 1.5F)];
      // F - чтобы сделать float, т.к. есть два разных round и только round(float) возвращает int
      for (int i = 0; i < array.length; i++) {
         myArray1[i] = array[i];
      }
      array = myArray1;
   }

}

