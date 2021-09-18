package misc.twist328.recursion;

import java.util.Arrays;

 class HanoiTower {
    /* Решить задачу Ханойской башни методом рекурсии. Ханойская башня - детская головоломка,
       представленная на картинке ниже. Задача - перенести башню с первого штырька на второй,
       используя 3-й как промежуточный. При этом запрещено класть большее кольцо на меньшее.*/


    private int[] TOPRING; // верхнее кольцо штыря (0-2)
    private int[] RINGS;  //  кольца сейчас (0..size-1)
    private int size;    // size кольца
    private boolean traceOn = true; //состояние отл. печати

    public static void main(String[] args) {
        HanoiTower tower = new HanoiTower(3, 0);
        tower.print();
        tower.move(0, 2);
        //tower.print();
    }

    void setTrace(boolean on) { // вкл. отладочной печати tower.print()
        traceOn = on;
    }

    public HanoiTower(int size, int pos) { //конструктор нициализирует башню с size кольцами (1..size). pos - номер первого штыря (0,1,2)

        TOPRING = new int[]{-1, -1, -1};
        TOPRING[pos] = 0;
        RINGS = new int[size];
        Arrays.fill(RINGS, pos);
        this.size = size;
    }

    int calcrings(int ring) {    // посчитать сколько колец над нужным кольцом

        int rate = RINGS[ring];
        int result = 0;
        int i = 0;
        while (i != ring) {
            if (RINGS[i] == rate) result++;
            i++;
        }
        return result;
    }

    void onestep(int ring, int from, int to) {          // перенести нужное кольцо на нужный штырь

        if (TOPRING[from] != ring) {
            throw new RuntimeException("невозможно перенести кольцо  " + name(ring) + " с " +
                    from + " на " + to + ": кольца нет на " + from);
        }
        if (TOPRING[to] >= 0 && TOPRING[to] <= TOPRING[from]) {
            throw new RuntimeException("невозможно перенести кольцо " + name(ring) + " с " +
                    from + " на " + to + ": на " + to + " лежит " + TOPRING[to]);
        }
        RINGS[ring] = to;
        TOPRING[to] = ring;
        int i = ring + 1;
        while (i < size && RINGS[i] != from) i++;
        TOPRING[from] = i == size ? -1 : i;
        if (traceOn) print();
    }

    public void move(int from, int to) {// переносит башню со штыря from на штырь to

        if (size == 0) {
            throw new RuntimeException("так не должно быть :(size=0)");
        }
        if (RINGS[0] != from) {
            throw new RuntimeException("Верхнее кольцо не на " + from + ", а на " + RINGS[0]);
        }
        if (from == to || from < 0 || from > 2 || to < 0 || to > 2) {
            throw new RuntimeException("Проверь введенные данные, from=" + from + ", to=" + to + ", from!=to");
        }

        enable(size - 1, from, to);
    }

    public void enable(int ring, int from, int to) {

        if (ring == 0) {
            onestep(ring, from, to);
            return;
        }
        int free = (from + 1) % 3;
        if (free == to) free = (to + 1) % 3;
        enable(ring - 1, from, free);
        onestep(ring, from, to);
        enable(ring - 1, free, to);
    }

    String name(int ring) {
        return String.format("<%03d>", ring + 1); //формат отображения колец в консоли <001>/<002>/<003>
    }

    void print() {                        // выводит текущее состояние  башни на консоль

        String[] str = new String[size];

        for (int j = 0; j < 3; j++) {
            int count = 0;
            for (int i = size - 1; i >= 0; i--)
                if (RINGS[i] == j) {
                    str[count] = (str[count] == null ? "" : str[count] + " ") + (RINGS[i] == j ? name(i) : "  I  ");
                    count++;
                }

            for (; count < size; count++)
                str[count] = (str[count] == null ? "" : str[count] + " ") + "  I  ";
        }
        for (int i = size - 1; i >= 0; i--) {
            System.out.println(str[i]);
        }

        System.out.println("=".repeat(17)); //перегородка между сериями ходов выводится на консоль
    }
}