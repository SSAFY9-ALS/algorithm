package net.acmicpc;

import java.util.*;

/** 
 * 계단 수 / 골드 1 / 110분
 * @author 민정인
 * https://www.acmicpc.net/problem/1562
 */

public class BOJ_1562 {
	static int n;
	static final int MOD = 1000000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[][][] dp = new int[n+1][10][1<<10];	// [자릿수][들어갈 수][사용한 수]
		for(int i = 1; i < 10; i++) {
			dp[1][i][1<<i] = 1;
		}
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k < 1<<10; k++) {
					if(j > 0) {	// 현재 자릿수에 수가 들어갈 때 이전에 선택한 수를 기준으로 +1이 된 수의 경우 + -1이 된 수의 경우
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i-1][j-1][k]) % MOD;
					}
					if(j < 9){
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i-1][j+1][k]) % MOD;
					}
				}
			}
		}
		int result = 0;
		for(int i = 0; i < 10; i++) {
			result = (result + dp[n][i][1023]) % MOD;
		}
		System.out.println(result);
	}
}
