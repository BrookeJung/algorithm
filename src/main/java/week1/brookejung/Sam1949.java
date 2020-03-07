package week1.brookejung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Sam1949 {
    static int[][] rand, visit;
    static int N, K, MAX_LENG; //N:땅가로세로길이 , K:깍을수있는수치 ,L :최장경로길이
    static int start; //시작높이
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    static class Peak {
        int x, y, h, leng;

        public Peak(int x, int y, int h, int leng) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.leng = leng;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")" + h + "," + leng;
        }
    }

    public static void searchPath(int x, int y, int leng, int[][] searchRand, int[][] visit) {
        visit[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (visit[nx][ny] == 1) {
                    continue;
                }
                if (searchRand[nx][ny] < searchRand[x][y]) {
                    searchPath(nx, ny, ++leng, searchRand, visit);
                }
            }
            if (MAX_LENG < leng) {
                MAX_LENG = leng;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine().trim());
        int printNum = 1;
        while (num-- > 0) {
            String[] info = br.readLine().split(" ");
            N = Integer.parseInt(info[0]);
            K = Integer.parseInt(info[1]);
            rand = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    rand[i][j] = Integer.parseInt(line[j]);
                    if (start < rand[i][j]) {
                        start = rand[i][j];
                    }
                }
            }
            ArrayList<Peak> peakArr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (rand[i][j] == start) {
                        peakArr.add(new Peak(i, j, start, 1));
                    }
                }
            }
            for (int p = 0; p < peakArr.size(); p++) {
                visit = new int[N][N];
                searchPath(peakArr.get(p).x, peakArr.get(p).y, 1, rand
                        , visit);
                for (int cut = 1; cut <= K; cut++) {
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            visit = new int[N][N];
                            int[][] cutRand = Arrays.copyOf(rand, rand.length);
                            if(peakArr.get(p).x==i&&peakArr.get(p).y==j){
                                continue;
                            }
                            cutRand[i][j] -= cut;
                            searchPath(peakArr.get(p).x, peakArr.get(p).y, 1, cutRand, visit);
                        }
                    }
                }
            }

            System.out.println("#" + (printNum++) + " " + MAX_LENG);

            MAX_LENG = 1;
            start = 1;
            peakArr.clear();

        }
    }
}
