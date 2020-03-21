import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
test case 입력
5
3 5 7 9 2
 */

public class ChooseAorB {
    static int N;

    static class ChosenAorB {
        List<Integer> A;
        List<Integer> B;

        ChosenAorB(List<Integer> A, List<Integer> B) {
            this.A = A;
            this.B = B;
        }
    }

    public static List<ChosenAorB> choiceAorB(List<Integer> choose) {
        List<ChosenAorB> cho = new ArrayList<>();
        for (int bit = 0; bit < (1 << N); bit++) {
            List<Integer> a = new ArrayList<>();
            List<Integer> b = new ArrayList<>();
            int window = bit;
            for (int j = 0; j < N; j++, window >>= 1) {
                if ((1 & window) == 1) {
                    a.add(choose.get(j));
                } else {
                    b.add(choose.get(j));
                }
            }
            cho.add(new ChosenAorB(a, b));
        }
        return cho;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++)
            list.add(sc.nextInt());

        for (ChosenAorB c : choiceAorB(list)) {
            System.out.print(c.A.toString());
            System.out.print(c.B.toString());
            System.out.println();
        }
    }

}

