import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
            for (int i = 0; i < 3; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans=N+1;
            for (int del = 1; del < N; del++) {
                row=del;
                pick = new boolean[N] ;
                Arrays.fill(pick,true);
                combi(0, del);
                if(ans<=N){
                    break;
                }
            }
            System.out.println("#" + (num++) + " " + ans);
        }
    }

    private static void combi(int start, int r) {

        if (r == 0) {
            if (isSameElement()) { //빼고나서 same element 통과하면
                ans =row;
            }
            return;
        } else {
            for (int i = start; i < N; i++) {
                pick[i] = false;
                combi(i + 1, r - 1);
                pick[i] = true;
            }
        }
    }

    private static boolean isSameElement() {
        ele1 = new boolean[N+1];
        ele2 = new boolean[N+1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) {
                if (!pick[j]) {
                    continue;
                }
                //포함할 줄이다.
                    if (i == 0) { //첫번째 줄이다.
                        ele1[map[i][j]] = true;
                        ele2[map[i][j]] = true;
                    } else if (i == 1) {
                        if (!ele1[map[i][j]]) {
                            return false;
                        } else {
                            ele1[map[i][j]] = false;
                        }
                    } else {
                        if (!ele2[map[i][j]]) {
                            return false;
                        } else {
                            ele2[map[i][j]] = false;
                        }
                    }
                }
            }
        return true;
    }

}