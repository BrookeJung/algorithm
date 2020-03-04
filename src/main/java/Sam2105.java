import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sam2105 {
    static int N, ANS;
    static int[][] map, visited;
    static int[] dx = {1, 1, -1, -1}, dy = {1, -1, -1, 1}; // ↘ ↙ ↖ ↗
    static List<String> menus;
    static int turn;

    public static void search(int x, int y, int[] start, int CNT, int turn) {
        if(turn==4) { // 방향 4번 바뀌면
            return;
        }
        int nx = x + dx[turn];
        int ny = y + dy[turn];
        if (nx >= N || nx < 0 || ny >= N || ny < 0) { //범위넘어가는지
            return;
        }
        if (visited[nx][ny] == 1 || menus.indexOf(map[nx][ny]+"") >= 0) { //방문했던좌표인지 , 먹은디저트인지
                return;
        }
        if(nx==start[0]&&ny==start[1]){ //시작했던 좌표면 ANS
            ANS= (ANS<CNT)? CNT:ANS;
            return;
        }
        menus.add(map[nx][ny]+""); //먹은 디저트 종류
        visited[nx][ny] = 1;  //좌표방문
        search(nx,ny,start,CNT+1,turn); //기존 turn 방향대로 dfs
        //같은방향대로 더이상 갈곳이없으면 빠져나와서
        search(nx,ny,start,CNT+1,turn+1); // 다음방향 turn+1 으로 꺽어서 dfs
        //depth 들어간 방향에서 해당 좌표에서 시작해서 빠져나가지못했다 > 루트를 완성할수 없다. 백트랙킹해서 다시 찾을꺼니까 좌표를 다시 초기화한다.
        menus.remove(map[nx][ny]+"");
        visited[nx][ny]=0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int cnt = 1;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            turn = 0;
            ANS = 0;
            menus = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    search(i, j, new int[]{i, j}, 1,0);
                }
            }
            ANS = (ANS < 4) ? -1 : ANS;
            System.out.println("#" + (cnt++) + " " + ANS);
        }
    }

}
