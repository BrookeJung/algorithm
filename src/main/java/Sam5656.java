import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sam5656 {
    static int ans, N, W, H;
    static int[][] map, copy;
    static boolean[][] visited;
    static Queue<Brick> que;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    private static class Brick {
        int x;
        int y;
        int p;

        public Brick(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }
    }

    //dfs 중복순열
    private static void selectLine(int depth, LinkedList<Integer> chosen) {
        if (depth == N) {
            dropTheBall(chosen);
            return;
        }
        for (int i = 0; i < W; i++) {
            chosen.add(i);
            selectLine(depth + 1, chosen);
            chosen.removeLast();
        }
    }

    private static void dropTheBall(LinkedList<Integer> chosen) {
        int bricks = 0;
//        copy = map.clone();
        copy = copyMap();
        que = new LinkedList<>();
        for (int a : chosen) {
            visited = new boolean[H][W];
//            System.out.println("이줄에 드랍"+a);
            crush(a); //드랍
            fallDown(); //파괴한 빈칸 내리기
        }
        bricks = count();
//        System.out.println(bricks);
        ans = Math.min(bricks, ans);
        return;
    }

    //bfs 벽돌깨기
    private static void crush(int pickedCol) {
        for (int i = 0; i < H; i++) { //맨위에서 내려가면서 블럭나올때까지 내려감
            if (copy[i][pickedCol] > 0) {
//                System.out.println(i + "/" + pickedCol + ":" + copy[i][pickedCol]);
                que.offer(new Brick(i, pickedCol, copy[i][pickedCol]));//첫 블록 만나면 큐에넣어 세팅.
                break;
            }
        }
        while (!que.isEmpty()) { // 큐에서 꺼내 숫자만큼 돌며 깨지않았던 파괴할브릭은 큐에 또 넣는다. 큐가 빌때까지.
            Brick curBrick = que.poll();
            int x = curBrick.x;
            int y = curBrick.y;
            visited[x][y] = true;
            copy[x][y] = 0;
            for (int dir = 0; dir < 4; dir++) {
//                System.out.println("상하좌우"+dir+"브릭"+curBrick.x+"/"+curBrick.y+"큐사이즈"+que.size());
                for (int p = 1; p < curBrick.p; p++) { //브릭 파괴력
                    int nx = x + dx[dir] * p;
                    int ny = y + dy[dir] * p;
                    if (!isOutOfBound(nx, ny) && !visited[nx][ny]) { //파괴한적없고 맵안에있으면
                        visited[nx][ny] = true;
                        que.offer(new Brick(nx, ny, copy[nx][ny])); //파괴큐목록에 넣고
                        copy[nx][ny] = 0; //파괴
                    }
                }
            }
        }
    }

    private static boolean isOutOfBound(int nx, int ny) {
        return (nx >= 0 && nx < H && ny >= 0 && ny < W) ? false : true;
    }

    private static void fallDown() {
        for (int i = 0; i < W; i++) {
            Queue<Integer> pickUp = new LinkedList<>();
            for (int j = H - 1; j >= 0; j--) {
                if (copy[j][i] > 0) {
                    pickUp.offer(copy[j][i]); //컬럼마다 돌면서 남은 벽돌을 주워담는다.
                }
            }
//                System.out.println(pickUp.size());
            for (int idx = H - 1; idx >= 0; idx--) {
                if (!pickUp.isEmpty()) {
                    copy[idx][i] = pickUp.poll();
                } else {
                    copy[idx][i] = 0;
                }
            }
        }
//        for(int i=0;i<H;i++){
//            for(int j=0;j<W;j++){
//                System.out.print(copy[i][j]);
//            }
//            System.out.println();
//        }
    }

    private static int[][] copyMap() {
        int[][] arr = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                arr[i][j] = map[i][j];
            }
        }

        return arr;
    }

    //벽돌수
    private static int count() {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (copy[i][j] > 0) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            ans = 12 * 15;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //W 에대해서 어느 곳에 구슬을 떨어뜨릴지는 중복순열
            LinkedList<Integer> chosen = new LinkedList<>();
            selectLine(0, chosen);

            System.out.println("#" + (num++) + " " + ans);
        }

    }
}
