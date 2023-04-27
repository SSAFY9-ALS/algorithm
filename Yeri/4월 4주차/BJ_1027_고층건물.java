import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 고층 건물 /  / 20분 0915~
 * https://www.acmicpc.net/problem/1027
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] building = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            building[i] = Integer.parseInt(st.nextToken());
        }

        int [] count = new int[N];
        int result = 0;
        for (int b = 0; b < N; b++) {
            double r=Integer.MIN_VALUE;
            for (int j = b - 1; j >= 0; j--) {
//                if(Math.round((double) building[j]/(b-j))>=r) {
                if((double) (building[j]-building[b])/(b-j)>r) {
                    count[b]++;
//                    r= Math.round((double) building[j]/(b-j);
                    r= (double) (building[j]-building[b])/(b-j);
                }
            }
            r=Integer.MIN_VALUE;
            for (int i = b + 1; i < N; i++) {
                if((double) (building[i]-building[b])/(i-b)>r) {
                    count[b]++;
                    r= (double) (building[i]-building[b])/(i-b);
                }
            }
            if(count[b]>result)
                result=count[b];
        }
        System.out.println(result);
    }
}
