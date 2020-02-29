import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sam1949 {
    static int[][] rand,visit; //visit 0 : 미방문 1 :방문
    static int N,K,L; //N:땅가로세로길이 , K:깍을수있는수치 ,L :최장경로길이
    static int start; //시작높이
    static int[] dx={1,-1,0,0}, dy={0,0,1,-1};

    public static void searchPath(int x, int y, int leng ){

        for(int i=0;i<4;i++){
            int nx = x +dx[i];
            int ny = y +dy[i];
            if(nx>=0&&nx<4&&ny>=0&&ny<4){

            }
        }
    }

    public static void main(String[] args) throws IOException {
        int [] arr= new int[5];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        while(num-->0){
            String[] info = br.readLine().split(" ");
            N= Integer.parseInt(info[0]);
            K= Integer.parseInt(info[1]);
            rand = new int[N][N];
            visit = new int[N][N];
            for(int i=0;i<N;i++){
                String[] line = br.readLine().split(" ");
                for(int j=0;j<N;j++){
                    rand[i][j]=Integer.parseInt(line[j]);
                    if(start<rand[i][j]){
                        start=rand[i][j];
                    }
                }
            }

        }
    }
}
