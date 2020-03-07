package week1.harkony;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Sam1953 {
    static int N; // map의 세로크기
    static int M; // map의 가로크기
    static int R; // 맨홀 세로위치
    static int C; // 맨홀 가로위치
    static int L; // 도주 시간
    static int map[][];
    //DIR[K]: K번 구조물이 연결된 네 방향
    static int DIR[][][] = {
            { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
            { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } },
            { { -1, 0 }, { 1, 0 }, { 0, 0 }, { 0, 0 } },
            { { 0, 0 }, { 0, 0 }, { 0, -1 }, { 0, 1 } },
            { { -1, 0 }, { 0, 0 }, { 0, 0 }, { 0, 1 } },
            { { 0, 0 }, { 1, 0 }, { 0, 0 }, { 0, 1 } },
            { { 0, 0 }, { 1, 0 }, { 0, -1 }, { 0, 0 } },
            { { -1, 0 }, { 0, 0 }, { 0, -1 }, { 0, 0 } } };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int testcase = 1; testcase <= T; testcase++) {
            Queue<Integer> queue = new LinkedList<Integer>(); // low,col,len을 넣는다.
            N = sc.nextInt();
            M = sc.nextInt();
            R = sc.nextInt();
            C = sc.nextInt();
            L = sc.nextInt();
            map = new int[N][M];
            int cnt = 0;
            for (int low = 0; low < N; low++) {
                for (int col = 0; col < M; col++) {
                    map[low][col] = sc.nextInt();
                }
            }
            queue.add(R);
            queue.add(C);
            queue.add(-1);
            queue.add(1);

            while (!queue.isEmpty()) {
                int low = queue.poll();
                int col = queue.poll();
                int from = queue.poll();
                int len = queue.poll();


                int type = map[low][col];
                if(len>L || type<0)
                    continue;
                cnt++;
                map[low][col] = -1;
                int dir = 4;
                for (int i = 0; i < dir; i++) {
                    int nl = low + DIR[type][i][0];
                    int nc = col + DIR[type][i][1];
                    if (nl < 0 || nl >= N || nc < 0 || nc >= M || map[nl][nc] < 1)
                        continue;
                    int ntype = map[nl][nc];

                    // i는 현재 구조물에서의 방향을 의미한다.
                    // 0과 1 ,2와 3은 서로 반대 방향을 의미하기 때문에  ndir은 다음 구조물의 상대방향을 의미한다.
                    int ndir = i % 2 == 0 ? i + 1 : i - 1;

                    // 현재 구조물에서 다음 구조물로 가기 위해서는 다음 구조물이 현재 구조물을 향해서 열려있어야한다.
                    if (DIR[ntype][ndir][0] + DIR[ntype][ndir][1] == 0)
                        continue;

                    queue.add(nl);
                    queue.add(nc);
                    queue.add(i);
                    queue.add(len + 1);

                }
            } // queue end
            System.out.println("#" + testcase + " " + cnt);
        } // testcase loop end
    }

}