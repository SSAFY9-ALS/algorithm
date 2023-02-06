package ssafy;

import java.util.Scanner;

/**
 * 2xn 타일링 2 / 실버3 / 15분
 * https://www.acmicpc.net/problem/11727
 */

public class BOJ_11727 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		dp[1] = 1;
		dp[2] = 3;
		for(int i = 3; i < dp.length; i++) {
			// 2xn 블록을 채우는 방법은 (n-1)까지 채운 상태에서 2x1 블록을 세로로 채우는 것과 (n-2)까지 채운 상태에서 2x1 블록 2개를 가로로 겹쳐서 쌓는 방법, 2x2 블록 하나를 놓는 방법이 있음
			dp[i] = (2 * dp[i-2] + dp[i-1]) % 10007;
		}
		System.out.println(dp[n]);
	}
}
