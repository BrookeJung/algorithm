import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
가장 큰 수
길이가 N인 숫자로 이루어진 문자열이 있다. 이 중 연속하는 K 개의 숫자로 만들 수 있는 수
중에서, 가장 큰 수를 찾는 프로그램을 작성하시오. 예를 들어, 문자열이 “1414213”이고 K=3
이라고 하자. 이 문자열에서 연속한 세 개의 숫자로 만들 수 있는 수는 141, 414, 142, 421,
213이며, 421이 이 중 가장 크기 때문에 답은 421이다.
[입력]
입력으로는 첫 줄에 두 정수 N과 K가 주어지는데, N은 문자열의 길이로 1 이상 100,000 이
하이며, K는 문제에서 주어진 것처럼 연속한 숫자 몇 개를 가지고 수를 만들지를 나타내고, 1
이상 10 이하이다.
다음 줄에는 길이 N인 숫자로 이루어진 문자열이 주어진다.
[출력]
한 줄에 정수 하나를 출력한다. 이 수는 주어진 문자열의 연속한 K 개의 숫자로 만들 수 있는
수 중에서 가장 큰 수이다. 수 앞에 불필요한 0은 출력하면 안된다. 예를 들어, 답이 0054라
면, 54를 출력해야 한다.
 */
public class HyundaiTypeA {
    static int N,K,ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            String str = br.readLine();
            ans=0;
            for(int i=0;i<N-K+1;i++){
                int now = Integer.parseInt(str.substring(i,i+K));
                ans=Math.max(now,ans);
            }
            System.out.println(ans);
            br.close();
    }
}
