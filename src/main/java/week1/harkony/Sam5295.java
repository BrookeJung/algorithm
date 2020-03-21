package week1.harkony;

import java.util.*;

public class Sam5295 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testcase = 1; testcase <= T; testcase++) {
            int N = sc.nextInt();
            int[][] mat = new int[3][N];
            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < N; c++) {
                    mat[l][c] = sc.nextInt();
                }
            }
            int min = findMin(mat, N);
            System.out.println("#" + testcase + " " + min);
        }
    }

    public static int findMin(int[][] mat, int n) {
        int caseNo = 1 << n;
        int min = n;
        for (int i = 0; i < caseNo; i++) {
            int bit = i;
            List<Integer> chosen = new ArrayList<>();
            for (int j = 0; j < n; j++, bit >>= 1) {
                if((bit & 1) == 1)
                    chosen.add(j);
            }
            if (isSatisfied(mat, n, chosen))
                min = Math.min(min, chosen.size());
            //System.out.println(chosen.toString());
        }
        return min;
    }

    public static boolean isSatisfied(int[][] mat, int n, List<Integer> chosen) {
        List<Set<Integer>> listOfSet = new ArrayList<Set<Integer>>();
        Set<Integer> firstLow = new HashSet<>();
        Set<Integer> secondLow = new HashSet<>();
        Set<Integer> thirdLow = new HashSet<>();
        listOfSet.add(firstLow);
        listOfSet.add(secondLow);
        listOfSet.add(thirdLow);

        for (int low = 0; low < 3; low++) {
            Set<Integer> set = listOfSet.get(low);
            for (int col = 0; col < n; col++) {
                if (!chosen.contains(col))
                    set.add(mat[low][col]);
            }
        }
        return listOfSet.get(0).equals(listOfSet.get(1)) && listOfSet.get(1).equals(listOfSet.get(2));
    }
}
