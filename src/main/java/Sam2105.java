import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Sam2105 {
    static int N, ANS;
    static int[][] map, visited;
    static int[] dx={-1,1,1,-1}, dy={1,1,-1,-1}; //↗ ↘ ↙ ↖
    static List<Integer> menus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
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
        }
    }
}
