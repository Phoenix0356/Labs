package AS;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class T1207 {
    static double k=1,x1,y1;
    static double compute(double x2,double y2){
        if (x1==x2){
            k=Double.MAX_VALUE;
            return k;
        }
        return k=(y2-y1)/(x2-x1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ind=0;
        double min;
        int n = sc.nextInt();
        double[][] coordinates = new double[n][2];

        for (int i = 0; i < n; i++) {
            coordinates[i][0]=sc.nextDouble();
            coordinates[i][1]=sc.nextDouble();
        }

        min=coordinates[0][0];
        for (int i=1;i<n;i++){
            if (min>coordinates[i][0]) {
                ind=i;
                min=coordinates[i][0];
            }
            if (min==coordinates[i][0]&&coordinates[i][1]<coordinates[ind][1]){
                ind=i;
            }
        }
        x1=coordinates[ind][0];
        y1=coordinates[ind][1];
        HashMap<Double,Integer> map=new HashMap<>();
        double[] arrK=new double[n];
        for (int i=0;i<n;i++){
            if (i==ind) {
                arrK[i]=Integer.MIN_VALUE;
                continue;
            }
            map.put(compute(coordinates[i][0],coordinates[i][1]),i);
            arrK[i]=compute(coordinates[i][0],coordinates[i][1]);
        }
        Arrays.sort(arrK);
        int ans=(map.get(arrK[(n)/2]));
        ind++;
        ans++;
        System.out.println(ind+"\n"+ans);
    }
}
