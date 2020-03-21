import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sam2117 {
    //week2
    static int N, M, ans; //N*N 맵 , M:지불가능금액 , ans : 집 갯수
    static int[][] map;

    public static void search(int x, int y, int cnt, int k) {

        for (int nx = 0; nx < N; nx++) {
            for (int ny = 0; ny < N; ny++) {
                if (isRhombus(x, y, nx, ny, k) && map[nx][ny] == 1) {
                    cnt++;
                }
            }
        }
        if (!isLoss(k, cnt)) {
            ans = Math.max(ans, cnt);
        }
    }

    private static boolean isLoss(int k, int cnt) {
        int pay = (int) (Math.pow(k, 2) + Math.pow(k - 1, 2));
        int payOff = cnt * M;
        if (payOff - pay < 0) {
            return true;
        }
        return false;
    }

    private static boolean isRhombus(int x, int y, int nx, int ny, int k) {
        if (Math.abs(x - nx) + Math.abs(y - ny) <= k-1 ) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = 0;
            for (int k = 1; k <= N + 1; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        search(i, j, 0, k);
                    }
                }
            }
            System.out.println("#" + (num++) + " " + ans);
        }
    }
}
