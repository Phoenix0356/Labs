package AS;

import java.util.Arrays;
import java.util.Scanner;

public class T2025 {
    public static int count(int num){
        if (num==1) return 0;
        int temp=0;
        --num;
        for (;num>0;num--)temp+=num;
        return temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ans=new int[n];
        for (int i = 0; i < n; i++){
            int people=sc.nextInt();
            int pos=count(people);

            int group= sc.nextInt();
            int[] arr=new int[group];

            Arrays.fill(arr,people/group);

            int remain=people-arr[0]*arr.length;
            if (remain>0){
                for (int j=0;j<remain;j++){
                    arr[j]++;
                }
            }

            for (int j : arr) {
                pos -= count(j);
            }
            ans[i]=pos;
        }
        for (int i=0;i< ans.length;i++){
            System.out.println(ans[i]);
        }
    }
}
