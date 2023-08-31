package AS;

import java.util.Scanner;

public class T1296 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        if (n==0) {
            System.out.print(0);
            return;
        }
        int[] arr=new int[n];
        for (int i=0;i<n;i++){
            arr[i]= sc.nextInt();
        }

        int pre=0,ans=arr[0];
        for (int i=0;i<n;i++){
            pre=Math.max(pre+arr[i],arr[i]);
            ans=Math.max(ans,pre);
            System.out.println(pre);
        }
        ans=Math.max(ans, 0);
        System.out.print(ans);
    }
}
