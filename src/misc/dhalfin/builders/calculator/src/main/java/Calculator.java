package ru.progwards.java2.lessons.builders.calculator.src.main.java;

public class Calculator {
    private String expression;
    private int stance;

    private Calculator(String expression) {
        this.expression = expression;
    }

    private String getNext() throws Exception {
        if (hasNext()) {
            return expression.substring(stance++, stance);
        } else {
            throw new Exception("Unforeseen end of the expression");
        }
    }

    private String checkNext() {
        if (hasNext()) {
            return expression.substring(stance, stance + 1);
        } else {
            return "";
        }
    }

    private boolean hasNext() {
        return stance < expression.length();
    }

    int getNumber() throws Exception {
        String num = getNext();
        return Integer.valueOf(num);
    }

    Operation getOperation(String operation) throws Exception {
        if (operation.length() > 0)
            switch (operation.toCharArray()[0]) {
                case ')':
                    return Operation.NONE;
                case '+':
                    return Operation.ADD;
                case '-':
                    return Operation.SUB;
                case '*':
                    return Operation.MUL;
                case '/':
                    return Operation.DIV;
            }
        throw new Exception("unknown operation " + operation);
    }

    Operation getOperation() throws Exception {
        String operation = getNext();
        return getOperation(operation);
    }

    Operation checkOperation() throws Exception {
        String operation = checkNext();
        return getOperation(operation);
    }

    private int getExpr() throws Exception {
        String bracket = checkNext();
        if (bracket.equals("(")) {
            getNext();
            int expression = term();
            if (!getNext().equals(")")) {
                throw new Exception("\")\" to be expected");
            }
            return expression;
        }
        return getNumber();
    }

    private int getTerm() throws Exception {
        int outcome = getExpr();
        while (hasNext()) {
            Operation operation = checkOperation();
            if (operation == Operation.MUL || operation == Operation.DIV) {
                operation = getOperation();
                int num = getExpr();
                switch (operation) {
                    case MUL:
                        outcome *= num;
                        break;
                    case DIV:
                        outcome /= num;
                        break;
                    default:
                        throw new Exception("Error: Unallowable operation " + operation);
                }
            } else {
                return outcome;
            }
        }
        return outcome;
    }

    private int term() throws Exception {
        int outcome = getTerm();
        while (hasNext()) {
            Operation operation = checkOperation();
            if (operation == Operation.ADD || operation == Operation.SUB) {
                operation = getOperation();
                int num = getTerm();
                switch (operation) {
                    case ADD:
                        outcome += num;
                        break;
                    case SUB:
                        outcome -= num;
                        break;
                    default:
                        throw new Exception("internal error: invalid operation " + operation);
                }
            } else {
                return outcome;
            }
        }
        return outcome;
    }

    private int calculate() throws Exception {
        return term();
    }

    public static int calculate(String expr) throws Exception {
        return new Calculator(expr).calculate();
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s);
        }
        if (sb.length() < 1) {
            throw new RuntimeException("Parameters should be specified");
        }
        System.out.println(Calculator.calculate(sb.toString()));
    }
}