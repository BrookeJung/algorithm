package week1.harkony;

import java.util.ArrayList;
import java.util.Scanner;

public class Sam2115 {
    static int N;
    static int M;
    static int K;
    static int mat[][];
    static int MAX;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testcase = 1; testcase <= T; testcase++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();
            mat = new int[N][N];
            MAX = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    mat[i][j] = sc.nextInt();
                }
            }
            ArrayList<Integer> test = new ArrayList<Integer>();
            DFS(0, 0, 0, 0, test);
            System.out.println("#" + testcase + " " + MAX);
        }

    }

    static void DFS(int low, int col, int cnt, int benefit, ArrayList<Integer> route) {
        if (low >= N)
            return;
        ArrayList<Integer> new_route = new ArrayList<Integer>(route);
        new_route.add(low);
        new_route.add(col);

        if (cnt < 2) {
            // 선택가능
            if (col + M - 1 < N) {
                int new_benefit=benefit+benefit(low,col);
                if (new_benefit > MAX)
                    MAX = new_benefit;

                DFS((low + (col + M) / N), ((col + M) % N), cnt + 1, benefit + benefit(low, col), new_route);
            }

            // 선택하지 않음
            DFS(low + (col + 1) / N, (col + 1) % N, cnt, benefit, new_route);
        }
    }

    //low,col에서 M개의 꿀통을 선택하여 얻을 수 있는 최대의 이익을 리턴한다.
    static int benefit(int low, int col) {

        //M개의 꿀통중에 선택 할 수 있는 경우의 수는 2의 M승이다.
        int num = 1 << M;
        int max = 0;

        //M개의 꿀통 중에 모든 선택에 대해서
        for (int i = 0; i < num; i++) {
            int bit = i;
            int sum = 0;
            int benefit = 0;
            for (int j = 0; j < M; j++, bit >>= 1) {
                //선택된 꿀통의 합과 가격 각각 구한다.
                if ((bit & 1) == 1) {
                    sum += mat[low][col + j];
                    benefit += (mat[low][col + j] * mat[low][col + j]);
                }
            }
            //선택된 꿀의 양은 K값을 넘지지 않고 이윤이 최대라면 갱신한다
            if (sum <= K && benefit >= max)
                max = benefit;
        }
        return max;
    }
}