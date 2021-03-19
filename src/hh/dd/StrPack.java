package hh.dd;

/**
 * Тестовое задание от Digital Design: распаковка строки и проверка валидности.
 *
 *         На вход поступает строка вида число[строка],
 *         на выход - строка, содержащая повторяющиеся подстроки.
 *
 *         Пример:
 *         Вход: 3[xyz]4[xy]z
 *         Выход: xyzxyzxyzxyxyxyxyz
 *
 *         Ограничения:
 *         - одно повторение может содержать другое. Например: 2[3[x]y]  = xxxyxxxy
 *         - допустимые символы на вход: латинские буквы, числа и скобки []
 *         - числа означают только число повторений
 *         - скобки только для обозначения повторяющихся подстрок
 *         - входная строка всегда валидна.
 */
public class StrPack {
   /**
    * Функция распаковки строки
    * @param inStr входящая строка
    * @return  распакованная строка
    */
   public static String unpack(String inStr) {
      String res = inStr;

//пока есть закрывающая скобка
      int fin = res.indexOf(']');
      while (fin > 2) {
//выделить подстроку
         int start = fin - 1;
         while (start >= 0 && res.charAt(start) != '[')
            start--;
         String substr = res.substring(start + 1, fin);

//выделить коеф. повтора
         int beg = start - 1;
         while (beg >= 0 && Character.isDigit(res.charAt(beg)))
            beg--;
         int koef = Integer.parseInt(res.substring(beg + 1, start));

//повторить подстроку
         substr = substr.repeat(koef);
//сложить все вместе
         res = res.substring(0, beg + 1) + substr + res.substring(fin + 1, res.length());
//продолжаем
         fin = res.indexOf(']');
      }
      return res;
   }

   /**
    * Функция для проверки валидности строки
    * @param inStr проверяемаяя строка
    * @return true если можно распаковывать
    */
   public static boolean isCorrect(String inStr) {

//баланс скобок
      int balance = 0;

      for (int i = 0; i < inStr.length(); i++) {
         switch (inStr.charAt(i)) {
//
            case '[':
               balance++;
               break;
//ошибка если закрывающих больше, чем открывающих
            case ']':
               balance--;
               if (balance < 0) {
                  System.out.println("Закрывающая скобка не на месте. Позиция " + i);
                  return false;
               }
               break;
//могут быть только буквы или цифры
            default:
               if (!Character.isLetterOrDigit(inStr.charAt(i))) {
                  System.out.println("Недопустимый символ. Позиция " + i);
                  return false;
               }
         }
      }

      if (balance == 0)
         return true;
      else {
         System.out.println("Нарушен баланс скобок.");
         return false;
      }
   }

   public static void main(String[] args) {
      String s = "3[xyz]4[xy]z";
      if (isCorrect(s))
         System.out.println(s + "  - " + unpack(s));

      s = "w2[10[x]y]";
      if (isCorrect(s))
         System.out.println(s + " - " + unpack(s));

      s = "w 2[10[x]y]";
      if (isCorrect(s))
         System.out.println(s + " - " + unpack(s));

      s = "w2][10[x]y]";
      if (isCorrect(s))
         System.out.println(s + " - " + unpack(s));

      s = "w2[10[xy]";
      if (isCorrect(s))
         System.out.println(s + " - " + unpack(s));
   }
}
