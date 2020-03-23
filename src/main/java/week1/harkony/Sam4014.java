package week1.harkony;

import java.util.Scanner;

public class Sam4014 {
    static int map[][];
    static int N;
    static int x;
    static int arr[];
    static boolean object[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int cnt = 0;
            N = sc.nextInt();
            x = sc.nextInt();
            map = new int[N][N];

            for (int low = 0; low < N; low++) {
                for (int col = 0; col < N; col++) {
                    map[low][col] = sc.nextInt();
                }
            }

            for (int low = 0; low < N; low++) {
                object = new boolean[N];
                arr = new int[N];
                for (int k = 0; k < N; k++)
                    arr[k] = map[low][k];
                if (isPossible(arr))
                    cnt++;
            }

            for (int col = 0; col < N; col++) {
                object = new boolean[N];
                arr = new int[N];
                for (int k = 0; k < N; k++)
                    arr[k] = map[k][col];

                if (isPossible(arr))
                    cnt++;

            }
            System.out.println("#" + (i + 1) + " " + cnt);
        }

    }

    public static boolean isPossible(int arr[]) {

        for (int point = 0; point < N - 1; ) {
            int cur = arr[point];
            int next = arr[point + 1];
            if (cur == next) {
                point++;
            } else if (cur + 1 == next) {

                // boundary check
                if (point - x + 1 < 0)
                    return false;

                // vertical check
                for (int i = 0; i < x - 1; i++) {
                    if (arr[point - i] != arr[point - i - 1])
                        return false;
                }

                // object check
                for (int i = 0; i < x; i++) {
                    if (object[point - i])
                        return false;
                }

                // possible to set object
                for (int i = 0; i < x; i++) // mark object to not allow duplicate object
                    object[point - i] = true;

                point++; // move point forward

            } else if (cur - 1 == next) {

                // boundary check
                if (point + x >= N)
                    return false;

                // vertical check
                for (int i = 0; i < x - 1; i++) {
                    if (arr[point + i + 1] != arr[point + i + 2])
                        return false;
                }

                // object check is unnecessary because it check only forward direction

                // possible to set object
                for (int i = 1; i <= x; i++) // mark object to not allow duplicate object
                    object[point + i] = true;

                point += x;

            } else {
                return false;
            }
        }
        return true;
    }

    public static void print() {
        for (int i = 0; i < N; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
        for (int i = 0; i < N; i++)
            System.out.print((object[i] ? 1 : 0) + " ");
        System.out.println("");
    }
}