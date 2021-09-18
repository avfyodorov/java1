package misc.twist328.generics;

public class ATest {
    static final int count1 = 250_000;
    static final int count2 = 250_000;

    public static void main(String[] args) {
        DynamicArray a2 = new DynamicArray(1000, 1000);
        DIntArray a1 = new DIntArray();

        long start = System.currentTimeMillis();
        for (int i = 0; i < count1; i++)
            a1.add(i);
        long middle = System.currentTimeMillis();
        for (int i = 0; i < count2; i++)
            a2.add(i);
        long stop = System.currentTimeMillis();

//            for(int i=0; i<count1; i++)
//                System.out.println(i+" "+a2.get(i)+" "+a1.get(i));
        System.out.println("DIntArray add time =" + (middle - start));
        System.out.println("DynamicArray add time =" + (stop - middle));
    }
}

