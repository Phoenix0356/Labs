package AS;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class T1009 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();
        long total=(long) Math.pow(k,n);
        long temp=n;
        while (--temp>0){
            if (k-temp==2)continue;
            total-=Math.pow(k-1,temp);
        }
        long ans=total-(n-1)*(long) Math.pow(k,n-2);
        System.out.println(ans);
    }
}
