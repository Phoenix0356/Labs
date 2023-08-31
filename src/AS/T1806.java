package AS;

import java.io.*;
import java.util.*;

public class T1806 {

    public String swap(String st,int i,int j){
        char[] arr=st.toCharArray();
        char[] c;
        c=Arrays.copyOf(arr,10);
        char ch=c[i];
        c[i]=c[j];
        c[j]=ch;
        return new String(c);
    }
    public String change(String st,int i,int j){
        char[] arr=st.toCharArray();
        char[] c;
        c=Arrays.copyOf(arr,10);
        c[i]=(char) (j + '0');
        return new String(c);
    }
    void Dijkstra() throws IOException {
        int num= nextInt();
        int[] time=new int[10];
        int maxDist=1000000001;
        int[] timeDist=new int[num];
        int[] pre=new int[num];
        Arrays.fill(pre,-1);
        String[] fighter=new String[num];
        HashMap<String, Integer> map = new HashMap<>();
        for (int i=0;i<10;i++) time[i]=nextInt();
        for (int i=0;i<num;i++) {
            fighter[i]= next();
            map.put(fighter[i],i);
        }

        PriorityQueue<int[]> pq=new PriorityQueue<>((o1, o2) -> {
            if (o1[1]==o2[1]) return o1[0]-o2[0];
            return o1[1]-o2[1];
        });
        Arrays.fill(timeDist,maxDist);
        timeDist[0]=0;

        pq.offer(new int[]{0,0});

        while (!pq.isEmpty()) {
            int[] vertex = pq.poll();
            while (!pq.isEmpty()&&timeDist[vertex[0]]<vertex[1]) vertex=pq.poll();
            if (vertex[0]==num-1) break;
            for (int k = 0; k < 10; k++) {
                for (int j = k + 1; j < 10; j++) {
                    if (map.containsKey(swap(fighter[vertex[0]],k,j))){
                        int idx = map.get(swap(fighter[vertex[0]],k,j));
                        if (idx==vertex[0]) continue;
                        int distance = timeDist[vertex[0]] + time[k];
                        if (timeDist[idx] > distance) {
                            pre[idx] = vertex[0];
                            timeDist[idx]= distance;
                            pq.offer(new int[]{idx, distance});
                        }
                    }
                }
            }
            for (int m = 0; m < 10; m++) {
                for (int j = 0; j < 10; j++) {
                    if (map.containsKey(change(fighter[vertex[0]],m,j))){
                        int idx = map.get(change(fighter[vertex[0]],m,j ));
                        if (idx==vertex[0]) continue;
                        int distance = timeDist[vertex[0]] + time[m];
                        if (timeDist[idx] > distance) {
                            pre[idx] = vertex[0];
                            timeDist[idx]= distance;
                            pq.offer(new int[]{idx, distance});
                        }
                    }
                }
            }

        }

        if (timeDist[num-1]==maxDist){
            System.out.print(-1);
            return;
        }
        System.out.println(timeDist[num-1]);
        Stack<Integer> res = new Stack<>();
        int p = num-1;
        while (p>=0) {
            res.push(p);
            p = pre[p];
        }
        System.out.println(res.size());
        while (!res.isEmpty()) System.out.print((res.pop()+1)+" ");
    }
    BufferedReader br;
    StringTokenizer st;

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            Dijkstra();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = br.readLine();
            if (s == null)
                return null;
            st = new StringTokenizer(s,",.\t ");
        }
        return st.nextToken();
    }
    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    public static void main(String[] args) throws IOException {
        new T1806().run();
    }
}