package week1.harkony;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sam5650 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testcase = 1; testcase <= T; testcase++) {
            int bestScore=0;
            int N;
            int map[][];
            Map<Integer, Integer> wormhole;
            N = sc.nextInt();
            map = new int[N][N];
            wormhole = new HashMap();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] > 5) {
                        //처음 map에는 wormhole index을 key로 x,y값을 value로 넣는다
                        if (wormhole.get(map[i][j]) == null)
                            wormhole.put(map[i][j], i * 1000 + j);
                        else {
                            //이후 map에는 wormhole index를 key로 연결된 좌표를 가져온다.
                            //그리도 다시 map에 쌍방향 웜홀 좌표를 넣는다.
                            int connected = wormhole.get(map[i][j]);
                            int current = 1000 * i + j;
                            wormhole.remove(map[i][j]);
                            wormhole.put(current, connected);
                            wormhole.put(connected, current);
                        }
                    }
                }
            }
            Game game = new Game(N, map, wormhole);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j]==0) {
                        for(int dir=0;dir<4;dir++) {
                            game.set(i, j, dir);
                            int score=game.play();
                            //System.out.println(i+","+j+","+dir+" : "+score);
                            if(score>bestScore)
                                bestScore=score;
                        }
                    }
                }
            }


            System.out.println("#" + testcase + " " + bestScore);
        }
    }

    static class Game {
        // obstacle[i][j]= i번 osbtacle에  j방향으로 움직이다가 충돌시 전환된 방향
		/* j=>
		0:위
		1:아래
		2:왼쪽
		3:오른쪽
		*/
        // obstacle[i][j]
        int obstacle[][] = {
                // 0번 장애물: 빈곳
                { 0, 0, 0, 0},
                // 1번 장애물
                { 1, 3, 0, 2 },
                // 2번 장애물
                { 3, 0, 1, 2 },
                // 3번 장애물
                { 2, 0, 3, 1 },
                // 4번 장애물
                { 1, 2, 3, 0 },
                // 5번 장애물
                { 1, 0, 3, 2 }, };
        int DIR[][] ={
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };
        int N;
        int map[][];
        // 1000*x+y값을 wormhole에 넣으면 연결된 1000*x+y값을 얻을 수 있다.
        Map<Integer, Integer> wormhole;
        int startLow;
        int startCol;
        int curLow;
        int curCol;
        // dir 0: 위 1:아래  2:왼 3: 오른
        int dir;
        int score;

        public Game(int n, int[][] map, Map<Integer, Integer> wormhole) {
            super();
            N = n;
            this.map = map;
            this.wormhole = wormhole;
        }

        public void set(int low, int col, int dir) {
            startLow=low;
            startCol=col;
            this.curLow = low;
            this.curCol = col;
            this.dir = dir;
            score = 0;
        }

        public int play() {
            while(true) {
                //System.out.println(curLow+","+curCol);
                move();
                if(outOfBoundary()) {
                    score++;
                    reverseDir();
                } else if(map[curLow][curCol]==-1 || (curLow==startLow && curCol==startCol)) {
                    return score;
                } else if(map[curLow][curCol]>0 && map[curLow][curCol]<6) {
                    dir=obstacle[map[curLow][curCol]][dir];
                    score++;
                } else if(map[curLow][curCol]>5) {
                    int value=wormhole.get(curLow*1000+curCol);
                    curLow=value/1000;
                    curCol=value%1000;
                }

            }
        }
        public void move() {
            curLow+=DIR[dir][0];
            curCol+=DIR[dir][1];
        }

        public boolean outOfBoundary() {
            if(curLow<0 || curCol<0 || curLow>=N || curCol>=N)
                return true;
            else
                return false;
        }
        public void reverseDir() {
            if(dir<2)
                dir=1-dir;
            else
                dir=5-dir;
        }

    }
}
