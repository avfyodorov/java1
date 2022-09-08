package examples;

/**
 * Автор: Фёдоров Александр
 * Дата:  28.04.2022  18:07
 */
public class CompareString {
    static boolean comp(String s1, String s2){
        return s1.equals(s2);
    }

    public static void main(String[] args) {
        System.out.println(comp("На Java работает ",""));
        System.out.println(comp("",""));
        System.out.println(comp("",""));
        System.out.println(comp("",""));
    }
}
