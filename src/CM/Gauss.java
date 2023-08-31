package CM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gauss {
    public static boolean isSolutionExists = true;
    public static String errorMessage = "";

    public static List<Double> solveByGauss(int n, List<List<Double>> matrix) {
        errorMessage = "The system has no roots of equations or has an infinite set of them.";
        ArrayList<Double> res=new ArrayList<>();
        for (int i=0;i<n;i++) res.add(0.0);

        for (int i = 0;i<matrix.size()-1;i++){
            for (int j=i;j<n;j++) {
                double denominator = matrix.get(j).get(i);
                for (int h = 0; h < n + 1; h++) {
                    matrix.get(j).set(h, matrix.get(j).get(h) / denominator);
                }
            }
            for (int k=i+1;k<n;k++) {
                for (int z = 0; z < n + 1; z++) {
                    matrix.get(k).set(z, matrix.get(i).get(z) - matrix.get(k).get(z));
                }
            }

        }

        for (int i=0;i<n;i++){
            if (matrix.get(i).get(i) == 0) {
                isSolutionExists = false;
                System.out.print(errorMessage);
                return null;
            }
        }

        res.set(n-1,matrix.get(n-1).get(n)/matrix.get(n-1).get(n-1));
        for (int i=n-2;i>=0;i--){
            double temp=matrix.get(i).get(n);
            for (int j=n-1;j>=i;j--){
                temp-=matrix.get(i).get(j)*res.get(j);
            }
            res.set(i,temp/matrix.get(i).get(i));
        }
        for (int i=0;i<n;i++){
            res.add(0.0);
        }
        return res;
    }
}
