import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Sam4411 {
    static int ans, N;
    static long K;
    static List<Integer> cards;

    public static void main(String[] args) throws IOException {
        cards = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Long.parseLong(st.nextToken());
            for (int i = 1; i < N + 1; i++) {
                cards.add(i);
            }
            move();
            System.out.println("#" + (num++) + " " + ans);
            cards.clear();
        }
    }

    private static void move() {
        if (cards.size() == 1) {
            ans = cards.get(0);
            return;
        }
        long repeat = K;
        while (repeat-- > 0) {
            int temp = cards.get(0);
            cards.remove(0);
            cards.add(temp);
        }
        cards.remove(0);
        move();
    }
}
