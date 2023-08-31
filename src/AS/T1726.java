package AS;

import java.util.Arrays;
import java.util.Scanner;

public class T1726 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long[][] address=new long[2][n];
        long sum=0;
        for (int i=0;i<n;i++){
            address[0][i]=sc.nextInt();
            address[1][i]=sc.nextInt();
        }
        Arrays.sort(address[0]);
        Arrays.sort(address[1]);
        int turn=n/2;
        for (int i=0;i<turn;i++) {
            sum += 2 * (n-1- 2L *i) * (address[0][n-1-i] - address[0][i] + address[1][n-1-i] - address[1][i]);
        }
        long ans=sum/((long) (n - 1) *n);
        System.out.println(ans);
    }
}
