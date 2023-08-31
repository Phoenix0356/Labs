package AS;


import java.util.Scanner;

public class T1162 {
    static double exchange(double numOfSource,double commission,double rate){
        return (numOfSource- commission)*rate;
    }
    static class Point {
        int sourceCurrency;
        int targetCurrency;
        double rate;
        double commission;
        Point(int s,int t,double r,double c){
            this.sourceCurrency=s;
            this.targetCurrency=t;
            this.rate=r;
            this.commission=c;
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int numOfCur= sc.nextInt();
        int numOfPoints= sc.nextInt();
        int numOfMoney= sc.nextInt();
        double sumOfMoney= sc.nextDouble();
        Point[] points=new Point[2*numOfPoints];

        //  integer A and B - numbers of currencies it exchanges, and real RAB, CAB, RBA and CBA
        int ind=0;
        for (int i=0;i<numOfPoints;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            double rateA2B=sc.nextDouble();
            double commissionA2B=sc.nextDouble();
            double rateB2A=sc.nextDouble();
            double commissionB2A=sc.nextDouble();
            points[ind++]=new Point(a,b,rateA2B,commissionA2B);
            points[ind++]=new Point(b,a,rateB2A,commissionB2A);
        }

        double[] currency=new double[numOfCur+1];
        currency[numOfMoney]=sumOfMoney;

        for (int i=0;i<numOfCur;i++){
            for (Point p:points){
                if (currency[p.sourceCurrency]>0){
                    currency[p.targetCurrency]=Math.max(currency[p.targetCurrency],
                            exchange(currency[p.sourceCurrency],p.commission,p.rate));
                }
            }
        }
        for (Point p:points){
            if (currency[p.sourceCurrency]>0){
                if (exchange(currency[p.sourceCurrency],p.commission,p.rate)>currency[p.targetCurrency]){
                    System.out.print("YES");
                    return;
                }
            }
        }
        System.out.print("NO");
    }
}
