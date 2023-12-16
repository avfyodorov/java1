package stepic;

//import java.util.*;

/**
 * Автор: Фёдоров Александр
 * Дата:  16.12.2023  15:06
 */
public class L24_TextPerRole {
    private String printTextPerRole(String[] roles, String[] textLines) {
        //создать подходящую структуру
        java.util.Map<String, java.util.List<String>> lines = new java.util.HashMap<>();

        //заполнить ролями
        for (String s : roles) {
            lines.put(s, new java.util.ArrayList<>());
        }

        //Читать текст построчно, разбить на ключ-значение, заполнить структуру.
        for (int i = 0; i < textLines.length; i++) {
            //Разбить один раз-только левое ':'
            String[] s = textLines[i].split(":", 2);
            java.util.List<String> arr = lines.get(s[0]);
            arr.add((i + 1) + ")" + s[1].stripTrailing() + "\n");
        }

        //Собрать результат. Роли в порядке поступления из входного параметра.
        StringBuilder sb = new StringBuilder();
        for (String role : roles) {
            //добавить роль
            sb.append(role).append(":\n");
            //список реплик
            java.util.List<String> arr = lines.get(role);
            for (String s : arr){
                sb.append(s);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        L24_TextPerRole mane = new L24_TextPerRole();
        String res = mane.printTextPerRole(new String[]{//"roles:",
                        "Городничий",
                        "Аммос Федорович",
                        "Артемий Филиппович",
                        "Лука Лукич"
                },
                new String[]{//"textLines:",
                        "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                        "Аммос Федорович: Как ревизор?",
                        "Артемий Филиппович: Как ревизор?",
                        "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                        "Аммос Федорович: Вот те на!",
                        "Артемий Филиппович: Вот не было заботы, так подай!",
                        "Лука Лукич: Господи боже! еще и с секретным предписаньем!"

                });
        System.out.println(res);
    }
}
