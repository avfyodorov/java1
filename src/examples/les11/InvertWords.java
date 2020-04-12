package examples.les11;

//Реализуйте метод с сигнатурой public String invertWords(String sentence),
// который переставляет слова, в полученной фразе. В качестве исходного разделителя
// использовать пробел. В качестве соединительного точку.
//
//        Например,
//        invertWords("Буря мглою небо кроет") должен вернуть
//        "кроет.небо.мглою.Буря"

public class InvertWords
{
  String invertWords(String sentence)
  {
    String res = "";
    String[] s = sentence.split(" ");

    for (int i = s.length - 1; i >= 0; i--)
      res = res + s[i] + ".";

    return res.substring(0, res.length() - 1);
  }

  public static void main(String[] args)
  {
    InvertWords iw = new InvertWords();
    System.out.println(iw.invertWords(("Буря мглою небо кроет")));
  }
}
