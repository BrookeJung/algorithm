import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sam5295 {
    //week3
    static int ans, N, row;
    static int[][] map;
    static boolean[] pick;
    static boolean[] ele1, ele2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            map = new int[3][N];
            pick = new boolean[N];
            for (int i = 0; i < 3; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = N; //제거할수있는 수 N이 최대
            dfs(0);
            System.out.println("#" + (num++) + " " + ans);
        }
    }

    private static void dfs(int cnt) {
        if (cnt == N) {
            if (isSameElement()) {
                ans = Math.min(ans, row);
                row=N;
            }
            return;
        }
        pick[cnt] = false;
        dfs(cnt + 1);
        pick[cnt] = true;
        dfs(cnt + 1);

    }

    private static boolean isSameElement() {
        ele1= new boolean[N+1];
        ele2= new boolean[N+1];
        int contain = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) {
                if(!pick[j]){
                    continue;
                }
                if (pick[j]) { //포함할 줄이다.
                    if (i == 0) { //첫번째 줄이다.
                        contain++;
                        ele1[map[i][j]]=true;
                        ele2[map[i][j]]=true;
                    } else if(i==1) {
                        if(!ele1[map[i][j]]){
                            return false;
                        }else{
                            ele1[map[i][j]]=false;
                        }
                    } else{
                        if(!ele2[map[i][j]]){
                            return false;
                        }else{
                            ele2[map[i][j]]=false;
                        }
                    }
                }
            }
        }
        row = N - contain;
        return true;
    }

}