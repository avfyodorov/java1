package examples.les09;

import java.util.HashMap;
import java.util.Map;

/**
 * Автор: Фёдоров Александр
 * Дата:  24.06.2022  10:54
 */
public class Base1 {
    public static void main(String[] args) {
        Map<Integer, String> m = new HashMap<>();
        m.put(1, "1");
        System.out.println(m.get(18));


        printJava();
        printJava("Чтобы Java понимать", "Надо функции писать");
        printJava("Буду, буду программистом", "Код пишу я чисто, чисто");
        String str = plusJava("самый популярный");
        System.out.println(str);
        str = plusJava("объектно-ориентированный");
        System.out.println(str);
        System.out.println(plusJava("очень интересный"));
    }

    public static void printJava() {
        String s1 = "Хорошо идут дела";
        String s2 = "Изучаю Java я!";
        String s3 = " ";
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 + " " + s2);
        System.out.println(s2 + " " + s1);
    }

    public static void printJava2() {
        String line1 = "Хорошо идут дела";
        String line2 = "Изучаю Java я!";
        String line3 = " ";
        System.out.println(line1);
        System.out.println(line2);
        System.out.print(line1);
        System.out.print(line3);
        System.out.println(line2);
//        System.out.println(line3);
        System.out.print(line2);
        System.out.print(line3);
        System.out.println(line1);
//        System.out.println(line3);
    }

    public static void printJava(String java1, String java2) {
        System.out.println(java1);
        System.out.println(java2 + "!");
        System.out.println(java1 + ", " + java2 + "!");
        System.out.println(java2 + ", " + java1 + "!");
    }

    public static String plusJava(String message) {
        return "Java - " + message + " язык программирования";
    }
}
