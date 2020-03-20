package week1.harkony;

import java.util.*;

public class Sam5295 {
    static int MIN;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int testcase = 1; testcase <= T; testcase++) {
            int N = sc.nextInt();
            int[][] mat = new int[3][N];
            MIN=N;
            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < N; c++) {
                    mat[l][c] = sc.nextInt();
                }
            }
            int[] chosen=new int[0];
            dfs(mat,0,N,chosen);
            System.out.println("#" + testcase + " " + (MIN));
        }
    }

    public static void dfs(int[][] mat, int current, int limit, int[] chosen){
        if(isSatisfied(mat,limit,chosen)){
            MIN= Math.min(chosen.length, MIN);
        }
        // 종료 조건
        if(current==limit)
            return;
        // 현재 열 선택
        int[] updatedChosen=new int[chosen.length+1];
        System.arraycopy(chosen, 0, updatedChosen, 0, chosen.length);
        updatedChosen[chosen.length]=current;
        dfs(mat,current+1,limit,updatedChosen);

        // 현재 열 선택하지 않음
        dfs(mat,current+1,limit,chosen);
    }

    public static boolean isSatisfied(int[][] mat, int n, int[] chosen){
        List<Set<Integer>> listOfSet=new ArrayList<Set<Integer>>();
        Set<Integer> firstLow=new HashSet<>();
        Set<Integer> secondLow=new HashSet<>();
        Set<Integer> thirdLow=new HashSet<>();
        listOfSet.add(firstLow);
        listOfSet.add(secondLow);
        listOfSet.add(thirdLow);

        for(int low=0;low<3;low++){
            Set<Integer> set=listOfSet.get(low);
            for(int col=0;col<n;col++){
                if(!arrayContains(chosen,col))
                    set.add(mat[low][col]);
            }
        }
        return listOfSet.get(0).equals(listOfSet.get(1)) && listOfSet.get(1).equals(listOfSet.get(2));
    }
    public static boolean arrayContains(int[] array,int target){
        for(int x:array){
            if(x==target)
                return true;
        }
        return false;
    }
}
