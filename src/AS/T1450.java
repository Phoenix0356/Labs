package AS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class T1450 {
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int quickRead() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
    public static void main(String[] args) throws IOException {
        int n = quickRead();
        int m = quickRead();
        int[] profits=new int[n+1];

        Arrays.fill(profits,Integer.MIN_VALUE);
        int[][] edges= new int[m][3];
        for (int i = 0; i < m; i++) {
            edges[i][0]= quickRead();
            edges[i][1]= quickRead();
            edges[i][2]= quickRead();
        }
        int s=quickRead();
        int f=quickRead();
        profits[s]=0;
        for (int i=0;i<31*n/61;i++){
            for (int j=0;j<m;j++){
                if (profits[edges[j][0]]!=Integer.MIN_VALUE){
                   profits[edges[j][1]]=Math.max(profits[edges[j][1]],edges[j][2]+profits[edges[j][0]]);
                }
            }
        }
        System.out.print(profits[f]==Integer.MIN_VALUE?"No solution":profits[f]);
    }
}
