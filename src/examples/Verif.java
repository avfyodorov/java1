package examples;

/**
 * Автор: Фёдоров Александр
 * Дата:  22.11.2022  21:38
 */
public class Verif {
    boolean b() throws RuntimeException {
        boolean fl = true;
        if (true) {
            fl = false;
            throw new RuntimeException("opa");
        }
        return fl;
    }

    public static void main(String[] args) {
        Verif v = new Verif();
        boolean m = v.b();
        System.out.println(m);
    }
}
