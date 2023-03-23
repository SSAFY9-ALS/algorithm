package march;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 동전 2 / 골드5 / 30분
 * https://www.acmicpc.net/problem/2294
 */

public class BOJ_2294 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        for(int i = 0; i < n; i++)
            coins[i] = Integer.parseInt(in.readLine());
        Arrays.sort(coins);
        int[] dp = new int[k+1]; // dp[i] : i원을 만들 수 있는 최소 동전 개수
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int coin: coins) {
            for(int j = 1; j <= k; j++) {
                if(j - coin < 0 || dp[j-coin] == Integer.MAX_VALUE)
                    continue;
                dp[j] = Math.min(dp[j], dp[j-coin] + 1);
            }
        }
        if(dp[k] == Integer.MAX_VALUE)
            dp[k] = -1;
        System.out.println(dp[k]);
    }
}
