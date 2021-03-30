package misc.vg.recursion;

public class AsNumbersSum {
    private static final String eq = " = ";
    private static final String pl = "+";

    public static String asNumbersSum(int number) {
        // передаем слагаемые первой суммы
        return number + asNumbersSumR(number - 1, 1, "");
        /// 5
    }

    public static String asNumbersSumR(int n, int i, String p) {
        return (n != 0 ?
                        (i > n ?
                                // рекурсия для формирование суммы для слагаемых при n < nunber/2
                                asNumbersSumR(n, i - n, p + n + pl)
                                    /// n=2 i=1 p=2+
                                        /// n=1 i=1 p=2+1+
                                    /// n=1 i=1 p=1+1+1+

                                // формирование начала строки, условия всегда сначала приводят сюда
                                // а при n < nunber/2 это конец рекурсии при n=1 и i=1
                                : eq + p + n + pl + i
                                /// = n=4 + i=1
                                    /// = n=3 + i=2
                                        /// = p=3+ n=1 + i=1
                                    /// = p=2+ n=2 + i=1
                                    /// = p=2+1+ n=1 + i+1
                                /// = p=1+1+1+ n=1 + i=1

                                // рекурсия для разложения второго слагаемого
                                // доводит последнее слагаемое до 1
                                + asNumbersSumR(i - 1, 1, p + n + pl))
                                /// n=0
                                    /// n=1 i=1 p=3+
                                        /// n=0
                                    /// n=0
                                /// n=0
                                /// n=0

                        // рекурсия для продолжения разложения,
                        // уменьшаем первое слагаемое на 1 в прямом ходе рекурсии
                        + asNumbersSumR(n - 1, i + 1, p)
                        /// n=3 i=2
                                /// n=0
                            /// n=2 i=3
                                /// n=1 i=2 p=2+
                                    /// n=0
                                /// n=1 i=4

                // выход из рекурсии при n=0
                : "");
    }

    public static void main(String[] args) {
        System.out.println(asNumbersSum(5));
    }
}