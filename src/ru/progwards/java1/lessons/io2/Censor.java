package ru.progwards.java1.lessons.io2;

//Создать статический метод public static void censorFile(String inoutFileName, String[] obscene),
// в котором прочитать файл inoutFileName и заменить слова, содержащиеся в String[] obscene на '*',
// соответствующие количеству символов в слове, изменения записать в исходный файл.
// В случае возникновения ошибки, выбросить свое собственное исключение CensorException
// в котором сохранить - строку, полученную у оригинального exception через метод getMessage() и имя файла,
// в котором возникла ошибка. В классе перекрыть метод toString(), вернув <имя файла>:<строка ошибки>.
// Класс CensorException разместить в классе Censor
//
//        Например файл содержит:
//        Java — строго типизированный объектно-ориентированный язык программирования, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).
//        obscene = {"Java", "Oracle", "Sun", "Microsystems"}
//
//        Должен выдать результат:
//        **** — строго типизированный объектно-ориентированный язык программирования, разработанный компанией *** ************ (в последующем приобретённой компанией ******).

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Censor {
   public static class CensorException extends Exception {
      public CensorException(String message, String filename) {
         super(message);
         this.message = message;
         this.filename = filename;
      }

      private String filename;
      private String message;

      @Override
      public String toString() {
         return filename + ":" + message;
      }
   }

   public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {

      try (RandomAccessFile f = new RandomAccessFile(inoutFileName, "rw")) {

//прочитать сразу весь файл
         byte[] b = new byte[(int) f.length()];
         f.readFully(b);
         String res = new String(b);

//подготовить звёздочки искать сq   лово в строке
         for (String str : obscene) {
            res = res.replaceAll(str, "*".repeat(str.length()));
         }

//записать обратно
         System.out.println(res);
         f.seek(0);
         f.write(res.getBytes());
         f.setLength(f.getFilePointer()); //режем хвост

      } catch (Exception e) {
         throw new CensorException(e.getMessage(), inoutFileName);
      }
   }

   public static void censorFile2(String inoutFileName, String[] obscene) throws CensorException {
      StringBuilder text = new StringBuilder();
      // читаем файл в строку
      try (FileReader fr = new FileReader(inoutFileName)) {
         Scanner sc = new Scanner(fr);
         while (sc.hasNextLine()) {
            text.append(sc.nextLine());
         }
      } catch (Exception e) {
         throw new CensorException(e.getMessage(), inoutFileName);
      }
      // ищем совпадения и меняем на *
      for (int i = 0; i < obscene.length; i++) {
         int iO = text.indexOf(obscene[i]);
         if (iO != -1) {
            StringBuilder obs = new StringBuilder("");
            for (char c : obscene[i].toCharArray()) {
               c = '*';
               obs.append(c);
            }
            text.replace(iO, iO + obscene[i].length(), obs.toString());
         }
      }
      // записываем в файл
      try (FileWriter fw = new FileWriter(inoutFileName)) {
         fw.write(text.toString());
      } catch (Exception e) {
         throw new CensorException(e.getMessage(), inoutFileName);
      }
   }

   //  Буря мглою небо кроет,
//  Вихри снежные крутя;
//  То как зверь она завоет,
//  То заплачет как дитя.
   public static void main(String[] args) {
      String[] obscene = {"мглою", "снежные", "То", "дитя"};
      //{"Java", "Oracle", "Sun", "Microsystems"};
      try {
         censorFile("censor.txt", obscene);
      } catch (CensorException e) {
         System.out.println(e.toString());
      }
   }
}
