package AS;


import java.util.Arrays;
import java.util.Scanner;

public class T1080 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        if (n==0){
            System.out.print(-1);
            return;
        }
        int[][] color=new int[2][n];
        Arrays.fill(color[0],-1);
        color[0][0]=0;
        int[][] countries=new int[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                int num=sc.nextInt();
                if (num==0) break;
                countries[i][j]=num;
            }
        }

        for (int i=0;i<n;i++){
            int ind=0;
            for (int j=0;j<n;j++) {
                if (countries[i][j]==0) break;
                for (int k=0;k<n;k++) {
                    if (countries[countries[i][j] - 1][k]==0){
                        ind=k;
                        break;
                   }
                }
                countries[countries[i][j] - 1][ind]=i+1;

            }

        }
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                System.out.print(countries[i][j]+" ");

            }
            System.out.println();
        }
        int count=0;
        while (count<n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (countries[i][j] == 0) break;
                    if (color[0][i] == 1) {
                        if (color[0][countries[i][j] - 1] == 0) continue;
                        else if (color[0][countries[i][j] - 1]==1){
                            System.out.print(-1);
                            return;
                        }
                        color[0][countries[i][j] - 1] = 0;
                    } else if (color[0][i] == 0) {
                        if (color[0][countries[i][j] - 1] == 1) continue;
                        else if (color[0][countries[i][j] - 1]==0){
                            System.out.print(-1);
                            return;
                        }
                        color[0][countries[i][j] - 1] = 1;
                    }
                }
            }
            count++;
        }
        for (int i=0;i<n;i++){
            System.out.print(color[0][i]);
        }
    }
}
