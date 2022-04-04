package ru.progwards.java1.lessons.javaformat;

/**
 * Автор: Фёдоров Александр
 * Дата:  04.04.2022  20:16
 */
public class JavaFormatter {

   public static String format(String code) {

      StringBuilder result = new StringBuilder();
      int countBrace = 0; // счетчик фигурных скобок
      boolean operator = false;
      String[] strings = code.split("\n"); // разбиение текста на строки

      for (int i = 0; i < strings.length; i++) {

         String str = strings[i].trim();
         String[] words = str.split(" "); // разбиение строки на слова

         switch (words[0]) {
            case "for", "while", "if", "else", "switch", "case" -> operator = true;
            case "}" -> countBrace--;
            default -> operator = false;
         }

         // обработка метода
         // если открывающая круглая скобка  -> склеиваем с предыдущим и со следующим
         // если закрывающая круглая скобка первый символ -> склеиваем с предыдущим
         // обработка операторов
         // если открывающая круглая  -> склеиваем со следующим
         // если закрывающая круглая  -> склеиваем с предыдущим
         // если открывающая или закрывающая квадратная скобка  -> склеиваем с предыдущим
         // для прототы понимания и отладки разнес круглые, квадратные и знаки по разным методам

         str = handlingSign(str, operator);
         str = str.replace(" .", ".");
         str = str.replace(". ", ".");

         if (str.startsWith("{")) {
            strings[i - 1] = strings[i - 1] + " {";
            countBrace++;
            str = str.replace("{", "`");
         }

         if (str.endsWith("}") && str.trim().length() > 1) { // если строка кончается на } и в ней есть что-то еще...
            str = str.trim();
            int countBraceInStr = 0;

            while (str.endsWith("}") && str.length() > 1) {
               countBraceInStr++;
               str = str.substring(0, str.length() - 1).trim();
            }

            for (int j = 0; j < countBraceInStr; j++) {
               str = str + "\n" + tab(countBrace - j - 1) + "}";
            }

            strings[i] = tab(countBrace) + str;
            countBrace = countBrace - countBraceInStr;
         } else if (str.equals("")) {
            strings[i] = str;
         } else {
            strings[i] = tab(countBrace) + str;
         }

         if (str.endsWith("{")) { // увеличиваем счетчик отступов
            countBrace++;
         }
      }

      for (String str : strings) {
         if (result.toString().equals("")) {
            result = new StringBuilder(str);
         } else if (!str.trim().equals("`")) {
            result.append("\n").append(str);
         }
      }
      return result.toString();
   }

   private static String tab(int count) { // нужное количество отступов (табуляций)
      return "    ".repeat(Math.max(0, count));
   }

   private static String handlingBracket(String str, char ch, boolean operator) { // если метод, то true, если оператор, то false
      switch (ch) {

         case '(' -> {
            str = str.replace(ch + " ", String.valueOf(ch)); // всегда убираем пробел после (
            //str = str.replace(String.valueOf(chArr[j]), " " + chArr[j]); // добавляем пробел спереди для операторов
            //str = str.replace("  ", " "); // убираем двойные пробелы

            if (!operator) { // если метод, то убираем пробелы спереди
               str = str.replace(" " + ch, String.valueOf(ch));
            }
         }

         case ')' -> str = str.replace(" " + ch, String.valueOf(ch)); // всегда убираем пробел перед )

         default -> {
         }
      }
      return str;
   }

   private static String handlingSquareBracket(String str, char ch) {
      switch (ch) {

         case '[' -> {
            str = str.replace(ch + " ", String.valueOf(ch)); // всегда убираем пробел после [
            str = str.replace(" " + ch, String.valueOf(ch)); // всегда убираем пробел перед [
         }

         case ']' -> str = str.replace(" " + ch, String.valueOf(ch)); // всегда убираем пробел перед ]

         default -> {}
      }
      return str;
   }

   private static String handlingBrace(String str, char ch) {
      switch (ch) {

         case '}' -> {
            str = str.replace(ch + " ", String.valueOf(ch)); //  убираем пробел после {
            str = str.replace(" " + ch, String.valueOf(ch)); //  убираем пробел перед {
         }

         case '{' -> str = str.replace(ch + " ", String.valueOf(ch)); //  убираем пробел после {

         default -> {}
      }
      return str;
   }

