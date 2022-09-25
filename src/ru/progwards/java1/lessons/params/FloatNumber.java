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
        double d = Double.parseDouble(number);
        String temp = String.format("%1E", d).replace(',', '.');

        //знак.
        sign = temp.charAt(0) == '-' ? false : true;
        //убрать минус из строки
        if (!sign)
            temp = temp.replace('-', ' ').trim();

        // разделить на 2 строки: мантисса и степень.
        String[] s = temp.split("E");
        exp = Integer.parseInt(s[1]);

        //Если первая цифра в мантиcce не 0, сдвинуть вправо, убрать точку, увеличить степень.
        if (s[0].charAt(0) != '0') {
            s[0] = s[0].substring(0, 1) + s[0].substring(2);
            exp++;
        } else
            s[0] = s[0].substring(2);

        mantissa = Long.parseLong(s[0]);

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
        String res = sign ? "0." : "-0.";
        res = res + Long.toString(mantissa);
        if (exp > 0)
            res = res + "E" + Integer.toString(exp);

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

    }
}
