import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sam1861_bfs {
    static int startRoom,maxCnt,N ,cnt;
    static int[][] map,visit;
    static int V,H;
    static int[] dx={1,-1,0,0} ,dy={0,0,1,-1};
    public static void bfs(){
        LinkedList<int[]> que = new LinkedList<>();
        que.offer(new int[]{V,H});
        visit[V][H]=1;
        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0;i<size;i++){
            int[] node = que.poll();
                int x = node[0];
                int y = node[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (isOutOfBound(nx,ny)) {
                        continue;
                    }
                    if (visit[nx][ny] == 1) {
                        continue;
                    }
                    if (map[nx][ny] - 1 == map[x][y]) {
                        visit[nx][ny] = 1;
                        cnt++;
                        que.offer(new int[]{nx, ny});
                    }
                }
            }

        }
    }

    private static boolean isOutOfBound(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= N || ny >= N)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num =1;
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            map= new int[N][N];
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j]= Integer.parseInt(st.nextToken());
                }
            }
            maxCnt=1;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    visit = new int[N][N];
                    cnt=1;
                    V=i;H=j;
                    bfs();
                    if(maxCnt<cnt){
                        maxCnt=cnt;
                        startRoom=map[i][j];
                    }
                    if(maxCnt==cnt){
                        startRoom= Math.min(map[i][j],startRoom);
                    }
                }
            }

            System.out.println("#"+(num++)+" "+startRoom+" "+maxCnt);
        }
    }
}
