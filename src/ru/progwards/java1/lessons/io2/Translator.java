package ru.progwards.java1.lessons.io2;

//Создать класс Translator - переводчик, который будет переводить предложения с одного языка на другой
//
//        2.1 Конструктор Translator(String[] inLang, String[] outLang),
//        где inLang и outLang - массивы слов на разных языках, например русском и английском.
//        Сохранить значения параметров в приватных свойствах класса
//
//        2.2  Метод public String translate(String sentence), в котором найти слова,
//        содержащиеся в sentence и в inLang и заменить их на соответствующие в outLang.
//        Пунктуация должна быть соблюдена (скопирована из оригинальной строки).
//        При этом надо соблюсти заглавные буквы, если они были в оригинальном тексте.
//        В inLang и outLang все слова хранятся в нижнем регистре.
//
//        Например, фраза
//        "Hello World!" будет переведена как "Привет Мир!"
//        при наличии слов "hello", "world" в inLang и "привет", "мир" в outLang


public class Translator
{
  String[] inLang;
  String[] outLang;

  Translator(String[] inLang, String[] outLang)
  {
    this.inLang = inLang;
    this.outLang = outLang;

    if (inLang.length != outLang.length)
      throw new ArrayIndexOutOfBoundsException("Размеры словарей не совпадают.");
  }

  public String translate(String sentence)
  {
    String res = "";
    String finded = "";

//поделить на эл-ты
    String[] items = sentence.split(" ");

    for (int i = 0; i < items.length; i++)
    {
//определить заглавную спереди и пунктуацию сзади
      boolean up = Character.isUpperCase(items[i].charAt(0));
      boolean pt = Character.isLetter(items[i].charAt(items[i].length() - 1));

      boolean flfinded = false;

//срезать хвост если есть
      String s = pt ? items[i] : items[i].substring(0, items[i].length() - 1);
//искать элемент
      for (int j = 0; j < inLang.length; j++)
      {
//
        flfinded = s.equalsIgnoreCase(inLang[j]);
        if (flfinded)
        {
//слишком короткий выходной массив -
          if (j >= outLang.length)
          {
            flfinded = false;
            break;
          }

//нашли. взять из аут-массива, приделать голову и хвост
          finded = outLang[j];
          if (up)
            finded = "" + Character.toUpperCase(finded.charAt(0)) + finded.substring(1);
          if (!pt)
            finded = finded + items[i].substring(items[i].length() - 1);

          res = res + " " + finded;
          break;
        }
      }

//если не нашли - оставить что пришло на входе
      if (!flfinded)
        res = res + " " + items[i];

    }
    return res;
  }

  public static void main(String[] args)
  {
//    System.out.println("Значение \".\" буква? Ответ: " + Character.isLetter('.'));

    String[] inLang = {"hello", "full", "world", "shit"};
    String[] outLang = {"привет", "комплекс", "мир", "мероприятий"};

    Translator translator = new Translator(inLang, outLang);
    System.out.println(translator.translate("Hello. java, full Qyqy"));
  }
}
