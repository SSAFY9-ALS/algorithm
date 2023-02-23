package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 연속합 / 실버2 / 50분
 * https://www.acmicpc.net/problem/1912
 */

public class BOJ_1912 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] reader = br.readLine().split(" "); // 이거랑 StringTokenizer 쓰는 거랑 뭐가 더 효율적일까?
		int num;
		int[] dp = new int[n];
		dp[0] = Integer.parseInt(reader[0]);
		int result = dp[0];
		for(int i = 1; i < n; i++) {
			num = Integer.parseInt(reader[i]);
			dp[i] = Math.max(dp[i-1] + num, num); // dp[n] = max(dp[n-1] + n, n)의 공식 사용
			if(result < dp[i]) // dp[i]이 현재 최댓값보다 크면
				result = dp[i]; // 값 갱신
		}
		System.out.println(result);
	}
}
