package ru.progwards.java1.lessons.io2;

        import java.util.Arrays;

public class Translator2 {
    private String[] inLang;
    private String[] outLang;

    public Translator2(String[] inLang, String[] outLang) {
        this.inLang = Arrays.copyOf(inLang, inLang.length);
        this.outLang = Arrays.copyOf(outLang, outLang.length);
    }

    public String translate(String sentence) {
        if ("".equals(sentence) ) return sentence;
        if (inLang.length != outLang.length || outLang.length == 0)
            throw new ArrayIndexOutOfBoundsException("Размеры словарей не совпадают.");
        String[] strArray = sentence.split(" ");

        for (int i = 0; i < strArray.length; i++) {
            String tail = "";
            String head = "";
            char chEnd = strArray[i].charAt(strArray[i].length() - 1);
            if (!Character.isAlphabetic(chEnd)) { // проверка последнего символа на не букву
                tail = String.valueOf(chEnd);
                strArray[i] = strArray[i].substring(0, strArray[i].length() - 1);
            }
            char chStart = strArray[i].charAt(0);
            if (!Character.isAlphabetic(chStart)) { // проверка first символа на не букву
                head = String.valueOf(chStart);
                strArray[i] = strArray[i].substring(1);
            }
            boolean flag = true;
            for (int j=0; j<inLang.length; j++){
                if (strArray[i].toLowerCase().equals(inLang[j])) {
                    if (Character.isUpperCase(strArray[i].charAt(0))) {
                        strArray[i] = outLang[j];
                        strArray[i] = head + Character.toUpperCase(strArray[i].charAt(0)) + strArray[i].substring(1) + tail;
                    } else {
                        strArray[i] = head + outLang[j] + tail;
                    }
                    flag =false;
                    break;
                }
            }
            if (flag) strArray[i] = head + strArray[i] + tail;

        }
        return String.join(" ", strArray);    }

    public static void main(String[] args) {
//        String[] inLang = {"make", "love", "not", "war"};
//        String[] outLang = {"твори", "любовь", "не", "войну"};

        String[] inLang = {"hello", "full", "world", "shit"};
        String[] outLang = {"привет", "комплекс", "мир", "мероприятий"};

       Translator2 translator = new Translator2(inLang, outLang);
//        System.out.println(translator.translate("Make Love, not war."));
        System.out.println(translator.translate("Hello. java, full Qyqy"));
    }
}