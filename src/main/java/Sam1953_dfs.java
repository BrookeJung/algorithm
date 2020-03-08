
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Sam1953_dfs {
    static HashMap<Integer, Integer> connect;
    static int[][] map,visit;
    static int N, M, V, H, L;
    static int ans, run;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][] pipe = {{}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {1, 2}, {0, 2}, {0, 3}, {1, 3}};

    public static void dfs(int x, int y, int run) {
        if (run == L) {
            return;
        }
        visit[x][y]=1;
        ans++;
        int type = map[x][y];
        for (int t = 0; t < pipe[type].length; t++) {
            int dir = pipe[type][t];
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (ifOutOfMap(nx, ny)) {
                continue;
            }
            if(visit[nx][ny]==1){
                continue;
            }
            if (isConnected(dir, nx, ny)) {
                visit[nx][ny]=1;
                dfs(nx, ny, run + 1);
                visit[nx][ny]=0;
            }
        }
    }

    private static boolean isConnected(int dir, int nx, int ny) {
        int inDir=connect.get(dir);
        int nextType = map[nx][ny];
        for(int d :pipe[nextType]){
            if(d==inDir){
                return true;
            }
        }
        return false;
    }

    public static boolean ifOutOfMap(int nx, int ny) {
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return true;
        } else {
            if (map[nx][ny] == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        connect = new HashMap<>();
        connect.put(1, 0);
        connect.put(0, 1);
        connect.put(2, 3);
        connect.put(3, 2);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visit = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = 0;
            dfs(V, H, 0);
            System.out.println("#" + (num++) + " " + ans);
        }
    }
}
