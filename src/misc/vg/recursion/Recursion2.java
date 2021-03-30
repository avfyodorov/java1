package misc.vg.recursion;

public class Recursion2 {
    // Grigorii
    private static final String eqSign = " = ";
    private static final String plSign = "+";

    public static String asNumbersSumGr(int number) {
        return number + getVariants(number - 1, 1, "");
    }

    public static String getVariants(int n, int i, String p) {
        return
                (n <= 0 ? "" :
                        (i > n
                                ? getVariants(n, i - n, p + n + plSign)
                                : eqSign + p + n + plSign + i
                                + getVariants(i - 1, 1, p + n + plSign)
                        )
                                + getVariants(n - 1, i + 1, p)
                );
    }

    // Internet
    public static String asNumbersSumInt(int number) {
        return number + divideIntoTerms(number, 1, "");
    }

    public static String divideIntoTerms(int number, int secondTerm, String lastTerm) {
        if (number <= 0){
            return "";
        } else
        if (secondTerm > number)
            return divideIntoTerms(number, secondTerm - number,
                    lastTerm + number + "+") + divideIntoTerms(number - 1, secondTerm + 1, lastTerm);

        else return " = " + lastTerm + number + "+" + secondTerm +
                divideIntoTerms(secondTerm - 1, 1, lastTerm + number + "+")
                + divideIntoTerms(number - 1, secondTerm + 1, lastTerm);
    }

    // Sergei
    static String recursion(int n, String str) {
        System.out.println(str + n);
        for (int i=1; i<=n/2; i++){
            recursion(n-i, str + i + "+");
        }
        return "";
    }

    public static void main(String[] args) {
        //recursion(5, "");
        System.out.println(asNumbersSumGr(5));
        //System.out.println(asNumbersSumInt(5));
    }
}

