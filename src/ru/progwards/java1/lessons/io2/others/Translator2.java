package ru.progwards.java1.lessons.io2.others;

public class Translator2 {
    private final String[] inLang;
    private final String[] outLang;
    public Translator2(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }
    public String translate(String sentence) {
        StringBuilder result = new StringBuilder();
        // разбиваем строку на составные части: слова, пробелы и знаки препинания
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = sentence.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                if (i <= chars.length - 2) {
                    if (!Character.isAlphabetic(chars[i + 1])) stringBuilder.append('@');
                }
            } else {
                stringBuilder.append(chars[i]);
                stringBuilder.append('@');
            }
        }
        System.out.println(stringBuilder.capacity());
        // передаём строку в массив
        String[] sentenceArr = stringBuilder.toString().split("@");
        // сравниваем
        for (String s : sentenceArr) {
            int count = 0; // счётчик если совпало
            for (int j = 0; j < inLang.length; j++) {
                if (s.compareToIgnoreCase(inLang[j]) == 0) {
                    count++;
                    if (s.compareTo(inLang[j]) != 0) {
                        result.append(outLang[j].substring(0, 1).toUpperCase()).append(outLang[j].substring(1));
                    } else {
                        result.append(outLang[j]);
                    }
                }
            }
            // если не совпало, то выводим оригинал
            if (count == 0) result.append(s);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] inLang = {"привет", "qyqy", "мир"};
        String[] outLang = {"hello", "world"};
        String sentence = "Приветливый мир!";
        Translator2 translator = new Translator2(inLang, outLang);
        System.out.println(translator.translate(sentence));
    }
}