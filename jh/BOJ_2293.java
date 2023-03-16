package algorithm_mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 동전1 / 골드5 / 2시간
 * https://www.acmicpc.net/problem/2293
 */

public class BOJ_2293 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // n 입력
		int k = Integer.parseInt(st.nextToken()); // k 입력
		
		int[] coins = new int[n]; // 동전의 가치를 저장할 배열
		for(int i = 0; i < n; i++)
			coins[i] = Integer.parseInt(in.readLine()); // 동전 가치 입력
		Arrays.sort(coins); // 오름차순 정렬
		
		int[] dp = new int[k+1]; // dp[i] => i원을 나타내는 경우의 수
		dp[0] = 1; // 0을 나타내는 경우의 수를 1로 해줘야  dp[i]에 i원의 동전이 존재하는 경우를 포함할 수 있음
		
		// 점화식: 동전마다 탐색하며 dp[i] = dp[i] + dp[i-coin];
		for(int coin: coins) {
			for(int i = 1; i <= k; i++) {
				if(i - coin < 0)
					continue;
				dp[i] += dp[i-coin];
			}
		}
		System.out.println(dp[k]); // 결과 출력
	}
}
