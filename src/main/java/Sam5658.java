import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//소요시간 :50분
public class Sam5658 {
    static int ans, N, K;
    static TreeSet<Integer> passwords;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            String numbers = br.readLine();
            //중복되지않게 일단 생길수있는 비밀번호들을 treeSet에 넣는다.
            passwords = new TreeSet<>();
            //로테이션은 한칸에들어갈수있는 수-1번만 돌려보면된다.
            getPasswords(numbers, N / 4);
            //트리셋을 내리차순으로 정렬하고 arraylist에 넣어서 해당하는 k번째 (k-1인덱스) 비밀번호를 찾는다.
            getPassword();
            System.out.println("#" + (num++) + " " + ans);
        }
    }

    private static void getPassword() {
        ArrayList<Integer> indexPassword= new ArrayList<>(passwords.descendingSet());
        ans = indexPassword.get(K-1);
    }

    private static void getPasswords(String numbers, int rotate) {
        for (int r = 0; r < rotate; r++) {
            String rotatedNumbers = numbers.substring(r)+numbers.substring(0,r);
            for(int start=0;start<=N-rotate;start+=rotate){
                String password = rotatedNumbers.substring(start,start+rotate);
//                System.out.println(password);
                int passwordInTen = Integer.parseInt(password,16);
                passwords.add(passwordInTen);
            }
        }
    }



}
