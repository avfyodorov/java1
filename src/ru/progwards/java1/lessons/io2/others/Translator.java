package ru.progwards.java1.lessons.io2.others;

/**
 * Автор: Фёдоров Александр
 * Дата:  07.04.2022  21:25
 */
public class Translator {

   private final String[] inLang;
   private final String[] outLang;

   Translator(String[] inLang, String[] outLang) {
      this.inLang = inLang;
      this.outLang = outLang;
   }

   /*
   Метод позволяет переводить строку с одного языка на другой (только в определенном порядке (например с русского на
   английский или наоборот)), перевод осуществляется с использованием inLang[] и outLang[]
   При этом перевод текста сохраняет заглавные буквы в словах и пунктуацию строки.
   Порядок слов в строке может быть произвольным, слова могут повторяться.
    */
   public String translate(String sentence) {

      if (sentence == null || sentence.isEmpty()) {
         throw new NullPointerException(sentence + ": Пустая строка /String is empty");
      }

      String[] words = sentence.trim().split(" ");    // получаю массив строк из строки-параметра метода
      StringBuilder checkWord = new StringBuilder();       //  переменная для хранения сравниваемого слова
      StringBuilder transformedWord = new StringBuilder(); //  переменная для хранения заменяемого слова
      int indexFirstLatter;                                //  индекс первой буквы слова в строке
      int indexLastLatter;                                 //  индекс последней буквы в строке

      for (int i = 0; i < words.length; i++) {
         for (int j = 0; j < inLang.length; j++) {
            indexFirstLatter = words[i].toLowerCase().indexOf(inLang[j]); // произвожу поиск слова, которое нужно перевести и запоминаю индекс первой буквы переводимого слова в элементе words[]
            if (indexFirstLatter != -1) {
               indexLastLatter = indexFirstLatter + inLang[j].length();  // вычисляю конечный индекс в элементе words[]
               transformedWord.append(words[i]);                         // определяю слово, которое нужно перевести
               checkWord.append(outLang[j]);                             // определяю слово, на которое нужно перевести
               if (Character.isUpperCase(words[i].charAt(indexFirstLatter))) {  // проверяю наличие заглавной буквы
                  checkWord.replace(0, 1, String.valueOf(outLang[j].charAt(0)).toUpperCase());  // меняю первую букву на заглавную в checkWord (при выполнении условия)
               }
               words[i] = String.valueOf(transformedWord.replace(indexFirstLatter, indexLastLatter, checkWord.toString())); // преобразую элемент words[]
               checkWord.delete(0, checkWord.length());               // обнуляю значение переменной
               transformedWord.delete(0, transformedWord.length());   // обнуляю значение переменной
               break;                                                 // прерываю итерацию и перехожу к следующему элементу words[]
            }
         }
      }
      return String.join(" ", words);  // формирую перевод строки
   }

   // test
   public static void main(String[] args) {

      String[] arrayListIn = {"привет", "мир"};
      String[] arrayListOut = {"hello", "word"};
      Translator translator = new Translator(arrayListIn, arrayListOut);

      String testString = "   9900 Привет, Мир!!!!! мир, Мир..... мир Привет привет!   ";
      String testString2 = "   мир Привет!   ";
      String testString3 = "   Привет, Мир привет!    ";
      String testString4 = "   Привет Мир!!!";

      System.out.println(translator.translate(testString));
      System.out.println(translator.translate(testString2));
      System.out.println(translator.translate(testString3));
      System.out.println(translator.translate(testString4));
   }
}