   private static String handlingSign(String str, boolean operator) {

      String[] tmpStr = str.split("\""); // разбиваем строку на подстроки с "", чтобы не обрабатывать "строковые части"

      for (int i = 0; i < tmpStr.length; i += 2) { // пробегаем через один, чтобы не обрабатывать "строковые части"

         char[] chArr = tmpStr[i].toCharArray(); // разбиение строки на символы

         for (int j = 0; j < chArr.length; j++) {
            tmpStr[i] = handlingBracket(tmpStr[i], chArr[j], operator); // круглые скобки
            tmpStr[i] = handlingSquareBracket(tmpStr[i], chArr[j]); // квадратные скобки
            tmpStr[i] = handlingBrace(tmpStr[i], chArr[j]); // фигурные скобки

            switch (chArr[j]) {

               case '+', '-', '/', '*', '=', '<', '>', '!', '%' -> {

                  if (j + 1 < chArr.length) {

                     switch (chArr[j + 1]) {

                        case '+', '-' -> {
                           String tmp = chArr[j] + String.valueOf(chArr[j + 1]);
                           if (j - 1 > 0 && chArr[j - 2] != ';') {
                              tmpStr[i] = tmpStr[i].replace(" " + tmp, tmp);
                           }

                           tmpStr[i] = tmpStr[i].replace(tmp + " ", tmp);
                           j++;
                        }

                        case '=' -> {
                           String tmp = chArr[j] + String.valueOf(chArr[j + 1]);
                           tmpStr[i] = tmpStr[i].replace(tmp, " " + tmp + " "); // ставим нужные пробелы
                           j++;
                        }

                        default -> {
                           tmpStr[i] = tmpStr[i].replace(String.valueOf(chArr[j]), " " + chArr[j] + " ");
                        }
                     }
                  }
               }

               case ',' -> {
                  tmpStr[i] = tmpStr[i].replace(" , ", ",");
                  tmpStr[i] = tmpStr[i].replace(" ,", ",");
                  tmpStr[i] = tmpStr[i].replace(", ", ",");
                  tmpStr[i] = tmpStr[i].replace(",", ", ");
               }

               case ';' -> {
                  tmpStr[i] = tmpStr[i].replace(" ;", ";");
                  if (j + 1 < chArr.length && !Character.isWhitespace(chArr[j + 1])) {
                     tmpStr[i] = tmpStr[i].replace(";", "; ");
                  }
               }

               default -> {}
            }
            tmpStr[i] = delDoubleSpace(tmpStr[i]);
         }
      }
      return String.join("\"", tmpStr);
   }

   private static String delDoubleSpace(String str) {
      while (str.contains("  ")) {
         str = str.replace("  ", " ");
      }
      return str;
   }

   public static void main(String[] args) {

      String code = "public static void main(String  []  args) {\n" +
              "    int row=2 , column=   3;\n" +
              "    int [ ] [ ] matrix = { { 2,3,4 } , { 5,6,4 } };\n" +
              "\n" +
              "    display ( matrix );\n" +
              "\n" +
              "    int [ ] [ ] transpose = new int [ column ] [ row ] ;\n" +
              "        for(int i=0; i < row; i++) {\n" +
              "            for ( int j = 0; j<column; j ++) {\n" +
              "                transpose [ j ] [ i ] = matrix [ i ] [ j ] ;}\n" +
              "     }\n" +
              "\n" +
              "  display(transpose);\n" +
              "}\n" +
              "\n" +
              "public static void display(int[][] matrix) {\n" +
              "    System.out.println( \"The matrix is: \" ) ;\n" +
              "  for(int [ ] row : matrix)\n" +
              "  {\n" +
              "     for (int column : row) {\n" +
              "            System.out.print( column + \"    \") ;\n" +
              "        }\n" +
              "     System.out.println ();    }}";
      String code1 = format(code);
      System.out.println(code1);
   }
}

