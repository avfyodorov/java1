package ru.progwards.java1.lessons.params;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Автор: Фёдоров Александр
 * Дата:  01.10.2022  9:13
 */
public class ArrayInteger {
    private final byte[] digits;

    public ArrayInteger(int n) {
        digits = new byte[n];
        Arrays.fill(digits, (byte) 0);
    }

    public void fromString(String value) {
        byte[] temp = value.getBytes(StandardCharsets.UTF_8);
        if (digits.length < temp.length)
            return;

        for (int i = 0; i < temp.length; i++) {
            digits[i] = (byte) (temp[temp.length - 1 - i] - 48);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (byte digit : digits) {
            res.insert(0, String.valueOf(digit));
        }
        return res.toString();
    }

    boolean add(ArrayInteger num) {
        if (digits.length < num.digits.length) {
            Arrays.fill(digits, (byte) 0);
            return false;
        }

        byte k = 0;
        for (int i = 0; i < num.digits.length; i++) {
            digits[i] = (byte) (digits[i] + num.digits[i] + k);
            k = (byte) (digits[i] > 9 ? 1 : 0);
            digits[i] = k == 1 ? (byte) (digits[i] - 10) : digits[i];
        }

        if (k == 0)
            return true;
        else {
            Arrays.fill(digits, (byte) 0);
            return false;
        }
    }

    boolean add2(ArrayInteger num) {
        boolean ok = true;
        String out1 = "", out2 = "", outSum = "";
        int sum;

        for (int i = 0; i <= num.digits.length - 1; i++) {
            out1 = out1 + Byte.toString((byte) (num.digits[i]));
        }

        for (int j = 0; j < digits.length; j++) {
            out2 = out2 + Byte.toString((byte) (digits[j]));
        }
        sum = Integer.parseInt(out1) + Integer.parseInt(out2);

        outSum = String.valueOf(sum);

        if (digits.length < outSum.length()) {
            ok = false;
            for (int l = 0; l < digits.length; l++) {
                digits[l] = 0;
            }
        } else {
            fromString(outSum);
        }
        return ok;
    }
    public static void main(String[] args) {
        ArrayInteger num = new ArrayInteger(10);
        System.out.println("Новый объект размер 10 -- "+num);
        num.fromString("10");
        System.out.println("Поместили число 10. Поэлементно -- "+Arrays.toString(num.digits));
        System.out.println("Оно же --"+num);
        ArrayInteger num3 = new ArrayInteger(4);
        num3.fromString("90");
        System.out.println("Другой объект == 90 -- "+num3);
        num.add(num3);
        System.out.println("Сложил своим методом:  "+num);

        num.add2(num3);
        System.out.println("Метод О.Таран add2 = "+num);

    }
}
