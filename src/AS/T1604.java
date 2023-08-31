package AS;


import java.util.*;

public class T1604 {
    static class pair{
        int num;
        int ind;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k= sc.nextInt();
        pair[] pairs=new pair[k];

        for (int i=0;i<k;i++){
            pairs[i]=new pair();
            pairs[i].num= sc.nextInt();
            pairs[i].ind=i+1;
        }

        Arrays.sort(pairs,Collections.reverseOrder(Comparator.comparingInt(a -> a.num)));

        int[][] bucket=new int[k][pairs[0].num];
        int idx=0,count=0;
        outer:for (int i=0;i<bucket.length;i++) {
            for (int j = 0; j < pairs[0].num; j++) {
                bucket[i][j] = pairs[idx].ind;
                count++;
                if (pairs[idx].num == count) {
                    count=0;
                    idx++;
                }
                if (idx == pairs.length) break outer;
            }
        }
        for (int i=0;i< pairs[0].num;i++) {
            for (int j=0;j<bucket[0].length;j++) {
                System.out.print(bucket[i][j] + " ");
            }
            System.out.println();
        }
        for (int i=0;i< pairs[0].num;i++) {
            for (int[] ints : bucket) {
                if (ints[i] == 0) continue;
                System.out.print(ints[i] + " ");
            }
        }
    }
}
