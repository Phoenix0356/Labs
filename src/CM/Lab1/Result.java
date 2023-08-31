package CM.Lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Result {

    public static boolean isMethodApplicable = true;
    public static String errorMessage;

    /*
     * Complete the 'solveBySimpleIterations' function below.
     *
     * The function is expected to return a DOUBLE_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_DOUBLE_ARRAY matrix
     *  3. DOUBLE epsilon
     */

    public static List<Double> solveBySimpleIterations(int n, List<List<Double>> matrix, double epsilon) {
        errorMessage = "The system has no roots of equations or has an infinite set of them.";
        int len=matrix.size();
        int rowLen=matrix.get(0).size();
        ArrayList<Double> res=new ArrayList<>();
        for (int i=0;i<len;i++) res.add(0.0);
        int[][] dom=new int[len][rowLen];
        //int[] order=new int[rowLen];
        double[] xPre=new double[len];

        for (int i=0;i<len;i++){
            boolean isFind=false;
            for (int j=0;j<rowLen;j++){
                int sum=0;
                for (int k=0;k<rowLen;k++){
                    if (k==j) continue;
                    sum+=Math.abs(matrix.get(i).get(k));
                }
                if (Math.abs(matrix.get(i).get(j))>sum){
                    isFind=true;

                    dom[i][j]=1;
                }
            }
            if (!isFind){
                isMethodApplicable = false;
                System.out.println(errorMessage);
                return null;
            }
        }

        for (int i=0;i<len;i++){
            System.out.println();
            for (int j=0;j<rowLen;j++){
                if (dom[i][j]==1){
                    if (i==j) continue;
                    Collections.swap(matrix,i,j);
                }
                System.out.print(matrix.get(i).get(j));
            }
        }
        int cnt=0;
        for (int i=0;i<len;i++){
            if (matrix.get(i).get(i)==1){
                cnt++;
            }
        }
        if (cnt<len){
            isMethodApplicable = false;
            System.out.println(errorMessage);
            return null;
        }
//        for (int num:order) {
//            if (num==0) {
//                isMethodApplicable = false;
//                System.out.println(errorMessage);
//                return null;
//            }
//        }

        for (int i=0;i<n;i++){
            for (int j=0;j<len;j++){
                double sum=0;
                for (int k=0;k<rowLen-1;k++){
                    if (k==j)continue;
                    sum+=matrix.get(j).get(k)*xPre[k];
                }
                res.set(j,(matrix.get(j).get(rowLen-1)-sum)/matrix.get(j).get(j));
            }
            int count=0;
            for (int m=0;m<len;m++){
                if (res.get(m)-xPre[m]<epsilon) count++;
            }
            if (count==len) break;
            for (int l=0;l<len;l++) xPre[l]=res.get(l);
        }
        return res;
    }

}


