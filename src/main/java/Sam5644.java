import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sam5644 {

    static int[][] map = new int[11][11];
    static int[] dx = {0, -1, 0, 1, 0}, dy = {0, 0, 1, 0, -1}; //상우하좌
    static int M, A, ans; //M:이동시간 , A:충전기갯수
    static Battery[] arr;
    static int[][] user;

    private static class Battery implements Comparable<Battery> {
        int x;
        int y;
        int c;
        int p;

        public Battery(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }

        @Override
        public int compareTo(Battery o) {
            return o.p-p;
        }
    }

    private static void move(int x1, int y1, int x2, int y2) {
        ans= getMax(x1,y1,x2,y2);

        for (int m = 0; m < M; m++) {
            int nx1 = x1 + dx[user[0][m]];
            int ny1 = y1 + dy[user[0][m]];
            int nx2 = x2 + dx[user[1][m]];
            int ny2 = y2 + dy[user[1][m]];
            ans+=getMax(nx1,ny1,nx2,ny2);
        }

    }

    private static int getMax(int nx1, int ny1, int nx2, int ny2) {
        Battery[][] container = new Battery[2][2]; //두사람이 최대 두개만 담아보면 알 수 있다.
        int a = 0, b = 0;
        for (int j = 0; j < A; j++) {
            if (isRhombus(arr[j].x, arr[j].y, nx1, ny1, arr[j].c)) {
                container[0][a++] = arr[j];
                if (a == 2) break;
            }
        }
        for (int j = 0; j < A; j++) {
            if (isRhombus(arr[j].x, arr[j].y, nx2, ny2, arr[j].c)) {
                container[1][b++] = arr[j];
                if (b == 2) break;
            }
        }
        int max=0;
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                int p1 = (container[0][i]==null)? 0 : container[0][i].p;
                int p2 = (container[0][j]==null)? 0 : container[0][j].p;
                int sum = p1+p2;
                if(container[0][i]==(container[1][j])){
                    sum /=2;
                }
                max = Math.max(sum,max);
            }
        }
        return max;
    }

    private static boolean isRhombus(int x, int y, int nx, int ny, int c) {
        if (Math.abs(x - nx) + Math.abs(y - ny) <= c - 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 1;
        while (T-- > 0) {
            ans = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); //이동시간
            A = Integer.parseInt(st.nextToken()); //충전기갯수
            user = new int[2][M];
            arr = new Battery[A];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    user[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()); //주어진 맵이 뒤집혀있음
                int x = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                arr[i] = new Battery(x, y, c, p);
            }
            move(0, 0, 10, 10);
            Arrays.sort(arr);
            for(Battery b : arr){
                System.out.print(b.p);
            }
            System.out.println();
            System.out.println("#" + (t++) + " " + ans);
        }
    }

}
