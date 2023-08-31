package AS;

import java.util.*;

public class T1444 {
    static class Points{
        double tan;
        double len;
        int ind;
    }

    static int compareP(Points p1,Points p2) {
        if (p1.tan>p2.tan) return 1;
        else if (p1.tan<p2.tan) return -1;

        return Double.compare(p1.len,p2.len);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        Points[] points=new Points[k-1];
        int x0=sc.nextInt();
        int y0=sc.nextInt();

        for (int i = 0; i < k-1; i++) {
            int x=sc.nextInt();
            int y=sc.nextInt();
            double tan=Math.atan2(y-y0,x-x0);
            points[i]=new Points();
            points[i].tan=tan;
            points[i].len=Math.pow(x-x0,2)+Math.pow(y-y0,2);
            points[i].ind=i+2;
        }
        Arrays.sort(points, T1444::compareP);
        int idx=0;
        for (int i=0;i<points.length-1;i++){
            if (Math.abs(points[i].tan)+Math.abs(points[i+1].tan)>Math.PI&&points[i].tan*points[i+1].tan<0){
                idx=i+1;
                break;
            }
        }

        System.out.println(k);
        System.out.println(1);

        for (int i=idx;i<points.length;i++){
            System.out.println(points[i].ind);

        }
        for (int i=0;i<idx;i++){
            System.out.println(points[i].ind);
        }
    }
}
