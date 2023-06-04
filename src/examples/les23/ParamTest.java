package examples.les23;

import examples.Arrays;

/**
 * Автор: Фёдоров Александр
 * Дата:  12.05.2023  19:24
 */
public class ParamTest {
    public static void main(String[] args) {
        Integer n1 = 22;
        Integer[] narr = {1, 2, 13, 66};

        ParamTest p = new ParamTest();
        p.some1(n1);
        //Проверить, Удалось ли изменить значение входного параметра
        System.out.println(n1);

        p.somearr(narr);
        //Проверить, удалось ли изменить значение элемента массива из метода
        System.out.println(narr[0]);
    }

    //Попытка изменить значение входного параметра.
    void some1(Integer n) {
        n = 44;
    }

    //Изменить значение элемента входного массива
    void somearr(Integer[] na) {
        na[0] = 999;

    }
}
