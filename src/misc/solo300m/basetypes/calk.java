package ru.progwards.java2.lessons.basetypes;

public class calk {
    public static void main(String[] args) {
        int k = 8;
        int v = 211;
        int p = getProbirHash(k,v);
        System.out.println(k%v);
        System.out.println(p);

    }
    public static int getProbirHash(int key, int v){
        double d = 0.6180339887*(int)key;
        System.out.println(d);
        System.out.println(d-Math.floor(d));
        System.out.println(v*(d-Math.floor(d)));
        return(int)(v*(d-Math.floor(d)));
    }
}
