package AS;

import java.util.Scanner;

public class T1005 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr=new int[n];
        for (int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

        int ans=0,sum=0;
        for (int i=0;i<n;i++){
            sum+=arr[i];
        }
//        int[][] dp=new int[n+1][sum/2+1];

//        for (int i=0;i<n;i++)dp[i][0]=0;
//        for (int i=0;i<=sum/2;i++)dp[0][i]=0;

//        for (int i=1;i<n+1;i++){
//            System.out.println();
//            for (int j=1;j<=sum/2;j++){
//                if (arr[i-1]>j) {
//                   dp[i][j]=dp[i-1][j];
//                }
//                else dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-arr[i-1]]+arr[i-1]);
//                System.out.print(dp[i][j]+" ");
//            }
//
//        }
        int[] dp=new int[sum/2+1];
        for (int i=0;i<n;i++){
            System.out.println();
            for (int j=sum/2;j>=arr[i];j--){
                dp[j] = Math.max(dp[j], dp[j - arr[i]] + arr[i]);
                System.out.print(dp[j]+" ");
            }
            for (int k=arr[i]-1;k>=0;k--){
                System.out.print(dp[k]+" ");
            }
        }
        ans=2*(sum/2-dp[sum/2]);
        ans+=sum%2>0?1:0;
        //System.out.print(ans);
    }
}
