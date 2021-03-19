package misc.solo300m.basetypes;

import java.util.Arrays;

public class SimpleNumber{
    private boolean[] simpleNum;
    private int startNumber;
    public SimpleNumber(int n){
        simpleNum = new boolean[n+11];
        this.startNumber = n;
        Arrays.fill(simpleNum,true);
        simpAlgorthm(n+10);
    }

    private void simpAlgorthm(int n) {
        for(int i = 2; i < simpleNum.length; i++){
            if(simpleNum[i]){
                for(int j = 2; i*j < simpleNum.length; j++)
                    simpleNum[i*j] = false;
            }
        }
    }

    private boolean isSimple(int n){
        return simpleNum[n];
    }

    public int minSimle(){
        int min = 0;
        if(!isSimple(this.startNumber)){
            for(int i = startNumber; i >=0; i--){
                if(isSimple(i)){
                    min = i;
                    return min;
                }
            }
        }
        else
            return startNumber;
        return min;
    }

    public int maxSimle(){
        int max = 0;
        if(!isSimple(this.startNumber)){
            for(int i = startNumber; i < simpleNum.length; i++){
                if(isSimple(i)){
                    max = i;
                    return max;
                }
            }
        }
        else
            return startNumber;
        return max;
    }
}
class T2{
    public static void main(String[] args) {
        SimpleNumber sim = new SimpleNumber(202);
        System.out.println(sim.minSimle());
        System.out.println(sim.maxSimle());
    }
}
