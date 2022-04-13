package ru.progwards.java1.lessons.io2.others;

/**
 * Автор: Фёдоров Александр
 * Дата:  07.04.2022  21:18
 */
public class PhoneNumber {

   public static String format(String phone) throws RuntimeException {

      if (phone == null || phone.isEmpty()) {
         throw new NullPointerException(phone + ": Номер не введён /No number entered");      // Бросаю исключение, если входной параметр NULL или пустой

      } else {
         StringBuilder phoneNumber = new StringBuilder();
         for (char c : phone.toCharArray()) {                                                      // Преобразую строку в массив символов Char
            if (Character.isDigit(c))                                                             // Фильтрую содержимое строки, выделяя из нее только цифры.
               phoneNumber.append(c);                                                            // Получаю новую строку только из цифр.
         }

         switch (phoneNumber.length()) {                                                           // Параметром для сравнения выбираю длину строки
            case 11:
               phoneNumber.deleteCharAt(0);                                                      // Если длина строки == 11 - удаляю первый символ, который может быть 7 или 8, чтобы не делать лишних сравнений
            case 10:                                                                              // Если длина строки == 10 - привожу строку к единому формату (+7(999)111-2233)
               return String.format("+7(%s)%s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6));
            default:
               throw new StringIndexOutOfBoundsException(phone + ": Номер введен не верно /Wrong number"); // Бросаю исключение, в случаях, когда длина строки != 10 или != 11
         }
      }
   }


/*  ВТОРОЙ ВАРИАНТ
        StringBuilder phoneNumber = new StringBuilder();
        boolean isPhone = "".equals(phone);

        if (isPhone || phone == null) {
            throw new IOException("Номер отсутствует (Number is absent)");
        } else {
            for (char c : phone.toCharArray()) { // Преобразую строку в массив символов Char
                if (Character.isDigit(c)) // Фильтрую содержимое строки, выделяя из нее только цифры.
                    phoneNumber.append(c); // Получаю новую строку только из цифр.
            }

            if (phoneNumber.length() < 10 || phoneNumber.length() > 11) {
                throw new NumberFormatException(phone + " - Wrong number length / Неправильный номер");
            } else if (phoneNumber.length() == 11) { // если длина строки == 11, то
                phoneNumber.delete(0, 1); // удаляю первый символ из строки (который может быть 7 или 8), чтобы не делать лишних сравнений
            }
        }
        // привожу строку к единому формату (+7(999)111-2233)
        return String.format("+7(%s)%s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6));
    }
 */

   public static void main(String[] args) {
      try {
         System.out.println(format("    &()#$%00136 57  00-20"));
         System.out.println(format(" 8(345)12-34-456"));
         System.out.println(format("(878)333-44-55)"));
         System.out.println(format("+78789-99-88-75)"));
         System.out.println(format("+7(495)5-49-81-25"));


      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
      }
   }
}
