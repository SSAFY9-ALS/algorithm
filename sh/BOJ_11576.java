import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 미래세계에서 사용하는 진법
        int A = Integer.parseInt(st.nextToken());
        // 정이가 사용하는 진법
        int B = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int total = 0;
        int num = 0;

        for(int i = m-1; i >= 0; i--) {
            num = Integer.parseInt(st.nextToken());
            total += num * Math.pow(A, i);
        }

        List<Integer> result = new ArrayList<>();

        int N = 0;
        while(total >= B) {
            N = total%B;
            total = total/B;
            result.add(N);
        }
        result.add(total);

        for(int i = result.size()-1; i >= 0; i--) {
            System.out.print(result.get(i) + " ");
        }
    }
}