import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sam2115 {
    //day3 벌꿀채취
    static int profit;
    static int[][] map;
    static int[] visit;
    static int N, M, C; //N*N , 꿀통 수 , 일꾼당 채취가능 꿀량
    static List<Spot> selects;

    static class Spot { //시작좌표, 꿀
        int x;
        int y;
        int sum;

        Spot(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            visit = new int[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            profit = 0;
            selects = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    getProfit(i, j);
                }
            }
            getAns();
            System.out.println("#" + (num++) + " " + profit);
        }
    }

    public static void getProfit(int x, int y) {
        int sum = 0;
        int earn = 0;

        for (int i = 0; i < M; i++) {
            sum += map[x][y + i];
            earn += (int) Math.pow(map[x][y + i], 2);
        }
        if (sum > C) {
            visit = new int[M];
            for (int i = 1; i < M; i++) {
                dfs(x, y, 0, i, 0, 0, 0);
                selects.add(new Spot(x, y, profit));
                profit = 0;
            }
        } else {
            selects.add(new Spot(x, y, earn));
        }
    }

    public static void dfs(int x, int y, int cnt, int num, int sum, int earn, int start) {
        if (cnt == num) {
            profit = Math.max(profit, earn);
            return;
        }
        for (int i = start; i < M; i++) {
            if (visit[i] == 1) {
                continue;
            }
            if (sum + map[x][y + 1] > C) {
                continue;
            }
            visit[i] = 1;
            dfs(x, y, cnt + 1, num, sum + map[x][y + 1], earn + (int) Math.pow(map[x][y + 1], 2), start + 1);
            visit[i] = 0;
        }
    }

    public static void getAns() {
        profit = selects.get(0).sum;
        for (int i = 0; i < selects.size(); i++) {
            int proA = selects.get(i).sum;
            int x = selects.get(i).x;
            int y = selects.get(i).y;

            for (int j = i + 1; j < selects.size(); j++) {
                int xp = selects.get(j).x;
                int yp = selects.get(j).y;
                if (x == xp && y + M > yp) {
                    continue;
                }
                int proB = selects.get(j).sum;
                int sum = proA + proB;
                profit = Math.max(sum, profit);
            }
        }
    }
}
