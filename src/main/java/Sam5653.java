import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sam5653 {
    //week4
    //난이도 :중하 소요시간:2.5
    static Cell[][] map;
    static int ans;
    static int[] dy={0,0,-1,1}, dx={-1,1,0,0};

    private static class Cell {
        int x; //좌표
        int y;
        int v; //생명력
        int c; //생성시점
        boolean die;

        public Cell(int x, int y, int v, int c, boolean die) {
            this.x = x;
            this.y = y;
            this.v = v;
            this.c = c;
            this.die = die;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            map = new Cell[350][350]; //시간은 최대 300 , N,M최대 50
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    int v = Integer.parseInt(st.nextToken());
                    if(v == 0) continue;
                    map[i+175-N/2][j+175-M/2]=new Cell(i+175-N/2,j+175-M/2,v,0,false);
                }
            }

            for(int t=1;t<K+1;t++){//시간만큼 번식수행
                grow(t);
            }
            ans =0;
            countCell();

            System.out.println("#" + (num++) + " " + ans);
        }


    }

    private static void countCell() {
        for(int i=0;i<350;i++){
            for(int j=0;j<350;j++){
                if(map[i][j]==null) continue;
                if(map[i][j].die) continue;
                ans++;
            }
        }
    }

    private static void grow(int t) {
        for(int i=0;i<350;i++){
            for(int j=0;j<350;j++){
                if(map[i][j]==null) continue;  //빈그리드
                Cell cel = map[i][j];
                if(cel.die) continue;
                if(t-cel.c<=cel.v) continue; // 비활성셀
                if(t-cel.c==cel.v*2){ //현재시간 - 만들어진시간 >= 생명력 *2
                    cel.die=true;
                }
                for(int dir =0;dir<4;dir++){
                    int nx = i+dx[dir];
                    int ny = j+dy[dir];
                    if(isMovable(nx,ny,t,cel.v)){
                        map[nx][ny] = new Cell(nx,ny,cel.v,t,false);
                    }
                }
            }
        }
    }

    private static boolean isMovable(int nx, int ny , int curTime, int curVital) {
        if(map[nx][ny]==null) return true;
        if(map[nx][ny].c==curTime) {
            if(map[nx][ny].v<curVital){ //동시생성시 지금꺼가 생명력이 더크면 생성가능
                return true;
            }
        }
        return false;
    }
}
