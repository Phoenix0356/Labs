package AS;

import java.util.Scanner;

public class T1494 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] order = new int[n];
        for (int i=0;i<n;i++){
            order[i]=sc.nextInt();
        }

        int[] stack=new int[n];
        int num=1;
        int ind=order[0]-1;
        for (int i=0;i<order[0];i++){
           stack[i]=num++;
        }
        num--;

        for (int i=0;i<n;){
            if (stack[ind]==order[i]){
                stack[ind]=0;
                ind--;
                if (ind<0) ind=0;
                i++;

            } else if (stack[ind]<order[i]){
                stack[++ind]=++num;
            } else {
                System.out.print("Cheater");
                return;
            }
        }

        System.out.print("Not a proof");
    }
}
