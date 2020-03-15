
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sam2115 {
    static int N, M, C;
    static int[][] map;
    static List<Spot> list;
    static int max;

    static class Spot {

        int x;
        int y;
        int earn;

        Spot(int x, int y, int earn) {
            this.x = x;
            this.y = y;
            this.earn = earn;
        }

    }

    public static void search(int x, int y, int limit, int sum, int benefit) {
        if (limit == M) {
            max = Math.max(max, benefit);
            return;
        }
        search(x, y + 1, limit + 1, sum, benefit);
        list.add(new Spot(x, y, benefit));
        if (sum + map[x][y] <= C) { //용량 이내
            sum += map[x][y];
            benefit += (int) Math.pow(map[x][y], 2);
            search(x, y + 1, limit + 1, sum, benefit);
            list.add(new Spot(x, y, benefit));
            max = 0;
        }
    }


    public static void getAns() {
         max = list.get(0).earn;

        for (int i = 0; i < list.size(); i++) {

            int sum = list.get(i).earn;
            int x = list.get(i).x;
            int y = list.get(i).y;

            for (int j = i + 1; j < list.size(); j++) {
                int xp = list.get(j).x;
                int yp = list.get(j).y;

                if (x == xp && y + M > yp)
                    continue;

                int sump = list.get(j).earn;
                int total = sum + sump;
                max = Math.max(total, max);
            }
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
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;
            list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    search(i, j, 0, 0, 0);
                }
            }
            getAns();
            System.out.println("#" + (num++) + " " + max);
        }
    }
}