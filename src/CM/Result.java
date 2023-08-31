package CM;

import com.sun.source.tree.BreakTree;

import java.util.function.Function;

import static java.lang.Math.log;
import static java.lang.Math.sin;

public class Result {


        public static String error_message = "";
        public static boolean has_discontinuity = false;

        static double first_function(double x) {
            return 1 / x;
        }

        private static double second_function(double x) {
            return sin(x) / x;
        }

        private static double third_function(double x) {
            return x * x + 2;
        }

        private static double fourth_function(double x) {
            return  2 * x + 2;
        }

        private static double five_function(double x) {
            return log(x);
        }

        /*
         * How to use this function:
         *    Function<Double, Double> func = get_function(4);
         *    func.apply(0.0001);
         */
        private static Function<Double, Double> get_function(int n) {
            switch (n) {
                case (1):
                    return Result::first_function;
                case (2):
                    return Result::second_function;
                case (3):
                    return Result::third_function;
                case (4):
                    return Result::fourth_function;
                case (5):
                    return Result::five_function;
                default:
                    throw new UnsupportedOperationException("Function " + n + " not defined.");
            }
        }


        /*
         * Complete the 'calculate_integral' function below.
         *
         * The function is expected to return a DOUBLE.
         * The function accepts following parameters:
         *  1. DOUBLE a
         *  2. DOUBLE b
         *  3. INTEGER f
         *  4. DOUBLE epsilon
         */

        public static double calculate_integral(double a, double b, int f, double epsilon) {
            boolean isNegative=false;
            if (a > b) {
                double temp = a;
                a = b;
                b = temp;
                isNegative=true;
            }
            if ((f==5&&(a<=0||b<=0))||(f==1&&(a*b<=0))){
                error_message="Integrated function has discontinuity or does not defined in current interval";
                has_discontinuity=true;
                return 0;
            }else if (f==2&&(a*b<=0)){
                double sum;
                sum=calculate_integral(a, -Double.MIN_VALUE,2,epsilon);
                sum+=calculate_integral(Double.MIN_VALUE,b ,2,epsilon);
                return sum;
            }

            Function<Double, Double> func = get_function(f);

            double upLimit = b;
            double downLimit = a;
            double addition ;
            double sum=0;
            while (true) {
                double step=b-downLimit;
                addition = (func.apply(b) + func.apply(downLimit))*step/2;
                if (Math.abs(addition)<epsilon) {
                    sum+=addition;
                    break;
                }
                int denominator = 1;
                while (Math.abs(addition) > epsilon) {
                    denominator*=2;
                    step=(b-downLimit)/denominator;
                    upLimit = downLimit + step;
                    addition = (func.apply(downLimit) + func.apply(upLimit))*step/2;
                }
                sum+=addition;
                downLimit=upLimit;
            }
            if (isNegative) sum=-sum;
        return sum;
        }
    }




