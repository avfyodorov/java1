package examples;

public class Arrays
{
  //  a.
  int a1[] = {11, 22, 33};
  int item = a1[1];

  //b.
  int[] a3 = new int[10];

  //c.
  int a44[];

  //d.-------------
//  int a4[] = new int[3];
//  int item4 = a4[1][1];

  //e.-----------
//  int []a5 = new int(10);

  //f.
  int[] a6 = {10, 26, 12, 35};

  //g.------------
//  int a7[] = (11, 22, 33);
//  int item7 = a7[1];

  //h.
  int ha1[], ha2[], ha3[];
  int ha[][] = {ha1, ha2, ha3};

  //i.
//  int a9[][];
//  int item9 = a9[1][2];

  //j.--------------
  //int ax[10];

  //k
  int az[][][];

  public int sumArrayItems(int[] a)
  {
    int sum = 0;
    for (int v : a)
      sum = sum + v;
    return sum;
  }

  public int arrayMax(int[] a)
  {
    if (a.length==0) return 0;

    int[] a2 = java.util.Arrays.copyOf(a, a.length);
    java.util.Arrays.sort(a2);

    return a2[a2.length-1];
  }

  public static void main(String[] args)
  {
    Arrays a = new Arrays();
    System.out.println(a.sumArrayItems(a.a6));

//test 6.3
//    int[] a1 = {1, 1, 1, 1, 3};
//    int[] a2 = new int[5];
//    java.util.Arrays.fill(a2, 1);
//    a2[4] = 3;
//    System.out.println(java.util.Arrays.equals(a1, a2));

//    int[] a1 = {12, 5, 0, 58, 36};
//    int[] a2 = java.util.Arrays.copyOf(a1, a1.length);
//    java.util.Arrays.sort(a2);
//    System.out.println(java.util.Arrays.equals(a1, a2));

    int[] a1 = {12, 5, 0, 58, 36};
    int[] a2 = java.util.Arrays.copyOf(a1, a1.length);
    a2[2] = 0;
    System.out.println(java.util.Arrays.equals(a1, a2));

    System.out.println(a.arrayMax(a1));


  }


}
