package ru.progwards;

import java.util.*;

/**
 * ?????: ??????? ?????????
 * ????:  21.01.2024  13:29
 */
public class Z {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> types = new ArrayList<>();
        String item = scanner.nextLine();

        while (!item.isEmpty()) {
            types.add(item);
            item = scanner.nextLine();
        }
        System.out.println(Arrays.toString(types.toArray()));

        HashMap<Integer, Z>zz=new HashMap<>();
        ArrayList<Z> zzz=new ArrayList<>(zz.values());
    }
}
