package misc.massimilian.tests.calc;

public class SimpleCalculator {
    enum action {SUM, DIFF, MULT, DIV}

    ;

    public int sum(int val1, int val2) {
        check(action.SUM, val1, val2);
        return val1 + val2;
    }

    public int diff(int val1, int val2) {
        check(action.DIFF, val1, val2);
        return val1 - val2;
    }

    public int mult(int val1, int val2) {
        check(action.MULT, val1, val2);
        return val1 * val2;
    }

    public int div(int val1, int val2) {
        check(action.DIV, val1, val2);
        return val1 / val2;
    }

    private void check(action act, int one, int two) {
        switch (act) {
            case SUM:
                if ((long) one + two > Integer.MAX_VALUE || (long) one + two < Integer.MIN_VALUE) {
                    throw new ArithmeticException("Impossible sum.");
                }
                break;
            case DIFF:
                if ((long) one - two > Integer.MAX_VALUE || (long) one - two < Integer.MIN_VALUE) {
                    throw new ArithmeticException("Impossible diff");
                }
                break;
            case MULT:
                if ((long) one * two > Integer.MAX_VALUE || (long) one * two < Integer.MIN_VALUE) {
                    throw new ArithmeticException("Impossible mult");
                }
                break;
            case DIV:
                if (two == 0) {
                    throw new ArithmeticException("Cannot div in 0");
                }
        }
    }
}
