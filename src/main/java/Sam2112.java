import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sam2112 {
    //day3 보호필름
    static int T, N, M, K;
    static int[][] film;
    static int ANS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int cnt = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[N][M];
            ANS = K; //약품은 K번 들어가면 무조건 만족. 최소주입수를 K로 초기화
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if (test()) {
                ANS = 0;
            } else {
                search(0, 0);
            }
            System.out.println("#" + (cnt++) + " " + ANS);
        }
    }

    public static void search(int idx, int cnt) {
        if (cnt > ANS) { //약품처리 횟수가 저장된 ANS 수보다 커지면 return
            return;
        }
        if (test()) {  // cnt가 ANS 보다 작은데 테스트 통과시 ANS 갱신후 return
            ANS = cnt;
            return;
        }
        if (idx < N) { // idx 현재 탐색한 인덱스가 가로 film 길이 벗어나면 return
/*
    idx[5] 0~   > idx[5] 1~  > idx[5]복구 idx4
    idx[4] 0~   > idx[5] 0~  > idx[5] 1~  idx[5]복구 idx4
    idx[4] 1~   > idx[5[ 0~  > idx[5[ 1~  idx[5] 복구 idx[4]복구 idx3
    idx[3] 0~
     ... 54~60
 */
            search(idx + 1, cnt); //
            int[] backtrack = film[idx].clone();
            inject(0, idx); //물약 a
            search(idx + 1, cnt + 1);
            inject(1, idx); //물약 b
            search(idx + 1, cnt + 1);
            film[idx] = backtrack.clone();
        }
    }

    public static void inject(int ab, int idx) {
        for (int i = 0; i < M; i++) {
            film[idx][i] = ab;
        }
    }

    public static boolean test() {
        Boolean bool = false;
        for (int i = 0; i < M; i++) {
            int same = 1;
            bool = false;
            for (int j = 0; j < N - 1; j++) {
                same = (film[j][i] == film[j + 1][i]) ? same + 1 : 1;
                if (same == K) {
                    bool = true;
                    break;
                }
            }
            if (!bool) {
                return false;
            }
        }
        return true;
    }
}
