package week1.brookejung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sam1861 {

    static int[][] ROOM;
    static int START_NUM=0,MAX_CNT=0;
    static int[] dx = {1,-1,0,0} , dy={0,0,1,-1};
    static int N=0;

    public static void searchRoute(int x,int y,int cnt,int start){
        for(int i=0;i<4;i++){
            int nx= x+dx[i];
            int ny= y+dy[i];
            if(nx<N && ny<N && nx>=0 && ny>=0){
                if(ROOM[nx][ny]==ROOM[x][y]+1){
                    searchRoute(nx,ny,++cnt,start);
                }
            }
        }
        //상하좌우로 더 못갈때니까 for 문 종료후
        if(cnt==MAX_CNT){//최대수와 카운트수 같을때
            if(start<START_NUM){ //시작넘버 작으면 교체 ROOM[x][y] 로 하면 안됨
                START_NUM=start;
            }
        }
        if(cnt>MAX_CNT){
            MAX_CNT=cnt;
            START_NUM=start;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int printNum = 1;
        while(num-->0){
            N = Integer.parseInt(br.readLine());
            ROOM=new int[N][N];
            for(int i=0;i<N;i++){
                String[] str =br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    ROOM[i][j]=Integer.parseInt(str[j]);
                }
            }
            START_NUM=ROOM[0][0];
            MAX_CNT=0;
            for(int i =0;i<N;i++){
                for(int j=0;j<N;j++){
                    searchRoute(i,j,1,ROOM[i][j]);
                }
            }
            System.out.println("#"+(printNum++)+" "+START_NUM+" "+MAX_CNT);

        }
    }
}
