package week1.harkony;

import java.util.Scanner;

public class Sam4411 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            long N = sc.nextLong();
            long K = sc.nextLong();
            long fnk=1;
            for(long n=1;n<N;n++)
            {
                fnk= (fnk+K)%(n+1)+1;
            }
            System.out.println("#" + (i + 1) + " " +fnk);

        }
    }
}
