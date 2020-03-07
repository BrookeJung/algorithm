package week1.harkony;

import java.util.Scanner;

//2018-09-23
//Ã¼°¨ ³­ÀÌµµ: Áß»ó
public class Sam2112 {
    static int D;
    static int W;
    static int K;
    static int mat[][];
    static int min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int testcase = 1; testcase <= T; testcase++) {
            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();
            mat = new int[D][W];
            min = Integer.MAX_VALUE;

            for (int low = 0; low < D; low++) {
                for (int col = 0; col < W; col++) {
                    mat[low][col] = sc.nextInt();
                }
            }

            if (check()) {
                min = 0;
            } else
                DFS(0, 0);

            StringBuilder str = new StringBuilder("#");
            str.append(testcase);
            str.append(" ");
            str.append(min);
            System.out.println(str);
        }
    }

    public static void DFS(int point, int cnt) {

        if (cnt > 0 && check()) {
            if (cnt < min)
                min = cnt;
        } else if (point < D) {
            int backup[] = new int[W];
            backup = mat[point].clone();

            DFS(point + 1, cnt);
            for (int i = 0; i < W; i++)
                mat[point][i] = 0;
            DFS(point + 1, cnt + 1);

            for (int i = 0; i < W; i++)
                mat[point][i] = 1;
            DFS(point + 1, cnt + 1);

            mat[point] = backup.clone();
        }
    }

    public static boolean check() {
        boolean result = true;
        for (int col = 0; col < W; col++) {
            boolean flag = false;
            for (int low = 0; low < D - K + 1; low++) {
                int sum = 0;
                for (int i = 0; i < K; i++) {
                    sum += mat[low + i][col];
                }
                if (sum == K || sum == 0) {
                    flag = true;
                    break;
                }

            }
            if (!flag)
                return flag;

        }
        return true;
    }
}