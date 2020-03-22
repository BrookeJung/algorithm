
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;


public class Bac14919 {

    //Week3 자바의 실수
    /*
5
0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.7 0.7 0.8 0.9
     */
    static int[] rangeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigDecimal M= new BigDecimal(br.readLine());
        BigDecimal term = new BigDecimal(1).divide(M,7,1);
        rangeArr = new int[M.intValue()];
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            BigDecimal r = new BigDecimal(st.nextToken());
            BigDecimal start = new BigDecimal(0);
            for(int i=0;i<M.intValue();i++){
                if((r.compareTo(start)>0||r.compareTo(start)==0)&&r.compareTo(start.add(term))<0){
                    rangeArr[i]++;
                    break;
                }
                start=start.add(term);
            }
        }
        for(int i: rangeArr){
            System.out.print(i+" ");
        }
    }
}