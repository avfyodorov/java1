package misc.vg.recursion;

import java.util.Stack;

public class HanoiTower {
    private final Stack<Integer>[] towers;
    private final int height;
    private int start;
    private boolean printOn;

    public HanoiTower(int size, int pos) {
        start = pos;
        height = size;
        printOn = false;
        towers = new Stack[3];
        for (int i=0; i<3; i++) {
            towers[i] = new Stack<>();
            if (i==pos) {
                for (int j=0; j < height; j++) {
                    towers[i].add(height - j);
                }
            }
        }
    }

    int getLast(int from, int to) {
        return 3 - (from + to);
    }

    void change(int from, int to, int last, int n) {
        if (n > 0) {
            change(from, last, to, n-1);
            Integer fromSize = towers[from].pop();
            towers[to].push(fromSize);
            if (printOn)
                print();
            change(last, to, from, n-1);
        }
    }

    public void move(int from, int to) {
        if (from != start) {
            System.out.println("Перемещение невозможно!");
            return;
        }
        change(from, to, getLast(from, to), height);
        start = to;
    }

    void print() {
        String[] printArr = new String[height];
        for (int i = 0; i < height; i++) {
            printArr[i] = "";
        }
        int h = height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < 3; j++) {
                if (towers[j].size() > h-1) {
                    printArr[i] = printArr[i] + "<00" + towers[j].get(h-1) + "> ";
                } else {
                    printArr[i] = printArr[i] + "  I   ";
                }
            }
            h--;
        }
        for (int i = 0; i < height; i++) {
            System.out.println(printArr[i]);
        }
        System.out.println("=================");
    }

    void setTrace(boolean on) {
        printOn = on;
    }

    public static void main(String[] args) {
        HanoiTower h = new HanoiTower(3, 0);
        h.print();
        h.setTrace(true);
        h.move(0, 1);
        h.setTrace(false);
        h.move(1, 2);
        h.print();
    }
}
