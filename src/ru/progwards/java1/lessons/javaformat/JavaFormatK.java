package ru.progwards.java1.lessons.javaformat;

import java.util.StringTokenizer;

/**
 * Автор: Фёдоров Александр
 * Дата:  04.04.2022  19:17
 */
public class JavaFormatK {

   public static String format(String code) {

      String ress = "";

//         code = "public static void main(String [] args)\n" +
//                 "   {\n" +
//                 "     int num = 1234, reversed = 0;\n" +
//                 "  System.out.println(\"Original Number: \" + num);\n" +
//                 "    while(num != 0)\n" +
//                 " {\n" +
//                 "        int digit = num%10;\n" +
//                 "        reversed=reversed*10+    digit ;\n" +
//                 "        num /= 10;\n" +
//                 "    }\n" +
//                 "    System.out.println(\"Reversed Number: \" + reversed);}";

      code = code.replaceAll("  ", " ")
              .replaceAll("  ", " ")
              .replaceAll("  ", " ")
              .replaceAll("  ", " ");

      String[] res = new String[code.length()];

      StringTokenizer st = new StringTokenizer(code, "{}\n", true);

      int teg = 0;

      for (int i = 0; st.hasMoreTokens(); ) {

         String nt = st.nextToken();

         if (nt.replace(" ", "").length() > 0) {
            res[i] = nt;
            res[i] = res[i].replace("=", " = ");
            res[i] = res[i].replace("+", " + ");
            res[i] = res[i].replace("-", " - ");
            res[i] = res[i].replace("*", " * ");
            res[i] = res[i].replace("/", " / ");
            res[i] = res[i].replace("%", " % ");

            res[i] = res[i].replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ");

            res[i] = res[i].replace("* =", "*=");
            res[i] = res[i].replace("+ =", "+=");
            res[i] = res[i].replace("/ =", "/=");
            res[i] = res[i].replace("- =", "-=");
            res[i] = res[i].replace("% =", "%=");
            res[i] = res[i].replace("! =", "!=");
            res[i] = res[i].replace(" ]", "]");
            res[i] = res[i].replace(" [", "[");
            res[i] = res[i].replace("[ ", "[");
            res[i] = res[i].replace(" )", ")");

            if (!res[i].contains("while") && !res[i].contains("for") && !res[i].contains("if"))
               res[i] = res[i].replace(" (", "(");
            else
               res[i] = res[i].replace("(", " (");

            res[i] = res[i].replace("( ", "(");
            res[i] = res[i].replace(" ;", ";");
            res[i] = res[i].replace(" .", ".");
            res[i] = res[i].replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ")
                    .replace("  ", " ");

            if (res[i].contains("{")) {
               res[i - 1] = res[i - 1].replace("\n", " ");
               teg++;
            }

            int prevTeg = teg;

            if (res[i].contains("}")) {
               res[i] = res[i].replace(" ", "");
               teg--;
            }

            if (res[i].contains("}")) {
               if (!res[i - 1].contains("\n") && (!res[i].trim().equals("}") || !res[i].contains("\n"))) {
                  res[i] = res[i].replace("}", "\n}");
               }
            }

            if (teg > 0
                    && !res[i].replace(" ", "").replace("\n", "").equals("{")
                    && res[i].replace(" ", "").replace("\n", "").length() > 0) {

               String spaces = "";

               int lstj = 0;
               for (int j = 0; j < prevTeg; j++) {
                  if (res[i].contains("}")) {
                     if (j + 1 < prevTeg)
                        spaces += "    ";
                  } else
                     spaces += "    ";
                  lstj = j;
               }

               if (lstj + 1 < prevTeg)
                  res[i] = spaces + res[i].trim();
               else
                  res[i] = spaces + res[i];
            }
            i++;
         }
      }

      for (String rl : res) {
         if (rl == null)
            break;
         ress += rl.replace("     ", "    ");
      }
      return ress;
   }

   public static void main(String[] args) {
      System.out.println(format(""));
   }
}


