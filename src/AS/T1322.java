package AS;

import java.util.*;

public class T1322 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt()-1;
        char[] text =sc.next().toCharArray();
        int len=text.length;
        int[][] p=new int[len][2];

        for (int i=0;i<len;i++){
            p[i][0]=i;
            p[i][1]=text[i];

        }
        Arrays.sort(p, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });



        for (int i=0;i<len;i++){
            System.out.print(text[i]+" ");
            System.out.print((char) p[i][1]+" ");
            System.out.print(i+" ");
            System.out.print(p[i][0]+" ");
            System.out.println();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length; i++) {
            n = p[n][0];
            sb.append(text[n]);
        }
        System.out.println(sb);
    }
}
