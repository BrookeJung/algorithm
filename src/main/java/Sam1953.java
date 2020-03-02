import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sam1953 {

    static int N, M, V, H, T, ANS, RUN; //맵xSize , 맵ySize , 맨홀x, 맨홀y , 소요시간
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1}; //하상우좌
    static int[][] dir = {{}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {1, 2}, {0, 2}, {0, 3}, {1, 3}}; // 0~7 pipe type
    static int[][] map, visited;

    public static void bfs() {
        LinkedList<int[]> que = new LinkedList<>();
        que.offer(new int[]{V, H}); //root노드 enque
        visited[V][H] = 1; //1 방문 - 0 미방문
        ANS++;
        RUN = 1;
        while (!que.isEmpty() && RUN < T) {
            for (int i = 0; i < que.size(); i++) {
                int[] node = que.poll(); //deque- FIFO
                int x = node[0];
                int y = node[1];
                int type = map[x][y]; //배관타입추출
                for (int n = 0; n < dir[type].length; n++) {
                    int nx = x + dx[n];
                    int ny = x + dy[n];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (map[nx][ny] != 0 && visited[nx][ny] == 0) {
                            if (isConnected(x,y,nx,ny,type)) { //넘어갈부분이 연결되어있다면
                                que.offer(new int[]{nx, ny});
                                visited[nx][ny] = 1;
                                ANS++;
                                RUN++;
                            }
                        }
                    }
                }
            }
        }
    }
  /*
   넘어갈 배관이 연결되는지 확인하는부분
  */
    public static boolean isConnected(int x, int y, int nx, int ny , int type) {
        boolean bool = false;
        return bool;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int cnt = 1;
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ANS = 0;
            bfs();
            System.out.println("#"+(cnt++)+" "+ANS);
        }

    }
}
