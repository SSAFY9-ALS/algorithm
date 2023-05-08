package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 계단수 / 골드1 / 2시간 + a (솔루션 봄) / 5월8일
 */
public class BJ_1562_계단수 {
	static int MOD = 1000000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][][] dp = new long[N+1][11][1<<10];
		
		// 1<<i : 0~9 비트마스킹
		for (int i = 1; i < 10; i++) {
			dp[1][i][1<<i] = 1;
		}
		
		for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 1024; k++) {
					int bit = k | (1 << j);
					if (j==0) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) % MOD;
					}
					else if(j==9) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) % MOD;
					}
					else {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k] + dp[i-1][j-1][k]) % MOD;
					}
				}
			}
		}
		
		long sum = 0;
//		System.out.println(dp[2][3][28]);
		for (int i = 0; i < 10; i++) {
//			System.out.println(dp[N][i][1023] + " ");
			sum = (sum + dp[N][i][1023]) % MOD;
		}
//		System.out.println();
		System.out.println(sum);
	}

}
