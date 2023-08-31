package AS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class T1160 {
    static boolean[] isConnected;
    static int[][] pair;
    static ArrayList<ArrayList<int[]>> neighbors;
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int quickRead() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
    static boolean isAllConnected(){
        for (boolean b:isConnected){
           if (!b) return false;
        }
        return true;
    }
    static boolean removeRedundant(int ind){
        while (!neighbors.get(ind).isEmpty()
                && isConnected[neighbors.get(ind).get(0)[0]]
                && isConnected[neighbors.get(ind).get(0)[1]]) {
            neighbors.get(ind).remove(0);
        }
        return neighbors.get(ind).isEmpty();
    }
    static void addPoint(int ind,int isReversed,int[] neighbor){
        if (isReversed==0) {
            pair[ind][0] = neighbor[0]+1;
            pair[ind][1] = neighbor[1]+1;
        }else if (isReversed==1) {
            pair[ind][0] = neighbor[1]+1;
            pair[ind][1] = neighbor[0]+1;
        }
    }


    public static void main(String[] args) throws IOException {
        int n = quickRead();
        int m = quickRead();
        int[][] connections = new int[m][3];
        pair = new int[n - 1][3];
        isConnected = new boolean[n];
        for (int i = 0; i < m; i++) {
            connections[i][0] = quickRead();
            connections[i][1] = quickRead();
            connections[i][2] = quickRead();
        }
        isConnected[connections[0][0] - 1] = true;
        neighbors= new ArrayList<>();

        int maxOfAll=-1,ind=0;

        //find points with all their neighbors and sort them with distance respectively.
        for (int i=0;i<n;i++){
            ArrayList<int[]> list=new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int cur=connections[j][0]-1;
                int next=connections[j][1]-1;
                int distance=connections[j][2];
                //if the pair is reversed, then I mark int[3] as 1, otherwise, 0.
                if (cur==i) list.add(new int[]{cur,next,distance,0});
                if (next==i) list.add((new int[]{next,cur,distance,1}));

            }
            neighbors.add(list);
        }
        for (ArrayList<int[]> l:neighbors) {
            l.sort((o1, o2) -> {
                if (o1[2] == o2[2]) return o1[1] - o2[1];
                return o1[2] - o2[2];
            });
        }

        //the prime algorithm
        for (int k=0;k<n;k++){
            int dis=Integer.MAX_VALUE;
            int indOfMin=0,rowOfMin=0;
            for (int l=0;l<n;l++){
                if (!isConnected[l]) continue;//check if the point is in the set
                if (removeRedundant(l))continue;
                int[] neighbor=neighbors.get(l).get(0);

                if (isConnected[neighbor[1]]) continue;//check if it will generate a ring
                if (dis > neighbor[2]) {
                    addPoint(ind,neighbor[3],neighbor);
                    dis = neighbor[2];
                    rowOfMin = neighbor[0];
                    indOfMin = neighbor[1];
                }
            }
            if (isAllConnected()) break;
            neighbors.get(rowOfMin).remove(0);//remove the selected point's pair
            maxOfAll = Math.max(maxOfAll, dis);
            isConnected[indOfMin] = true;//add the selected point  into the set
            ind++;
        }

        System.out.println(maxOfAll);
        System.out.println(n-1);
        for (int[] p : pair) System.out.println(p[0] + " " + p[1]);

    }
}
