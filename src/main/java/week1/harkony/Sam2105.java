package week1.harkony;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//2018-10-12

public class Sam2105 {
    static int N;
    static int MAX;
    static int lowStart;
    static int colStart;
    static int mat[][];

    // ↗ ↘ ↙ ↖ 순서
    static int DIR[][] = { { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testcase = 1; testcase <= T; testcase++) {
            N = sc.nextInt();
            mat = new int[N][N];
            for (int low = 0; low < N; low++) {
                for (int col = 0; col < N; col++) {
                    mat[low][col] = sc.nextInt();
                }
            }
            MAX = 0;

            for (int low = 0; low < N; low++) {
                for (int col = 0; col < N; col++) {
                    lowStart=low;
                    colStart=col;
                    DFS(low, col, 1, 0,new HashMap(), 0,true);
                }
            }
            if (MAX ==0)
                MAX=-1;
            System.out.println("#" + testcase + " " + MAX);
        }
    }

    public static void DFS(int low, int col, int curDir, int nCurve, Map<Integer, Boolean> hash,
                           int cnt,boolean isFirst) {

        // 종료 조건
        if (lowStart == low && colStart == col && nCurve == 3) {
            if (cnt > MAX)
                MAX = cnt;
            return;
        }

        //해당 지역을 들린적이 있거나 해당 디저트를 먹은 적이 있다면
        if (hash.get(low * 100000 + col*1000) != null || hash.get(mat[low][col])!=null){
            // System.out.println("먹어봤찌롱!");
            return;
        } else {
            // 먹어본적도 들려본적도 없다면
            hash.put(low * 100000 + col*1000, true);
            hash.put(mat[low][col], true);

            // 다음 루트 탐색.
            // 1) 진행방향 그대로
            int nl = low + DIR[curDir][0];
            int nc = col + DIR[curDir][1];
            if (isValid(nl, nc)) {
                DFS( nl, nc, curDir, nCurve, new HashMap(hash), cnt + 1,false);
            }
            if (!isFirst) {
                // 2) 오른쪽으로 90 turn
                int nDir = (curDir + 1) % 4;
                nl = low + DIR[nDir][0];
                nc = col + DIR[nDir][1];
                if (isValid(nl, nc) && nCurve < 3) {
                    DFS(nl, nc, nDir, nCurve + 1, new HashMap(hash), cnt + 1,false);
                }
            }

        }

    }

    public static boolean isValid(int low, int col) {
        if (low < 0 || col < 0 || low >= N || col >= N)
            return false;
        return true;
    }

}