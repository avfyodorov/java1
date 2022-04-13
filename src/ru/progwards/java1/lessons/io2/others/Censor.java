package ru.progwards.java1.lessons.io2.others;

/**
 * Автор: Фёдоров Александр
 * Дата:  07.04.2022  21:02
 */

        import java.io.*;
        import java.util.Scanner;

public class Censor {

   public static class CensorException extends Exception {
      private final String filename;

      public CensorException(String filename) {
         super();
         this.filename = filename;
      }

      @Override
      public String toString() {
         return filename + ":" + this.getMessage();
      }
   }

   public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
      StringBuilder result = new StringBuilder();
      StringBuilder temp = new StringBuilder();

      try {
         FileReader file = new FileReader(inoutFileName);
         Scanner scanner = new Scanner(file);
         int countLine = 0;  // номер строки в файле

         while (scanner.hasNextLine()) {
            temp.append(scanner.nextLine()); // получаю строку из файла
            countLine++;
            int start; // индекс первого символа совпадения
            int end;   // диапазон замены (для метода replace)

            for (String word : obscene) {
               start = temp.indexOf(word);
               if (start != -1) {
                  end = start + word.length();
               } else {
                  System.out.println("Строка №-" + countLine + ": Совпадение с \"" + word + "\" - не найдено");
                  continue;   // перехожу к следующему значению word
               }
               temp.replace(start, end, replaceToStar(word)); // произвожу замену совпадения в строке
            }

            result.append(temp).trimToSize(); // записываю полученный результат в строку result
            temp.delete(0, temp.length());    // обнуляю временную строку
         }

         FileWriter writer = new FileWriter(inoutFileName, false);
         writer.write(String.valueOf(result));  // перезаписываю изменения в файл inoutFileName
         writer.close();
         file.close();

      } catch (Exception e) {
         throw new CensorException(inoutFileName);
      }
   }

   public static String replaceToStar(String word) {
      return "*".repeat(word.length());
   }

   // test
   public static void main(String[] args) throws Exception {
      String[] obscene = {"Java", "Oracle", "Sun", "Microsystems"};
      String pathName = "C:\\Users\\petr\\HomeWork\\src\\ru\\progwards\\java1\\lessons\\io2\\text.txt";
      String pathName3 = "simple22.txt";
      censorFile(pathName, obscene);
      censorFile(pathName3, obscene);
   }
}

