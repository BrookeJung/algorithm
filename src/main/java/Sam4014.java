import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sam4014   {

    static int [][] hmap, vmap;
    static int N,X,ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num =1;
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            hmap= new int[N][N];
            vmap= new int[N][N];
            for(int i = 0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    hmap[i][j]= Integer.parseInt(st.nextToken());
                    vmap[j][i]= hmap[i][j];
                }
            }
            ans = 0;
            for(int i=0; i<N;i++){ //행 마다 조사
                    search(i,0,vmap,1);
                    search(i,0,hmap,1);

            }
            System.out.println("#"+(num++)+" "+ans);
        }
    }

    private static void search(int x, int y, int[][] map, int same) {
        if(y>=N-1){
            ans++;
            return;
        }
        if(Math.abs(map[x][y]-map[x][y+1])>1){
            return;
        }
        if(map[x][y]==map[x][y+1]){
            search(x,y+1,map,same+1);
        }else if(map[x][y]<map[x][y+1]){ //1높아질때
            if(same>=X){
                same=1;
                search(x,y+1,map,same);
            }
            return;
        }else{ //1낮아질때
            if(y+X>N){
                return;
            }
            for(int i=1;i<X;i++){
                if(y+1+i>N-1){
                    return;
                }
                if(map[x][y+1]!=map[x][y+1+i]){
                    return;
                }
            }

            search(x,y+X,map,1);
        }

    }
}
