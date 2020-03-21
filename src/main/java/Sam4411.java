import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Sam4411 {
    static int sword, N;
    static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Long.parseLong(st.nextToken());
            sword = 1; //f(1,k)=1 1번에서 시작
            for (int i = 2; i < N + 1; i++) { //요세푸스 점화식 재귀로 해결하면 StackOverFlow
                sword = (int) ((sword + K) % i + 1);
//                System.out.println("카드수"+i+"sword"+sword);
            }
            System.out.println("#" + (num++) + " " + sword);
        }
    }


}
