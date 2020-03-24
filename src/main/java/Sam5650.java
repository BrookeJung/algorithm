import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//31/50
public class Sam5650 {
    static int[][] map;
    static int[][][] visit;
    static int ans, cnt, N;
    static int[][] bounceDir = {{1, 0, 3, 2}, {1, 3, 0, 2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 2, 3, 0}, {1, 0, 3, 2}};
    static int[] dy = {0,0,-1,1} , dx={-1,1,0,0};
    static List<Warm> warmList;
    static StartSpot start;

    public static class StartSpot {
        int x;
        int y;
        int dir;

        public StartSpot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static class Warm {
        int x;
        int y;
        int warm;

        public Warm(int x, int y, int warm) {
            this.x = x;
            this.y = y;
            this.warm = warm;
        }

    }

    private static void move(int x, int y, int dir) {
        int nx=x;
        int ny=y;
        while(true){
            nx  += dx[dir];
            ny += dy[dir];
            if (OutOfBound(nx, ny)) {//벽에부딪혔을때
                cnt++;
                dir = bounceDir[0][dir];
            }//시작점에 돌아왔거나 블랙홀
            else if ((nx == start.x && ny == start.y)) {
                return;
            }else if(map[nx][ny] == -1){
                visit[start.x][start.y][start.dir] = cnt;
                return;
            }
            //방문하려는 곳과 방향이 이미 탐색해본경우
            else if (visit[nx][ny][dir] > 0) {
                cnt += visit[nx][ny][dir];
                visit[start.x][start.y][start.dir] = cnt;
                return;
            }//웜홀인경우
            else if (map[nx][ny] >= 6 && map[nx][ny]<=10) {
//                System.out.println("???");
                for (Warm w : warmList) {
                    if ((w.warm == map[nx][ny]) && (w.x != nx || w.y != ny)) {
//                        System.out.println(w.warm+" "+w.x+"/"+w.y);
                        nx = w.x;
                        ny = w.y;
                        break;
                    }
                }
            }//블록만났을때
            else if ((map[nx][ny] > 0) && (map[nx][ny] < 6)) {
                cnt++;
                dir=bounceDir[map[nx][ny]][dir];
            }
        }

    }

    private static boolean OutOfBound(int nx, int ny) {
        return (nx >= N || nx < 0 || ny >= N || ny < 0) ? true : false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            int ans = 0;
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visit = new int[N][N][4];
            warmList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
//                        System.out.println("오타");
                        warmList.add(new Warm(i, j, map[i][j]));
                    }
                }
            }
//            System.out.println("사이즈는"+warmList.size());
//            for(Warm w:warmList){
//                System.out.print(w.warm+"은"+w.x+"/"+w.y);
//            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0) continue;
                    for (int dir = 0; dir < 4; dir++) {
                        start = new StartSpot(i, j, dir);
//                        System.out.println(start.x+"x/y"+start.y);
                        cnt = 0;
                        move(i, j, dir);
                        ans = Math.max(cnt, ans);
                    }
                }
            }
            System.out.println("#" + (num++) + " " + ans);
        }
    }
}
