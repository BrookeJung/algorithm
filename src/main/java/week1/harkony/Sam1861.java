package week1.harkony;

import java.util.Scanner;

public class Sam1861 {
    final static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] mat;
    static int[][] visit;
    static int[][] score;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int Case = sc.nextInt();
        for (int loop = 0; loop < Case; loop++) {
            int n = sc.nextInt();
            mat = new int[n][n];
            visit = new int[n][n];
            score = new int[n][n];

            int minRoom = 0;
            int maxScore = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Traversal(i, j, i, j, n);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (score[i][j] > maxScore || (score[i][j] == maxScore && mat[i][j] < minRoom)) {
                        minRoom = mat[i][j];
                        maxScore = score[i][j];
                    }
                }
            }

            System.out.println("#" + (loop + 1) + " " + minRoom + " " + maxScore);
        }
    }

    public static void Traversal(int low, int col, int curlow, int curcol, int n) {
        if (visit[curlow][curcol] == 0)
            score[low][col] += 1;
        else
            score[low][col] += score[curlow][curcol];
        for (int d = 0; d < 4; d++) {
            int nextlow = curlow + dir[d][0];
            int nextcol = curcol + dir[d][1];

            if (nextlow > -1 && nextlow < n && nextcol > -1 && nextcol < n
                    && mat[nextlow][nextcol] - 1 == mat[curlow][curcol])
                Traversal(low, col, nextlow, nextcol, n);
        }

    }
}