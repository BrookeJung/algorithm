import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Sam2383 {
    //week2 점심 식사 시간
    //체감 난이도 : 최상
    static int N, ans;
    static int[][] map;
    static int[] gate;
    static List<Spot> people;
    static List<Spot> stairs;

    private static class Spot {
        int x;
        int y;

        Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void pickGate(int cnt) { //사람들에 대해 어느 게이트로 갈지 dfs
        if (cnt == people.size()) {
            search();
            return;
        }
        gate[cnt] = 0;
        pickGate(cnt + 1);
        gate[cnt] = 1;
        pickGate(cnt + 1);
    }

    static void search() {
        int maxTime = 0;
        for(int gateNum = 0 ; gateNum < 2 ; gateNum++) { //0 or 1 게이트
            HashMap<Integer,Integer> distMap = new HashMap<>(); //계단까지 소요되는 시간마다의 사람수
            for(int pidx = 0 ; pidx < people.size(); pidx++) {
                if(gate[pidx] == gateNum) {
                    int time = getDistance(people.get(pidx), stairs.get(gateNum));
                    if(!distMap.containsKey(time)){ //해당 소요시간이 키로존재하지않으면
                        distMap.put(time,1);
                    }else{
                        distMap.put(time,distMap.get(time)+1);
                    }
                }
            }
            int mintime = 0;
            //
            int pctime[] = new int[10*2+10*10]; //맵의 최장 길이 N*2 의 소요시간 + 계단대기,내려가는시간 10(최대사람수)*10(최대계단길이)
            for(int time : distMap.keySet()) { // 각 소요거리만큼 사람수를 정해둔곳에서 소요시간마다 for문
                while(distMap.get(time) > 0) { //해당 소요시간에대해 사람이 없으면 멈춘다.
                    distMap.put(time,distMap.get(time)-1); //사람수 -1 갱신
                    int stairsize = map[stairs.get(gateNum).x][stairs.get(gateNum).y]; // 계단사이즈
                    int walktime = time; //계단까지의 소요시간을 초깃값으로 이후에 걸리는 시간을 구할꺼다.
                    while(true) {
                        if(pctime[walktime] < 3) { //3명보다 적다면
                            pctime[walktime] ++; //해당시간에 사람을 추가하고
                            stairsize --;       //계단을 내려간다

                            if(stairsize == 0) { //계단사이즈 0 되면 소요되는 시간을 갱신한다.
                                mintime = Math.max(mintime, walktime + 2); //walktime+ 도착 1분후 내려가고 ,계단 0 으로 내려오는 시간
                                break;
                            }
                        }
                        walktime++; //3명을 넘어가면 시간 +1
                    }
                }
            }
            maxTime = Math.max(maxTime, mintime);
        }
        ans = Math.min(ans, maxTime);
    }

    private static int getDistance(Spot person, Spot stair) {
        return Math.abs(person.x-stair.x)+Math.abs(person.y-stair.y);
    }

    public static void main(String[] args) throws IOException {
        people = new ArrayList<>();
        stairs = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int num = 1;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        people.add(new Spot(i, j));
                    }
                    if (map[i][j] > 1) {
                        stairs.add(new Spot(i, j));
                    }
                }
            }
            gate = new int[people.size()];
            ans=Integer.MAX_VALUE;
            pickGate(0);
            System.out.println("#" + (num++) + " " + ans);
            people.clear();
            stairs.clear();
        }
    }
}
