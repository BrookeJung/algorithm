import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sam1949 {
    static int[][] rand, visited;
    static int N, K, MAX_LENG; //N:땅가로세로길이 , K:깍을수있는수치 ,L :최장경로길이
    static int highest; //시작높이
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void search(int x, int y, int leng, int cut) {
        MAX_LENG = (MAX_LENG < leng) ? leng : MAX_LENG;
        for (int dir = 0; dir < 4; dir++) {
            int nx= x+dx[dir];
            int ny= y+dy[dir];
            if(nx<0||ny<0||nx>=N||ny>=N){
                continue;
            }
            if(visited[nx][ny]==1){
                continue;
            }
            if(rand[x][y]<=rand[nx][ny]){
                if(cut==0){
                    for(int dep=1;dep<=K;dep++){
                        int backup = rand[nx][ny];
                        int cutter = rand[nx][ny]-dep;
                        if(cutter<rand[x][y]){
                            visited[nx][ny]=1;
                            rand[nx][ny]=cutter;
                            search(nx,ny,leng+1,cut+1);
                            rand[nx][ny]=backup;
                            visited[nx][ny]=0;
                        }
                    }
                }
            }else{
                visited[nx][ny]=1;
                search(nx,ny,leng+1,cut);
                visited[nx][ny]=0;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine().trim());
        int printNum = 1;
        while (num-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            rand = new int[N][N];
            visited = new int[N][N];
            MAX_LENG = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    rand[i][j] = Integer.parseInt(st.nextToken());
                    highest = (highest < rand[i][j]) ? rand[i][j] : highest;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (rand[i][j] == highest && visited[i][j] == 0) {
                        visited[i][j] = 1;
                        search(i, j, 1, 0);
                        visited[i][j] = 0;
                    }
                }
            }
            System.out.println("#" + (printNum++) + " " + MAX_LENG);
        }
    }

}
