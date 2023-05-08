package ru.progwards.java1.lessons.params;

/**
 * Автор: Фёдоров Александр
 * Дата:  25.09.2022  12:23
 */
public class FloatNumber {
    boolean sign;   // - знак (+/-)
    long mantissa;  // - мантиса (всегда положительная)
    int exp;        // - порядок - 10 в степени exp

    public FloatNumber(boolean sign, long mantissa, int exp) {
        this.sign = sign;
        this.mantissa = mantissa;
        this.exp = exp;
    }

    /*
    Принимает String и сохраняет  знак, мантиссу и порядок отдельно.
    Разбор строки сделать самостоятельно, поддержать формат:
            [+|-]9[.9][E9]
    */
    public FloatNumber(String number) {
        //Привести число к нужному формату.
        String temp = String.format("%1E", Double.valueOf(number)).replace(',', '.');

        // разделить на 2 строки: мантисса и степень.
        String[] s = temp.split("E");

        int point = s[0].indexOf('.');
        if (point > -1)
            s[0] = s[0].substring(0, point) + s[0].substring(point + 1);
        mantissa = Long.parseLong(s[0]);
//Мантисса всегда положительная
        if (mantissa < 0) {
            sign = false;
            mantissa = -mantissa;
        } else
            sign = true;
//степень
        exp = Integer.parseInt(s[1]);
    }

    /*
        Приводит число к строке, выводить в формате:

                [-]1[.9][E9]

        где 1 это всегда одна цифра
        остальные значащие цифры выводить после точки
        если порядок == 0, то его не выводить, во всех остальных случаях он должен быть
        знак + не выводить
    */
    @Override
    public String toString() {
//Принудительно вставить точку после первой цифры.
        String res = String.valueOf(mantissa);
        if (mantissa > 0)
            res = res.charAt(0) + "." + res.substring(1);
//Добавить степень
        if (exp != 0)
            res = res + "E" + exp;
//знак
        if (!sign)
            res = "-" + res;
        return res;
    }

    public double toDouble() {
        return Double.valueOf(toString());
    }

    /*
    принимает double и сохраняет  знак, мантиссу и порядок отдельно.
     */
    public void fromDouble(double num) {
        FloatNumber fn = new FloatNumber(Double.toString(num));
        sign = fn.sign;
        mantissa = fn.mantissa;
        exp = fn.exp;
    }

    public void negative() {
        sign = !sign;
    }

    public FloatNumber add(FloatNumber num) {
        return new FloatNumber(Double.toString(toDouble() + num.toDouble()));
    }

    public FloatNumber sub(FloatNumber num) {
        return new FloatNumber(Double.toString(toDouble() - num.toDouble()));
    }

    public static void main(String[] args) {

        System.out.println(new FloatNumber("-0.45456e7"));
        System.out.println(new FloatNumber("-123.45456e7"));
        System.out.println(new FloatNumber("-12e7"));
        System.out.println(new FloatNumber(" -123.45456"));
        System.out.println(new FloatNumber(" 123"));
        System.out.println(new FloatNumber(" 0"));

        System.out.println(new FloatNumber("8.5").sub(new FloatNumber("1.2")));
        System.out.println(new FloatNumber("93810e2").add(new FloatNumber("85699e3")));

//        System.out.println(new FloatNumber(true,645630,8));
    }
}
