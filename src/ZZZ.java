public class ZZZ {
    public static void main(String[] args) {
        double z = 0;
        for (int i = 0; i < 30; i++) {
            z = z + 1_000_000;
            z = z * 0.97;
        }
        System.out.println(z);
        System.out.printf("%11.2f",z);
    }
}
