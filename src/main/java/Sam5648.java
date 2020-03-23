import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
2
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9
 */
public class Sam5648 {
    static int N, ans;
    static int map[][];
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static List<Spot> list;

    static class Spot {
        int x;
        int y;
        int dir;
        int k;

        public Spot(int x, int y, int dir, int k) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.k = k;
        }

    }
    private static void move() {
        while (list.size() > 0) {
            for (Iterator<Spot> it = list.iterator(); it.hasNext(); ) {
                Spot s = it.next();
                int x = s.x;
                int y = s.y;
                int d = s.dir;
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isNoNeedCheck(nx, ny)) {//주어진 원자끼리는 그들이 차지하던 범위만큼을 이동하고 난뒤엔 더이상 만날 수 없다.
                    map[x][y]=0;
                    it.remove();
                    continue;
                }
                map[x][y] = 0;
                map[nx][ny]++;
                s.x = nx;
                s.y = ny;
            }
            for (Iterator<Spot> it = list.iterator(); it.hasNext(); ) {
                Spot s = it.next();
                if (map[s.x][s.y] >= 2) {
                    ans+=s.k;
                    it.remove();
                }
            }
            map = new int[4004][4004];
            for (Iterator<Spot> it = list.iterator(); it.hasNext(); ) {
                Spot s = it.next();
                map[s.x][s.y]=1;
            }

            }
    }

    private static boolean isNoNeedCheck(int nx, int ny) {
        return (nx < 0 || nx >= 4004 || ny < 0 || ny >= 4004) ? true : false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            map = new int[4004][4004];
            list = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + 1001) * 2; //1)0번째부터시작하는 인덱스로 받기위해 +1001 2)교차하며 마주치는경우를 알아내기 위해 *2
                int y = (Integer.parseInt(st.nextToken()) + 1001) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                list.add(new Spot(x, y, dir, k));
                map[x][y] = 1;
            }

            ans = 0;
            move();
            System.out.println("#" + (num++) + " " + ans);
        }
    }


}
