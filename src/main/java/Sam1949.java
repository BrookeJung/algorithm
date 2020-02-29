import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sam1949 {
    static int[][] rand,visit; //visit 0 : 미방문 1 :방문
    static int N,K;
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
                }
            }

        }
    }
}
