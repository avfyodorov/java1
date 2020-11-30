package ru.progwards.java1.lessons.io2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Censor_Minkin {

   public static void censorFile(String inoutFileName, String[] obscene) throws IOException {
      String lineFromFile = "";

      try(RandomAccessFile rafCensor = new RandomAccessFile(inoutFileName, "rw")){  //Поток для записи в файл)
         byte[] bytes = new byte[(int) rafCensor.length()];
         rafCensor.read(bytes);
         String textFromFile = new String(bytes);


//            for (String str:obscene){
////                textFromFile = textFromFile.replaceAll(str, stars(str));
//            }
         String res = textFromFile;
         for (int i = 0; i < obscene.length; i++){
            //искать слово в строке
            int j = res.indexOf(obscene[i]);
            while (j != -1)
            {
               String stars = stars(obscene[i]);
               res = res.substring(0, j) + stars + res.substring(j + stars.length());
               j = res.indexOf(obscene[i]);
            }
         }



//записать обратно
         System.out.println(res);

         rafCensor.seek(0);     //перевожу курсор в нужную позицию
         rafCensor.write(res.getBytes());

         rafCensor.setLength(rafCensor.getFilePointer());

      }catch (FileNotFoundException fnfExc){
         System.out.println("Errooooor");
      }
      catch (IOException ioExc){

      }

   }
   //Метод для определения количества знаков "" для замены слова
   public static String stars(String word) {
      return "*".repeat(word.length());   //Или собрать в цикле с помощью StringBuilder
   }
//  Буря мглою небо кроет,
//  Вихри снежные крутя;
//  То как зверь она завоет,
//  То заплачет как дитя.
   public static void main(String[] args)
   {
      String[] obscene ={"мглою","снежные","То","дитя"};
      //{"Java", "Oracle", "Sun", "Microsystems"};
      try
      {
         censorFile("censor.txt", obscene);
      } catch (IOException e)
      {
         System.out.println(e.toString());
      }
   }

}
