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

import java.io.IOException;
import java.io.RandomAccessFile;

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

//подготовить звёздочки искать слово в строке
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
