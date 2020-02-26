package ru.progwards.java1.lessons.io2;

//Создать статический метод public static String format(String phone),
// который форматирует телефон под формат +7(999)111-2233, входящий формат может быть различным:
//        - 79991112233
//        - 8(999)111-22-33
//        - 8 999 111 22 33
//
//        формальное задание на форматирование:
//        - выделить все цифры, отбросить все разделители
//        - проверить что цифр в номере 11 или 10, в противном случае выбросить исключение
//        - убрать спереди 8
//        - добавить, если нужно +7
//        - отформатировать под выходной шаблон

public class PhoneNumber
{
  static StringBuilder onlyDigits(String phone)
  {
    StringBuilder res = new StringBuilder();

    for (char c : phone.toCharArray())
      if (Character.isDigit(c))
        res.append(c);

    return res;
  }

  public static String format(String phone)
  {
//сжать, оставить только цифры
    StringBuilder res = onlyDigits(phone);

//кол-во цифр или исключение
    if (res.length() < 10 || res.length() > 11)
      throw new NumberFormatException("требуется 10-11 цифр в номере");

//убрать 8,
    if (res.length() == 11 && res.charAt(0) == '8')
      res.deleteCharAt(0);

//добавить +7
    if (res.length() == 10)
      res.insert(0, '7');

//формат +7(999)111-2233
    res.insert(7, '-');
    res.insert(4, ')');
    res.insert(1, '(');
    res.insert(0, '+');

    return res.toString();
  }

  public static void main(String[] args)
  {
    try
    {
      System.out.println(format("8(999)111-22-33"));
    } catch (NumberFormatException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
