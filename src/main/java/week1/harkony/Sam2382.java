package week1.harkony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sam2382 {
    static int N; // cell의 갯수 , 5~100
    static int M; // 시간
    static int K; // 미생물 군집의 갯수
    static int DIR[][] = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static String DIR_C[] = {"x", "^", "v", "<", ">"};
    static ArrayList<MicroGroup> groups;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Map<Integer, ArrayList<MicroGroup>> map = new HashMap();
        for (int testcase = 1; testcase <= T; testcase++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();
            groups = new ArrayList<MicroGroup>();
            for (int i = 0; i < K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int n = sc.nextInt();
                int dir = sc.nextInt();
                MicroGroup group = new MicroGroup(i, x, y, n, dir);
                groups.add(group);
            }
            for (int time = 0; time < M; time++) {
                for (int g = 0; g < groups.size(); g++)
                    //groups.get(g).print();
                    //System.out.println();
                    map.clear();
                ArrayList<Integer> keys = new ArrayList<Integer>();

                //모든 미생물 군집에 대하여
                for (int i = 0; i < groups.size(); i++) {
                    //이동 후의 위치를 key값으로 한다
                    int key = groups.get(i).move();
                    ArrayList<MicroGroup> list = map.get(key);
                    //해당 위치로 이동을 완료한 그룹이 없으면 ArrayList의 할당이 필요하다
                    if (list == null) {
                        list = new ArrayList<MicroGroup>();
                        keys.add(key);
                    }
                    //해당 위치에 미생물을 추가한다
                    list.add(groups.get(i));
                    map.put(key, list);
                } // end individual move


                for (int i = 0; i < keys.size(); i++) {
                    //assemble : 한 위치에 모여 있는 미생물들의 list
                    ArrayList<MicroGroup> assembles = map.get(keys.get(i));
                    int max = 0;
                    int index_assembles = 0;
                    for (int j = 0; j < assembles.size(); j++) {
                        if (assembles.get(j).n > max) {
                            //가장 군집의 큰 미생물의 규모와 방향을 저장해놓는다.
                            max = assembles.get(j).n;
                            index_assembles = j;
                        }
                    }
                    for (int j = 0; j < assembles.size(); j++) {
                        //가장 큰 규모의 미생물 군집에 다른 미생물 군집을 포함시킨다.
                        if (j != index_assembles) {
                            assembles.get(index_assembles).n += assembles.get(j).n;
                            for (int k = 0; k < groups.size(); k++) {
                                if (groups.get(k).index == assembles.get(j).index) {
                                    groups.remove(k);
                                    break;
                                }
                            }
                        }
                    }

                }
            } // for time end
            int alive = 0;
            for (int i = 0; i < groups.size(); i++)
                alive += groups.get(i).n;
            System.out.println("#" + testcase + " " + alive);
        }
    }

    public static class MicroGroup {
        int index;
        int x;
        int y;
        int n;
        int dir; // 1:up 2:down 3:left 4:right

        MicroGroup(int index, int x, int y, int n, int dir) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.n = n;
            this.dir = dir;
        }

        public int move() {
            x += DIR[dir][0];
            y += DIR[dir][1];

            if (x == 0 || y == 0 || x == N - 1 || y == N - 1) // 경계지역
            {
                if (dir % 2 == 0)
                    dir -= 1;
                else
                    dir += 1;

                n /= 2;
            }
            return (1000 * x + y);
        }

        public void print() {
            System.out.println("[" + index + "]" + " (" + x + "," + y + ")" + DIR_C[dir] + "  : " + n);
        }
    }
}