
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sam2382 {
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

    //week2
    static int bugs;
    static int N, M, K;
    static int[] dx = {0, -1, 1, 0, 0}, dy = {0, 0, 0, -1, 1}; //1상 2하 3좌 4우
    static List<bug> list;
    static HashMap<Integer, Integer> turn;
    static bug[][] map;

    private static void move(bug bu) {
        int nx = bu.x + dx[bu.dir];
        int ny = bu.y + dy[bu.dir];
        if (isOnDrug(nx, ny)) { //약품위에올라왔을때
            bu.cnt = bu.cnt / 2;
            bu.x=nx;
            bu.y=ny;
            bu.dir = turn.get(bu.dir);
        } else {
            if (map[nx][ny] == null) { //이전에 이칸에 군집 들어왔던적없다.
                bu.x = nx;
                bu.y = ny;
                map[nx][ny] = bu;
            } else {
                bug originBug = map[nx][ny];
                originBug.cnt += bu.cnt;
                bu.cnt = 0;
            }
        }
    }

    private static void calBugs() {
        for (bug bu : list) {
            bugs += bu.cnt;
        }
    }

    private static Boolean isOnDrug(int nx, int ny) {
        if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
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
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // N*N
            M = Integer.parseInt(st.nextToken()); // 격리시간
            K = Integer.parseInt(st.nextToken()); // 미생물 cluster 갯수
            list = new ArrayList<>();
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());//x좌표
                int b = Integer.parseInt(st.nextToken());//y좌표
                int c = Integer.parseInt(st.nextToken());//미생물 수
                int d = Integer.parseInt(st.nextToken());//진행방향
                list.add(new bug(a, b, c, d));
            }
            bugs = 0;
            //이동하기전에 일단 미생물 갯수대로 내림차순 정렬하자. -그러면 처음들어온 애 방향대로 나아가면 된다.
            while (M-- > 0) {
                ClusterComparator com = new ClusterComparator();
                Collections.sort(list, com);
                map=new bug[N][N];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (list.get(i).cnt == 0) continue;
                    move(list.get(i));
                }
            }
            calBugs();
            System.out.println("#" + (num++) + " " + bugs);
            list.clear();
        }
    }

    static class ClusterComparator implements Comparator<bug> {

        @Override
        public int compare(bug o1, bug o2) { //내림차순
            if (o1.cnt > o2.cnt) {
                return -1;
            } else if (o1.cnt < o2.cnt) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
