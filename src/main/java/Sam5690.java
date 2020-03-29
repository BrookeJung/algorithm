import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Sam5690 {
    //week4 5690 혜리의 숫자나누기
    static int ans, N, M;
    static String numbers;
    static LinkedList<Integer> splitIdxes;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            numbers = br.readLine();
            numbers.toCharArray();
            ans = 0;
            visited = new boolean[N];
            splitIdxes = new LinkedList<>();
            if (isMultiM(BigInteger.valueOf(Integer.parseInt(numbers)))) ans++;
            for (int s = 0; s < N - 1; s++) {
                whereToSplit(0, 0, s); //idx,depth,R - 어디에서 구분할지 뽑는 조합
            }
            BigInteger nmg = BigInteger.valueOf(1000000007); //나머지계산
            nmg = BigInteger.valueOf(ans).remainder(nmg);

            System.out.println("#" + (num++) + " " + nmg);

        }
    }

    private static void whereToSplit(int idx, int depth, int r) {
        if (depth == r) {
            splitNumbers();
            return;
        }
        for (int i = idx; i < N - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                splitIdxes.add(i);
                whereToSplit(i, depth + 1, r);
                visited[i] = false;
                splitIdxes.removeLast();
            }
        }
    }

    private static void splitNumbers() {
        LinkedList<Integer> list = (LinkedList<Integer>) splitIdxes.clone();
        Collections.sort(list, Collections.reverseOrder());
        String number = numbers;
        for (int i = 0; i < list.size(); i++) {
            int idx = list.get(i);
            String preIdx = number.substring(0, idx + 1);
            String postIdx = number.substring(idx + 1);
            if (postIdx.equals("") || isMultiM(BigInteger.valueOf(Integer.parseInt(postIdx)))) {
                number = preIdx;
            } else {
                return;
            }
            if (i == list.size() - 1) {
                if (isMultiM(BigInteger.valueOf(Integer.parseInt(number)))) {
                    ans++;
                }
            }
        }


    }

    private static boolean isMultiM(BigInteger big) {
//        if (split % M != 0) {
//            return false;
//        }
//        return true;
//    }
        if (big.remainder(BigInteger.valueOf(M))!=BigInteger.valueOf(0)) {
            return false;
        }
        return true;
    }
}