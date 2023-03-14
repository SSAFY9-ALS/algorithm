package ws.ws0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동전1 / 골드5 / 1시간 / 3월 14일
 * https://www.acmicpc.net/problem/2293
 */
public class BJ_2293_동전1_이예리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[k + 1];
        dp[0] = 1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
//            dp[i][a]=1;
            arr[i] = a;
        }
        for (int j = 0; j < n; j++) {
            for (int i = arr[j]; i <= k; i++) {
                    dp[i] += dp[i - arr[j]];
            }
        }
        System.out.println(dp[k]);
    }
}
