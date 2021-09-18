package misc.umk27.tests.calc;

public class SimpleCalculator {

    public static void main(String[] args) {
        SimpleCalculator sim = new SimpleCalculator();
        System.out.println(sim.div(8, 5));
    }

    public int sum(int val1, int val2) throws ArithmeticException {
        long res = (long) val1 + val2;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            throw new ArithmeticException("Произошло переполнение на операции sum");
        }
        return (int) res;
    }

    public int diff(int val1, int val2) throws ArithmeticException {
        long res = (long) val1 - val2;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            throw new ArithmeticException("Произошло переполнение на операции diff");
        }
        return (int) res;
    }

    public int mult(int val1, int val2) throws ArithmeticException {
        long res = (long) val1 * val2;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            throw new ArithmeticException("Произошло переполнение на операции mult");
        }
        return (int) res;
    }

    public int div(int val1, int val2) throws NullPointerException {
        if (val2 == 0) {
            throw new NullPointerException("Деление на 0 на операции div");
        }
        return val1 / val2;

    }

}
