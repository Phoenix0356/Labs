package CM.Lab3;

import java.util.function.BiFunction;

import static java.lang.Math.sin;

class Result {

    private static double first_function(double x, double y) {
        return sin(x);
    }

    private static double second_function(double x, double y) {
        return (x * y)/2;
    }

    private static double third_function(double x, double y) {
        return y - (2 * x)/y;
    }

    private static double fourth_function(double x, double y) {
        return x + y;
    }

    private static double default_function(double x, double y) {
        return 0.0;
    }

    /*
     * How to use this function:
     *    BiFunction<Double, Double, Double> func = get_function(4);
     *    func.apply(0.0001);
     */
    private static BiFunction<Double, Double, Double> get_function(int n) {
        switch (n) {
            case (1):
                return Result::first_function;
            case (2):
                return Result::second_function;
            case (3):
                return Result::third_function;
            case (4):
                return Result::fourth_function;
            default:
                return Result::default_function;
        }
    }

    /*
     * Complete the 'solveByEuler' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. INTEGER f
     *  2. DOUBLE epsilon
     *  3. DOUBLE a
     *  4. DOUBLE y_a
     *  5. DOUBLE b
     */
    public static double solveByEuler(int f, double epsilon, double a,  double y_a, double b) {
        BiFunction<Double, Double, Double> func = get_function(f);
        double step=(b-a)/10;
        double y_pre=y_a;
        while (a<b){
            y_a=y_pre+step*func.apply(a,y_pre);
            while (y_a-y_pre>epsilon) {
                step /= 2;
                y_a = y_pre + step * func.apply(a, y_pre);
            }
            y_pre=y_a;
            a+=step;
        }
        return y_a;
        }
    }
