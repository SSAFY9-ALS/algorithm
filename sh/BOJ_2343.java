import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 기타레슨 / 실버1 / 1시간 30분
 * https://www.acmicpc.net/problem/2343
 */
public class BJ_2343_기타레슨 {
    static int N;
    static int M;
    static int[] arr;
    static int low, high;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for(int i =0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            low = Math.max(low, arr[i]);
        }

        high = sum;
        search();
        System.out.println(low);
    }

    public static void search() {
        int mid, sum, cnt;
        while(low <= high) {
            mid = low + high >> 1;
            sum = 0;
            cnt = 0;
            for(int i = 0; i < N; i++) {
                if(sum + arr[i] > mid) {
                    sum = 0;
                    cnt++;
                }
                sum += arr[i];
            }

            if(sum > 0) cnt++;
            if(cnt <= M) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }
}

