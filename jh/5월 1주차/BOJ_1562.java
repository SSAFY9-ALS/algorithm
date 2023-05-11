package may;

import java.util.Scanner;

/**
 * 계단 수 / 골드1 / 솔루쳔 참고 / 5월 11일"
 * https://www.acmicpc.net/problem/1562
 */

public class BOJ_1562 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int mod = 1000000000; 
		long[][][] dp = new long[n+1][11][1<<10];
		// dp[i][j][k] => i번째 자리에 j가 올 때, 마킹된 숫자 k가 있는 경우의 수

		// 1<<i : 0~9 비트마스킹
		for(int i = 1; i < 10; i++) {
			dp[1][i][1<<i] = 1;
		}

		// 점화식: dp[i][j][visit | (1<<k)] = dp[i-1][j-1][visit] + dp[i-1][j+1][visit]
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j <= 9; j++) {
				for(int k = 0; k < 1024; k++) {
					int bit = k | (1 << j);
					if(j == 0) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) % mod;
					}
					else if(j == 9) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) % mod;
					}
					else {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]+ dp[i-1][j-1][k]) % mod;
					}
				}
			}
		}

		long sum = 0;
		for(int i = 0; i < 10; i++) {
			sum = (sum + dp[n][i][1023]) % mod;
		}
		System.out.println(sum);
	}
}
