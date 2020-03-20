package week1.harkony;

import java.util.*;

public class Sam5644 {
    static int M;
    static int A;
    static int[][] DIR = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
    // key: x,y 좌표 value: 접속가능한 BC의 index list
    static Map<Integer, ArrayList<Integer>> ableBCs ;
    static int P[];

    static int a_move[];
    static int b_move[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testcase = 1; testcase <= T; testcase++) {
            M = sc.nextInt();
            A = sc.nextInt();
            a_move = new int[M];
            b_move = new int[M];
            P = new int[A];
            ableBCs=new HashMap();
            int a_pos[] = new int[2];
            int b_pos[] = new int[2];
            int total = 0;
            a_pos[0] = 1;
            a_pos[1] = 1;
            b_pos[0] = 10;
            b_pos[1] = 10;

            for (int i = 0; i < M; i++)
                a_move[i] = sc.nextInt();
            for (int i = 0; i < M; i++)
                b_move[i] = sc.nextInt();

            for (int i = 0; i < A; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                int p = sc.nextInt();
                setable(i, x, y, c, p);
            }

            total += chargeEach(a_pos, b_pos);
            for (int i = 0; i < M; i++) {
                move(i, a_pos, b_pos);
                int charge = chargeEach(a_pos, b_pos);
                total += charge;
            }
            System.out.println("#"+testcase+" "+total);
        }
    }

    public static boolean isValid(int x, int y) {
        if (x < 1 || x > 10 || y < 1 || y > 10)
            return false;
        return true;
    }

    public static void setable(int i, int x, int y, int c, int p) {
        boolean visit[][] = new boolean[11][11];
        Queue<Integer> queue = new LinkedList();

        P[i] = p;
        queue.add(x);
        queue.add(y);
        queue.add(0);

        while (!queue.isEmpty()) {
            int x_c = queue.poll();
            int y_c = queue.poll();
            int dist = queue.poll();

            if (!visit[x_c][y_c]) {
                int key = 100 * x_c + y_c;
                visit[x_c][y_c] = true;
                ArrayList<Integer> able = ableBCs.get(key);
                if (able == null)
                    able = new ArrayList<Integer>();

                able.add(i);
                ableBCs.put(key, able);

                if (dist < c) {
                    for (int dir = 1; dir <= 4; dir++) {
                        int nx = x_c + DIR[dir][0];
                        int ny = y_c + DIR[dir][1];
                        if (isValid(nx, ny)) {
                            queue.add(nx);
                            queue.add(ny);
                            queue.add(dist + 1);
                        }
                    }
                }
            }
        }
    }

    public static int chargeEach(int[] a_pos, int[] b_pos) {
        ArrayList<Integer> a_able = ableBCs.get(a_pos[0] * 100 + a_pos[1]);
        ArrayList<Integer> b_able = ableBCs.get(b_pos[0] * 100 + b_pos[1]);
        boolean isUsed[]=new boolean[A];

        int max = 0;
        if (a_able != null && b_able != null) {
            for (int a = 0; a < a_able.size(); a++) {
                int charge_a = P[a_able.get(a)];
                isUsed[a_able.get(a)] = true;
                int charge_b = 0;
                for (int b = 0; b < b_able.size(); b++) {
                    if (!isUsed[b_able.get(b)] && P[b_able.get(b)] > charge_b)
                        charge_b = P[b_able.get(b)];
                }
                int sum = charge_a + charge_b;
                isUsed[a_able.get(a)] = false;
                if (sum > max)
                    max = sum;
            }
        } else if (a_able != null && b_able == null) {
            for (int a = 0; a < a_able.size(); a++) {
                if (P[a_able.get(a)] > max)
                    max = P[a_able.get(a)];
            }
        } else if (a_able == null && b_able != null) {
            for (int b = 0; b < b_able.size(); b++) {
                if (P[b_able.get(b)] > max)
                    max = P[b_able.get(b)];
            }
        }
		/*
		System.out.println("[A]: " + a_pos[0] + "," + a_pos[1]);
		System.out.println("[B]: " + b_pos[0] + "," + b_pos[1]);
		System.out.println("charge " + max);
		 */
        return max;
    }

    public static void move(int move_index, int a_pos[], int b_pos[]) {
        int a_dir = a_move[move_index];
        int b_dir = b_move[move_index];

        a_pos[0] += DIR[a_dir][0];
        a_pos[1] += DIR[a_dir][1];
        b_pos[0] += DIR[b_dir][0];
        b_pos[1] += DIR[b_dir][1];

    }
}
