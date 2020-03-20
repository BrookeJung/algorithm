package week1.harkony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sam5648 {
    static int DIR[][] = { {  0,  1 }, {  0,  -1 }, {  -1,  0 },
            {  1,  0 } };
    static double[][] REVERSE_HALF = { {  0,  (double) -0.5 }, {  0,  (double) 0.5 },
            {  (double) 0.5,  0 }, {  (double) -0.5,  0 } };
    static ArrayList<Atom> atoms;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testcase = 1; testcase <= T; testcase++) {
            int ans = 0;
            int N = sc.nextInt();
            atoms = new ArrayList<Atom>();
            for (int i = 0; i < N; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int dir = sc.nextInt();
                int e = sc.nextInt();
                atoms.add(new Atom(x+1000, y+1000, dir, e));
            }

            // 유효한 원자들이 모두 없어질 때 까지
            while (atoms.size() > 0) {

                Map<Double, Boolean> isCollision = new HashMap();
                Map<Double, Integer> shadow = new HashMap();

                // 모든 atom은 움직이기 전의 자신의 위치를 그림자로 남긴다.
                for (int i = 0; i < atoms.size(); i++) {
                    shadow.put(atoms.get(i).getKey(), atoms.get(i).dir);
                }

                // 모든 원자들에 대해서 이동한다.
                for (int i = 0; i < atoms.size(); i++) {
                    Atom atom = atoms.get(i);
                    atom.move();

                    // 범위안의 원자들만 충돌이 가능하다.
                    if (!atom.outOfBound()) {
                        // key값은 x좌표*10000+y좌표 이다.
                        // x,y범위 안에서 key값은 유일하다.
                        double key = atom.getKey();

                        // 현재 atom이 이동하려는 위치에 이미 이동을 완료한 다른 atom이 있는지
                        Boolean value = isCollision.get(key);

                        // 자신과 반대방향의 그림자를 마주치면
                        if ((shadow.get(key) != null
                                && (shadow.get(key) + atom.dir == 1 || shadow.get(key) + atom.dir == 5))) {
                            // 다시 반대로 반 칸 이동하고
                            atom.moveReverseHalf();
                            // key값을 다시 구하여 충돌지역에 추가한다.
                            key = atom.getKey();
                            isCollision.put(key, true);
                        } else if (value == null) {
                            // 이동하려는 위치에 이동을 끝 마친 다른 atom이 없으면
                            isCollision.put(key, false);
                        } else if (value != null) {
                            // 이동하려는 위치에 이동을 끝 마친 다른 atom이 있으면
                            isCollision.put(key, true);
                        }
                    } else { // 범위 밖의 원자들은 충돌 가능성이 없으므로 삭제한다.
                        atoms.remove(i);
                        // list가 앞으로 땡겨지므로 i는 증가시키지 않고 유지한다.
                        i--;
                    }
                }

                // 모든 atom에 대해서 충돌지역에 있는 atom은 에너지를 방출하고 삭제한다.
                for (int i = 0; i < atoms.size(); i++) {
                    if (isCollision.get(atoms.get(i).getKey())) {
                        ans += atoms.get(i).getEnergy();
                        //System.out.println(atoms.get(i).getKey());
                        atoms.remove(i);
                        i--;
                    }
                }

            } // time loop end
            System.out.println("#" + testcase + " " + ans);
        }
    }

    public static class Atom {
        double x;
        double y;
        int dir;
        int energy;

        public void move() {
            x += DIR[dir][0];
            y += DIR[dir][1];
        }

        public void moveReverseHalf() {
            x += REVERSE_HALF[dir][0];
            y += REVERSE_HALF[dir][1];
        }

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }

        public double getKey() {
            return (10000*x+y);
        }

        public int getEnergy() {
            return energy;
        }

        public boolean outOfBound() {
            if (x < 0 || y < 0 || x > 2000 || y > 2000)
                return true;

            else
                return false;
        }

    }
}
