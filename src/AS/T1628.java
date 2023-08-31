package AS;

import java.util.*;

public class T1628 {
    static class Pair implements Comparable<Pair> {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair other) {
            if (this.x != other.x) {
                return Integer.compare(this.x, other.x);
            }
            return Integer.compare(this.y, other.y);
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int num = sc.nextInt();

        Pair[] points = new Pair[180000];

        for (int i = 0; i < num; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points[i] = new Pair(x, y);
        }

        for (int i = 1; i <= cols; i++) {
            points[num++] = new Pair(0, i);
            points[num++] = new Pair(rows + 1, i);
        }
        for (int i = 1; i <= rows; i++) {
            points[num++] = new Pair(i, 0);
            points[num++] = new Pair(i, cols + 1);
        }


        int result = 0;

        TreeSet<Pair> set = new TreeSet<>();

        Arrays.sort(points, 0, num);

        for (int i = 0; i + 1 < num; i++) {
            int gap = points[i + 1].y - points[i].y;
            if (points[i].x == points[i + 1].x && gap > 2) {
                result++;
            }else if (points[i].x == points[i + 1].x && gap == 2){
                set.add(new Pair(points[i].x, points[i].y + 1));
            }
        }

        Arrays.sort(points, 0, num, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                if (a.y != b.y) {
                    return Integer.compare(a.y, b.y);
                }
                return Integer.compare(a.x, b.x);
            }
        });

        for (int i = 0; i + 1 < num; i++) {
            int gap = points[i + 1].x - points[i].x;
            if (points[i].y == points[i + 1].y && gap >= 2) {
                if (gap == 2) {
                    if (set.contains(new Pair(points[i].x + 1, points[i].y))) {
                        result++;
                    }
                } else {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

}
