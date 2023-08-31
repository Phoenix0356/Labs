package AS;

import java.util.Scanner;

public class T1155 {
    static String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H" };
    static int[] arr = new int[8];

    static void move(int in, int out, int t) {
        while (arr[in] > 0) {
            if (arr[t] == 0) {
                arr[t]++;
                arr[out]++;
                System.out.println(vertices[t] + vertices[out] + "+");
            }
            arr[in]--;
            arr[t]--;
            System.out.println(vertices[in] + vertices[t] + "-");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 8; i++) {
            arr[i] = sc.nextInt();
        }

        if (arr[0] + arr[2] + arr[5] + arr[7] != arr[1] + arr[3] + arr[4] + arr[6]) {
            System.out.print("IMPOSSIBLE");
            return;
        }
        move(0, 2, 1);
        move(5, 2, 1);
        move(7, 2, 3);
        move(4, 6, 5);
        move(1, 6, 5);
        move(3, 6, 7);
        while (arr[2] > 0) {
            System.out.println("C" + "G" + "-");
            arr[2]--;
        }
    }
}


