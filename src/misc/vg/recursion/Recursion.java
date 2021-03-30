package misc.vg.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Valerii
public class Recursion {
    Set<String> combinations = new HashSet<>();

    boolean checkAdd(String str) {
        List<String> list = Arrays.asList(str.split("[+]"));
        if (list.size() < 2)
            return false;
        list.sort(String::compareTo);
        String res = list.stream().reduce("", (a, x) -> x+a);
        if (!combinations.contains(res)) {
            combinations.add(res);
            return true;
        }
        return false;
    }

    String recursion3(int i, int n, String str) {
        return recursion2(1, n-i,str +i+ "+");
    }

    String recursion2(int i, int n, String str) {
        checkAdd(str + n);
        if (i<=n/2){
            recursion3(i, n, str);
            recursion2(i+1, n, str);
        }
        return "";
    }

    String result(int n) {
        String res = String.valueOf(n);
        String[] arr = new String[combinations.size()];
        combinations.toArray(arr);
        Arrays.sort(arr);
        for (int j=arr.length-1; j>=0; j-- ) {
            String nums = arr[j];
            res += " = ";
            for (int i=0; i<nums.length();i++) {
                String str = nums.substring(i,i+1);
                res += str + "+";
            }
            res = res.substring(0, res.length()-1);
        }
        return res;
    }

    public static String asNumbersSum(int num) {
        Recursion rec = new Recursion();
        rec.recursion2(1,num, "");
        return rec.result(num);
    }

    public static void main(String[] args) {
        System.out.println(asNumbersSum(6));
    }
}
