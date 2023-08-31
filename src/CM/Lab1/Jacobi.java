package CM.Lab1;

public class Jacobi {
    public static double[] jacobi(double[][] A, double[] b, double[] x0, double tol, int maxIter) {
        int n = A.length;
        double[] x = new double[n];
        int iter = 0;
        while (iter < maxIter) {
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum += A[i][j] * x0[j];
                    }
                }
                x[i] = (b[i] - sum) / A[i][i];
            }
            double error = 0;
            for (int i = 0; i < n; i++) {
                error += Math.abs(x[i] - x0[i]);
            }
            if (error < tol) {
                break;
            }
            System.arraycopy(x, 0, x0, 0, n);
            iter++;
        }
        return x;
    }

}
//在上面的代码中，A是系数矩阵，b是常数向量，x0是初始解向量，tol是容差值，maxIter是最大迭代次数。函数返回最终的解向量。
