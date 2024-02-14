package examples;

/**
 * Автор: Фёдоров Александр
 * Дата:  27.12.2023  12:06
 */
public class EnumValue {
    public Status getSt(String str) {
        //      st.
        return st;
    }

    Status st;

    public static void main(String[] args) {
        EnumValue ev = new EnumValue();
        ev.st = Status.DONE;
        System.out.println(ev.st);
        System.out.println(ev.st.toString());
        System.out.println(ev.st.name());
        System.out.println(ev.st.ordinal());
        System.out.println(Status.valueOf("NEW"));
    }
}

enum Status {
    NEW,
    INPROGRESS,
    DONE
}