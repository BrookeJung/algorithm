
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Sam2382 {
    //week2
    static int bugs;
    static int N, M, K;
    static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1}; //1상 2하 3좌 4우
    static List<bug> list;
    static HashMap<Integer, Integer> turn;

    private static void move(bug bu) {
        int nx = bu.x + dx[bu.dir];
        int ny = bu.y + dy[bu.dir];
        if (IsOnDrug(nx, ny)) {
            bu.cnt = bu.cnt / 2;
            if(bu.cnt==0){
                list.remove(bu);
            }
            bu.dir = turn.get(bu.dir);
        }
        afterMove();
    }
    //이동한뒤 겹치는부분 계산하기 brain stopped
    private static void afterMove() {

    }

    private static void calBugs(){
        for(bug bu : list){
          bugs+=bu.cnt;
        }
    }

    private static Boolean IsOnDrug(int nx, int ny) {
        if (nx == 0 || ny == 0 || nx == N || ny == N) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        turn = new HashMap<>();
        turn.put(1, 2);
        turn.put(2, 1);
        turn.put(3, 4);
        turn.put(4, 3);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // N*N
            M = Integer.parseInt(st.nextToken()); // 격리시간
            K = Integer.parseInt(st.nextToken()); // 미생물 cluster 갯수
            bugs=0;
            int num=1;
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());//x좌표
                int b = Integer.parseInt(st.nextToken());//y좌표
                int c = Integer.parseInt(st.nextToken());//미생물 수
                int d = Integer.parseInt(st.nextToken());//진행방향
                list.add(new bug(a, b, c, d));
            }
            while (M-- > 0) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    move(list.get(i));
                }
            }
            calBugs();
            System.out.println("#"+(num++)+" "+bugs);
        }
    }

    private static class bug {
        int x;
        int y;
        int cnt;
        int dir;

        bug(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }
    }
}
