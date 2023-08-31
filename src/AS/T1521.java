package AS;

import java.util.Arrays;
import java.util.Scanner;

public class T1521 {
    static int[][] node;
    static int idx,len=1;
    static void buildTree(int start,int end,int ind){
        node[0][ind]=end-start+1;
        node[1][ind]=end-start+1;
        if (end==start) return;
        int mid=(end+start)/2;
        buildTree(start,mid,ind*2);
        buildTree(mid+1,end,ind*2+1);
    }
    static void findNode(int pos,int indNode,int index){
        if (node[0][indNode<<1]==-1&&node[0][indNode]==1){
            idx=index+pos;
            node[0][indNode+pos-1]=0;
            return;
        }
        if (pos>node[0][indNode]) {
            findNode(pos-node[0][indNode],indNode+1,node[1][indNode]+index);
        }
        else{
            findNode(pos,indNode<<1,index);
            node[0][indNode]=node[0][indNode<<1]+node[0][(indNode<<1)+1];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();
        if (n==1) {
            System.out.print(k);
            return;
        }

        int temp1=n,temp2=k;

        while (temp1>1){
            temp1=temp1 >> 1|1;
            len++;
        }
        node=new int[2][2<<(len+1)];

        Arrays.fill(node[0],-1);
        int[] ans=new int[n];
        buildTree(1,n,1);


        for (int i=0;i<n;i++){
            findNode(temp2,2,0);
            node[0][1]=node[0][2]+node[0][3];
            System.out.print(idx+" ");
            if (node[0][1]==0) break;
            temp2=(temp2-1+k)%node[0][1];
            if (temp2==0) temp2=node[0][1];
        }
    }
}
